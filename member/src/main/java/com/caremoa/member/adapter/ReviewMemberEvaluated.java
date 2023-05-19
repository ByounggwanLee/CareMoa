package com.caremoa.member.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : ReviewMemberEvaluated.java
* @author         : 이병관
* @date           : 2023.05.19
* @description    : 리뷰 고객 평가 Polish Event
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.19        이병관       최초 생성
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ReviewMemberEvaluated extends AbstractEvent{
	private Long memberId; // -- ID
	private Long helperId; // -- ID
	private Boolean great;

	@Builder
	public ReviewMemberEvaluated(Long memberId, Long helperId, Boolean great) {
		super();
		this.memberId = memberId;
		this.helperId = helperId;
		this.great = great;
	}
}
