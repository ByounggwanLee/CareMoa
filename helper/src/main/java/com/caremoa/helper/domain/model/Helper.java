package com.caremoa.helper.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.helper.domain.listener.HelperListener;
import com.caremoa.helper.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : HelperDto.java
* @author         : 이병관
* @date           : 2023.05.24
* @description    : 도우미정보
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.24        이병관       최초 생성
*/
@Entity
@EntityListeners({ HelperListener.class, AuditingEntityListener.class })
@Table(name = "HELPER")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class Helper {
	@Id
	@Column(name = "ID")
	@Schema(description = "고객ID", nullable = false, defaultValue = "1")
	Long id;
	
	@Column(name = "NAME", length = 50)
	@Schema(description = "이름", nullable = true, defaultValue = "이병관")
	private String name; // -- 이름

	@Column(name = "NICKNAME", nullable = true, length = 40)
	@Schema(description = "별명", nullable = true, defaultValue = "Daniel")
	private String nickname; // -- 별명

	private Address address; // -- 도/시, 군/구, 읍/동

	@Column(name = "BIRTHDAY", nullable = true)
	@Schema(description = "생년월일", nullable = true, defaultValue = "1968-12-25")
	LocalDate birthday;
	
	@Column(name = "HELPER_SCORE", nullable = true)
	@Schema(description = "도우미별점", nullable = true)
	Integer helperScore;
	
	@Column(name = "CAMCODER_AGREE", nullable = true)
	@Schema(description = "근무중영상촬영동의여부", nullable = true, defaultValue = "TRUE")
	Boolean camcoderAgree;
	
	@Column(name = "CAREER_PERIOD", nullable = true)
	@Schema(description = "경력기간(개월)", nullable = true, defaultValue = "0")
	Integer careerPeriod;
	
	@Column(name = "STATUS", nullable = true)
	@Schema(description = "상태", nullable = true, defaultValue = "WRITING")
	HelperStausType status;
}
