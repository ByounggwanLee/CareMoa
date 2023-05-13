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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.member.domain.listener.MemberListener;
import com.caremoa.member.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners({ MemberListener.class, AuditingEntityListener.class })
@Table(name = "MEMBER")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	// @Schema(description = "", nullable = true , defaultValue = "" , example = "", allowableValues = {"", ""})
	private Long id; // -- ID

	@Column(name = "USER_ID", nullable = false, length = 30)
	@Schema(description = "사용자ID", nullable = true, defaultValue = "lbg1225")
	private String userId; // -- 사용자ID

	@Schema(description = "패스워드", nullable = false)
	@Column(name = "PASSWORD", nullable = true, length = 30)
	private String password; // -- 암호

	@Column(name = "NAME", length = 50)
	@Schema(description = "이름", nullable = true, defaultValue = "이병관")
	private String name; // -- 이름

	@Column(name = "NICKNAME", nullable = true, length = 40)
	@Schema(description = "별명", nullable = true, defaultValue = "Daniel")
	private String nickname; // -- 별명

	private Address address; // -- 도/시, 군/구, 읍/동

	/*
	 * @Column(name = "ROLE", nullable = true, length = 20)
	 * @Schema(description = "권한", nullable = true , defaultValue = "USER")
	 * @Enumerated(EnumType.STRING) private RoleType role; //-- 권한
	 */
	//@OneToMany(mappedBy = "member")
	//private List<MemberRole> memberRoles = new ArrayList<>();

	@Column(name = "USER_SCORE", nullable = true, length = 20)
	@Schema(description = "사용자별점", nullable = true, defaultValue = "50")
	private Integer userScore; // -- 서비스 사용자 점수

	@Column(name = "STATUS", nullable = true, length = 20)
	@Enumerated(EnumType.STRING)
	@Schema(description = "상태", nullable = true, defaultValue = "ENABLED", example = "")
	private MemberStatusType status; // -- 계정상태

	@Column(name = "CREATED_TIME", nullable = true, updatable = false)
	@CreatedDate
	@Schema(description = "생성일시", nullable = true)
	private LocalDateTime createdTime; // --최초_등록_시간

	@Column(name = "MODIFIED_TIME", nullable = true, insertable = false)
	@LastModifiedDate
	@Schema(description = "최종수정시간", nullable = true)
	private LocalDateTime modifiedTime; // --최종_수정_시간
}
