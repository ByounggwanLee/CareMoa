package com.caremoa.member.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
	USER("ROLE_USER","회원"),
	HELPER("ROLE_HELPER","도우미"),
	ADMIN("ROLE_ADMIN", "관리자");
	
	private final String key;
	private final String title;
}
