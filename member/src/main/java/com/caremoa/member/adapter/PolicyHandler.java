package com.caremoa.member.adapter;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.ErrorMessage;

import com.caremoa.member.domain.dto.ContractCompleted;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class PolicyHandler {

	private ObjectMapper objectMapper = new ObjectMapper();
	private long errorOccur = 0;
	
	@Bean
	public Consumer<ContractCompleted> basicConsumer() {
		return contractCompleted -> {
			log.info( "{} : {}", contractCompleted.getEventType(), contractCompleted.validate());
			log.info("teamUpdated 이벤트 수신 : {}", contractCompleted);
		};
	}
	
	@Bean
	public Consumer<ErrorMessage> KafkaErrorHandler() {
		return e -> {
	    	errorOccur++;
	        log.error("에러 발생: {}, 횟수: {}", e, errorOccur);
	    };
	}
}
