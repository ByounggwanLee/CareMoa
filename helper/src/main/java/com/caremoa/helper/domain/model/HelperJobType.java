package com.caremoa.helper.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : HelperJobType.java
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
public enum HelperJobType {
	BABYSITER("JOB_BABYSITER" , "유아돌봄"),
	BABYHOMEWORKER("JOB_BABYHOMEWORKER" , "유아가사돌봄"),
	HOMEWORKER("JOB_HOMEWORKER" , "가사돌봄"); // 추후사용

	private final String key;
	private final String title;
}
