package com.caremoa.member.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : ContractCompleted.java
* @author         : 이병관
* @date           : 2023.05.19
* @description    : 계약종료 Polish Evnet
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.19        이병관       최초 생성
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
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
