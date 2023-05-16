package com.caremoa.member.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : AbstractEvent.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : Event pub/sub 정의을 위한 부모클래스
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Data
public class AbstractEvent {
	private String eventType;
	private String timestamp;

    public AbstractEvent(){
    	// 정의한 클래스명이 들어감
        this.setEventType(this.getClass().getSimpleName());
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }

    public String toJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }

    public boolean validate(){
    	// 정의한 클래스명의 Subscription인지 확인
        return getEventType().equals(getClass().getSimpleName());
    }
}
