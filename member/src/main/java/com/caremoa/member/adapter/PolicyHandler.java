package com.caremoa.member.adapter;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.service.MemberService;
import com.caremoa.member.exception.ApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : PolicyHandler.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : Cloud Stream 을 이용한 Pub/Sub 구현
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
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

    @Bean
    Consumer<ContractCompleted> basicConsumer() {
		return contractCompleted -> {
			log.info( "{} : {}", contractCompleted.getEventType(), contractCompleted.validate());
			log.info("teamUpdated 이벤트 수신 : {}", contractCompleted);
		};
	}


    @Bean
    Consumer<ErrorMessage> KafkaErrorHandler() {
		return e -> {
	    	errorOccur++;
	        log.error("에러 발생: {}, 횟수: {}", e, errorOccur);
	    };
	}
    
    /**
     * @methodName    : ReflectionScore
     * @date          : 2023.05.19
     * @description   : 점수반영
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
			log.debug("{} : {}", e.getCode(), e.getMessage() );;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * @methodName    : wheneverContractCompletedThenReflectionScore
     * @date          : 2023.05.19
     * @description   : 계약이 완료될 때마다 반영 점수
     * @return
    */
    @Bean
    Consumer<ContractCompleted>wheneverContractCompletedThenReflectionScore() {
    	return contractCompleted -> {
    		log.debug("Call contractCompleted : {}", contractCompleted.validate() );
    		if ( contractCompleted.validate() ) {
    			 ReflectionScore(CONTRACT_SCORE, contractCompleted.getMemberId());
    		}
    	};
    }
    
    /**
     * @methodName    : wheneverClaimCompletedThenReflectionScore
     * @date          : 2023.05.19
     * @description   : Claim이 완료될 때마다 반영 점수
     * @return
    */
    @Bean
    Consumer<ClaimCompleted>wheneverClaimCompletedThenReflectionScore() {
    	return claimCompleted -> {
    		log.debug("Call claimCompleted : {}", claimCompleted.validate() );
    		if ( claimCompleted.validate() ) {
    		     ReflectionScore(CLAIM_SCORE, claimCompleted.getMemberId());
    		}
    	};
    }
    
    /**
     * @methodName    : whenevereReviewMemberEvaluatedThenReflectionScore
     * @date          : 2023.05.19
     * @description   : 고객이 도우미로 부터 평가될 때마다 반영 점수
     * @return
    */
    @Bean
    Consumer<ReviewMemberEvaluated>whenevereReviewMemberEvaluatedThenReflectionScore() {
    	return reviewMemberEvaluated -> {
    		log.debug("Call reviewMemberEvaluated : {}", reviewMemberEvaluated.validate() );
    		if ( reviewMemberEvaluated.validate() ) {
    		     ReflectionScore(REVIEW_SCORE * ( reviewMemberEvaluated.getGreat() ? 1 : -1 )  , 
    				        reviewMemberEvaluated.getMemberId());
    		}
    	};
    }
}
