package com.caremoa.authority.domain.dto;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.caremoa.authority.domain.model.RoleType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* @packageName    : com.caremoa.member.domain.dto
* @fileName       : MemberRoleDto.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : MemberRoleDto
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
public class MemberRoleDto {
	@Schema(description = "ID", nullable = true)
	private Long id;

	@Schema(description = "회원ID", nullable = false )
	private Long memberId;

	@Schema(description = "권한", nullable = true , defaultValue = "USER")
	@Enumerated(EnumType.STRING)
	private RoleType role;                    //-- 권한

	@Schema(description = "생성자ID", nullable = true)
	private String creatorId; //--생성자

	@Schema(description = "생성일시", nullable = true)
	private LocalDateTime createdTime; //--최초_등록_시간

	@Schema(description = "수정자ID", nullable = true)
	private String modifierId; //--생성자

	@Schema(description = "최종수정시간", nullable = true)
	private LocalDateTime modifiedTime; //--최종_수정_시간
}
