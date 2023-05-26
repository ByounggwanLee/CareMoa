package com.caremoa.member.adapter;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.service.MemberService;
import com.caremoa.member.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @packageName : com.caremoa.member.adapter
 * @fileName : PolicyHandler.java
 * @author : 이병관
 * @date : 2023.05.14
 * @description : Cloud Stream 을 이용한 Pub/Sub 구현
 *              =========================================================== DATE
 *              AUTHOR NOTE
 *              -----------------------------------------------------------
 *              2023.05.14 이병관 최초 생성
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class PolicyHandler {

	private long errorOccur = 0;
	private final MemberService memberService;
	private final static int CONTRACT_SCORE = 1;
	private final static int CLAIM_SCORE = -1;
	private final static int REVIEW_SCORE = 2;

	/**
	 * @methodName : ReflectionScore
	 * @date : 2023.05.19
	 * @description : 점수반영
	 * @param memberId
	 */
	private void ReflectionScore(int score, Long memberId) {
		try {
			log.debug("ReflectionScore {}, {}", score, memberId);

			Member member = Member.builder().userScore(score).build();

			member = memberService.reflectionScore(member, memberId);

			log.debug("점수반영 {}", member);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			log.debug("{} : {}", e.getCode(), e.getMessage());
			;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Bean
	Consumer<Map<String, Object>> basicConsumer() {

		return mapData -> {
			ObjectMapper mapper = new ObjectMapper();
			log.debug(mapData.toString());
			
			switch (mapData.get("eventType").toString()) {

			case "ContractCompleted": // 계약이 완료될 때마다 반영 점수
				ContractCompleted contractCompleted = mapper.convertValue(mapData, ContractCompleted.class);
				log.debug("contractCompleted : {}", contractCompleted.toString());
				ReflectionScore(CONTRACT_SCORE, contractCompleted.getMemberId());
				break;

			case "ClaimCompleted": // Claim이 완료될 때마다 반영 점수
				ClaimCompleted claimCompleted = mapper.convertValue(mapData, ClaimCompleted.class);
				log.debug("claimCompleted : {}", claimCompleted.toString());
				ReflectionScore(CONTRACT_SCORE, claimCompleted.getMemberId());
				break;

			case "ReviewMemberEvaluated": // 고객이 도우미로 부터 평가될 때마다 반영 점수
				ReviewMemberEvaluated reviewMemberEvaluated = mapper.convertValue(mapData, ReviewMemberEvaluated.class);
				log.debug("reviewMemberEvaluated : {}", reviewMemberEvaluated.toString());
				ReflectionScore(REVIEW_SCORE * (reviewMemberEvaluated.getGreat() ? 1 : -1),
						reviewMemberEvaluated.getMemberId());
				break;
			
			default: // 처리가 정의되지 않은 이벤트
				log.debug("Undefined EventType : {}", mapData.get("eventType").toString());
				break;
			}
		};
	}

	@Bean
	Consumer<ErrorMessage> KafkaErrorHandler() {
		return e -> {
			errorOccur++;
			log.error("에러 발생: {}, 횟수: {}", e, errorOccur);
		};
	}
}
