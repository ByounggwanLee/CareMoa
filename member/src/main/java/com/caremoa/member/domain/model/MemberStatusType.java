package com.caremoa.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
* @packageName    : com.caremoa.member.domain.model
* @fileName       : MemberStatusType.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : MemberStatusType
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Getter
@RequiredArgsConstructor
public enum MemberStatusType {
	ENABLED("STATUS_ENABLED" , "사용중"),
	DISABLED("STATUS_DISABLED" , "사용중지"), // 사용중지 1년 후 삭제
	DELETED("STATUS_DELETEED", "삭제");       // 삭제 후 2년뒤 완전삭제

	private final String key;
	private final String title;
}
