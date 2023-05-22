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
public enum WageAmountType {
	HOURLYBASE("AMOUNT_HOURLYBASE" , "9,600"),
	HOURLY01("AMOUNT_HOURLY01" ,  "10,000"),
	HOURLY02("AMOUNT_HOURLY02" , "11,000"),
	HOURLY03("AMOUNT_HOURLY03" , "12,000"),
	HOURLY04("AMOUNT_HOURLY04" , "13,000"),
	HOURLY05("AMOUNT_HOURLY05" , "14,000"),
	HOURLY06("AMOUNT_HOURLY06" , "15,000"),
	MONTHLYBASE("AMOUNT_MONTHLYBASE" , "90만원"),
	MONTHLY10("AMOUNT_MONTHLY10" , "100만원"),
	MONTHLY11("AMOUNT_MONTHLY11" , "110만원"),
	MONTHLY12("AMOUNT_MONTHLY12" , "120만원"),
	MONTHLY13("AMOUNT_MONTHLY13" , "130만원"),
	MONTHLY14("AMOUNT_MONTHLY14" , "140만원"),
	MONTHLY15("AMOUNT_MONTHLY15" , "150만원"),
	MONTHLY16("AMOUNT_MONTHLY16" , "160만원"),
	MONTHLY17("AMOUNT_MONTHLY17" , "170만원"),
	MONTHLY18("AMOUNT_MONTHLY18" , "180만원"),
	MONTHLY19("AMOUNT_MONTHLY19" , "190만원"),
	MONTHLY20("AMOUNT_MONTHLY20" , "200만원"),
	MONTHLY21("AMOUNT_MONTHLY21" , "210만원"),
	MONTHLY22("AMOUNT_MONTHLY22" , "220만원"),
	MONTHLY23("AMOUNT_MONTHLY23" , "230만원"),
	MONTHLY24("AMOUNT_MONTHLY24" , "240만원"),
	MONTHLY25("AMOUNT_MONTHLY25" , "250만원"),
	MONTHLY26("AMOUNT_MONTHLY26" , "260만원"),
	MONTHLY27("AMOUNT_MONTHLY27" , "270만원"),
	MONTHLY28("AMOUNT_MONTHLY28" , "280만원"),
	MONTHLY29("AMOUNT_MONTHLY29" , "290만원"),
	MONTHLY30("AMOUNT_MONTHLY30" , "300만원");
	
	private final String key;
	private final String title;
}
