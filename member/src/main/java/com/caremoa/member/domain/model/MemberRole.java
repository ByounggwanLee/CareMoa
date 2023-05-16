package com.caremoa.member.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.member.domain.listener.MemberRoleListener;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.domain.model
* @fileName       : MemberRoleDto.java
* @author         : 이병관
* @date           : 2023.05.14
* @description    : MemberRoleDto Entity
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.14        이병관       최초 생성
*/
@Entity
@EntityListeners({AuditingEntityListener.class, MemberRoleListener.class})
@Table(name="MEMBERROLE") // uniqueConstraints = { @UniqueConstraint(columnNames = { "MEMBER_ID", "ROLE" }) })
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class MemberRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;


//	@ManyToOne // (fetch = FetchType.LAZY)
//	@JoinColumn(name = "MEMBER_ID")
//	private Member member;
	@Column(name = "MEMBER_ID", nullable = false)
	@Schema(description = "회원ID", nullable = false )
	private Long memberId;

	@Column(name = "ROLE", nullable = false, length = 20)
	@Schema(description = "권한", nullable = true , defaultValue = "USER")
	@Enumerated(EnumType.STRING)
	private RoleType role;                    //-- 권한

	@Column(name = "CREATOR_ID", nullable = true, updatable = false)
	@CreatedBy
    @Schema(description = "생성자ID", nullable = true)
	private String creatorId; //--생성자

	@Column(name = "CREATED_TIME", nullable = true, updatable = false)
    @CreatedDate
    @Schema(description = "생성일시", nullable = true)
	private LocalDateTime createdTime; //--최초_등록_시간

	@Column(name = "MODIFIER_ID", nullable = true, insertable = false)
	@LastModifiedBy
    @Schema(description = "수정자ID", nullable = true)
	private String modifierId; //--생성자

	@Column(name = "MODIFIED_TIME", nullable = true, insertable = false)
    @LastModifiedDate
    @Schema(description = "최종수정시간", nullable = true)
	private LocalDateTime modifiedTime; //--최종_수정_시간
}
