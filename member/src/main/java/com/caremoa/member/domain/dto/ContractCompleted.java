package com.caremoa.member.domain.dto;

import com.caremoa.member.adapter.AbstractEvent;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.domain.dto
* @fileName       : ContractCompleted.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : 계약종료 Polish Evnet
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ContractCompleted extends AbstractEvent{
	private Long memberId; // -- ID
	private Long helperId; // -- ID
	
	@Builder
	public ContractCompleted(Long memberId, Long helperId) {
		super();
		this.memberId = memberId;
		this.helperId = helperId;
	}
}
