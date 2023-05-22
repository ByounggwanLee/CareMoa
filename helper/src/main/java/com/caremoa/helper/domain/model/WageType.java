package com.caremoa.helper.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : WageType.java
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
public enum WageType {
	HOURLY("WAGE_HOURLY" , "시급"),
	MONTHLY("WAGE_MONTHLY" , "월급"); 

	private final String key;
	private final String title;
}
