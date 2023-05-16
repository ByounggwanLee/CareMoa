package com.caremoa.member;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing     // JPA Auditing을 활성화 하기 위한 어노테이션
@EnableFeignClients
@Slf4j
public class MemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
	/*
    @Bean
    public Supplier<Integer> numberProducer() {
        return () -> new SecureRandom().nextInt(1, 100);
    }

	@Bean
	public Consumer<Integer> numberConsumer() {
		return incomingNumber -> log.info("Incoming Number : {}", incomingNumber);
	}

	@Bean
	public Function<Integer, Double> consumeAndProcessSqrt() {
		return Math::sqrt;
	}

	@Bean
	public Function<Integer, Double> consumeAndProcessCube() {
		return incomingNumber -> Math.pow(incomingNumber, 3);
	}
    */
}
