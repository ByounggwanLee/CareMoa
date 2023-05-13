package com.caremoa.member.domain.dto;

import com.caremoa.member.adapter.AbstractEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
