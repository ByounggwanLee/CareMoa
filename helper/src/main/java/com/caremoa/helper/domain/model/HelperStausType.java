package com.caremoa.helper.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : HelperStausType.java
* @author         : 이병관
* @date           : 2023.05.22
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.22        이병관       최초 생성
*/
@Getter
@RequiredArgsConstructor
public enum HelperStausType {
	WRITING("STATUS_WRITING" , "작성중"),
	UNDERWRITING("STATUS_UNDERWRITING" , "심사중"),
	ACTIVE("STATUS_ACTIVE" , "승인"),
	WORKING("STATUS_WORKING" , "도우미수행중"),
	DISABLED("STATUS_DEACTIVE" , "탈퇴"), // 탈퇴 후 3개월 후 삭제
	DELETED("STATUS_DELETEED", "삭제");    // 탈퇴 후 2년뒤 완전삭제


	private final String key;
	private final String title;
}
