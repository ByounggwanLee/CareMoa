package com.caremoa.authority.domain.dto;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.caremoa.authority.domain.model.MemberStatusType;
import com.caremoa.authority.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* @packageName    : com.caremoa.member.domain.dto
* @fileName       : MemberDto.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : MemberDto
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
	@Schema(description = "ID", nullable = true)
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

	// List<MemberRoleDto> memberRoles = new ArrayList<>();;

	@Schema(description = "사용자별점", nullable = true, defaultValue = "50")
	private Integer userScore; // -- 서비스 사용자 점수

	@Enumerated(EnumType.STRING)
	@Schema(description = "상태", nullable = true, defaultValue = "ENABLED", example = "")
	private MemberStatusType status; // -- 계정상태

	@Schema(description = "생성일시", nullable = true)
	private LocalDateTime createdTime; // --최초_등록_시간

	@Schema(description = "최종수정시간", nullable = true)
	private LocalDateTime modifiedTime; // --최종_수정_시간
}
