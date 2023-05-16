package com.caremoa.member.adapter;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import com.caremoa.member.domain.dto.ContractCompleted;

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
public class PolicyHandler {

	private long errorOccur = 0;

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
}
