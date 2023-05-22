package com.caremoa.helper.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : TakerLevelType.java
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
public enum TakerLevelType {
	NEWBORN("LEVEL_NEWBORN" , "신생아(0~6개월)"),
	INFANT("LEVEL_INFANT" , "영아(7~36개월)"),
	CHILD("LEVEL_CHILD" , "유아(4~7세"),
	ELEMENTARY("LEVEL_ELEMENTARY" , "초등생(8~13세)"); 
	private final String key;
	private final String title;
}
