package com.caremoa.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
* @packageName    : com.caremoa.member.domain.model
* @fileName       : RoleType.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : RoleType
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Getter
@RequiredArgsConstructor
public enum RoleType {
	USER("ROLE_USER","회원"),
	HELPER("ROLE_HELPER","도우미"),
	ADMIN("ROLE_ADMIN", "관리자");

	private final String key;
	private final String title;
}
