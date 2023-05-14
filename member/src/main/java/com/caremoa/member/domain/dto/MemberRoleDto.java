package com.caremoa.member.domain.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.member.domain.listener.MemberRoleListener;
import com.caremoa.member.domain.model.Member;
import com.caremoa.member.domain.model.MemberRole;
import com.caremoa.member.domain.model.MemberStatusType;
import com.caremoa.member.domain.model.RoleType;
import com.caremoa.member.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

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
	
	public MemberRole toModel() {
		return MemberRole.builder()
				.id(id)
				.memberId(memberId)
				.role(role)
				.build();
	}
	
	public static MemberRoleDto toDto(final MemberRole memberRole) {
		return MemberRoleDto.builder()
				.id(memberRole.getId())
				.memberId(memberRole.getMemberId())
				.role(memberRole.getRole())
				.creatorId(memberRole.getCreatorId())
				.createdTime(memberRole.getCreatedTime())
				.modifierId(memberRole.getModifierId())
				.modifiedTime(memberRole.getModifiedTime())
			    .build();
	}
}
