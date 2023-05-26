package com.caremoa.member.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : ClaimCompleted.java
* @author         : 이병관
* @date           : 2023.05.19
* @description    : 클레임종료 Polish Event
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.19        이병관       최초 생성
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ClaimCompleted extends AbstractEvent{
	private Long memberId; // -- ID
	private Long helperId; // -- ID

	@Builder
	public ClaimCompleted(Long memberId, Long helperId) {
		super();
		this.memberId = memberId;
		this.helperId = helperId;
	}
}
