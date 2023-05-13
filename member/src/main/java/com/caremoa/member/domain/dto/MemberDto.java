package com.caremoa.member.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.model.MemberStatusType;
import com.caremoa.member.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
	private Long id; // -- ID
	@Schema(description = "사용자ID", nullable = false, defaultValue = "lbg1225")
	private String userId; // -- 사용자ID

	@Schema(description = "패스워드", nullable = false)
	private String password; // -- 암호

	@Schema(description = "이름", nullable = true, defaultValue = "이병관")
	private String name; // -- 이름

	@Schema(description = "별명", nullable = true, defaultValue = "Daniel")
	private String nickname; // -- 별명

	private Address address; // -- 도/시, 군/구, 읍/동
	
	// List<MemberRole> memberRoles = new ArrayList<>();;

	@Schema(description = "사용자별점", nullable = true, defaultValue = "50")
	private Integer userScore; // -- 서비스 사용자 점수

	@Enumerated(EnumType.STRING)
	@Schema(description = "상태", nullable = true, defaultValue = "ENABLED", example = "")
	private MemberStatusType status; // -- 계정상태

	@Schema(description = "생성일시", nullable = true)
	private LocalDateTime createdTime; // --최초_등록_시간

	@Schema(description = "최종수정시간", nullable = true)
	private LocalDateTime modifiedTime; // --최종_수정_시간

	public Member toModel() {
		return Member.builder()
		      .id(id)
		      .userId(userId)
		      .password(password)
		      .name(name)
		      .nickname(nickname)
		      .address(address)
		      .userScore(userScore)
		      .status(status)
		      .createdTime(createdTime)
		      .modifiedTime(modifiedTime)
		      .build();
	}

    public static MemberDto toDto(final Member member) {
    	 
    	return MemberDto.builder()
		       .id(member.getId())
		       .userId(member.getUserId())
		       .password(member.getPassword())
		       .name(member.getName())
		       .nickname(member.getNickname())
		       .address(member.getAddress())
		       .userScore(member.getUserScore())
		       .status(member.getStatus())
		       .createdTime(member.getCreatedTime())
		       .modifiedTime(member.getModifiedTime())
		       .build();
	} 
}
