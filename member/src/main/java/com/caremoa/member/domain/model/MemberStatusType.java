package com.caremoa.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatusType {
	ENABLED("STATUS_ENABLED" , "사용중"),
	DISABLED("STATUS_DISABLED" , "사용중지"), // 사용중지 1년 후 삭제
	DELETED("STATUS_DELETEED", "삭제");       // 삭제 후 2년뒤 완전삭제

	private final String key;
	private final String title;	
}
