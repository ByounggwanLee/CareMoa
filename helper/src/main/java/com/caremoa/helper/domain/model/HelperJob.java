package com.caremoa.helper.domain.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.helper.domain.listener.HelperJobListener;
import com.caremoa.helper.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : HelperJobDto.java
* @author         : 이병관
* @date           : 2023.05.24
* @description    : 도우미 구직정보
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.24        이병관       최초 생성
*/
@Entity
@EntityListeners({ HelperJobListener.class, AuditingEntityListener.class })
@Table(name = "HELPER_JOB")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class HelperJob {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;
	
	@Column(name = "HELPER_ID", nullable = false)
	@Schema(description = "도움미ID", nullable = false, defaultValue = "1")
	Long helperId;
	
	@Column(name = "JOB_TYPE", nullable = true)
	@Schema(description = "도우미업무", nullable = true, defaultValue = "BABYSITER")
	HelperJobType jobType;
	
	@Column(name = "TAKE_AGE", nullable = true)
	@Schema(description = "도우미업무", nullable = true, defaultValue = "NEWBORN")
	TakerLevelType takerAge;
	
	@Schema(description = "근무선호지역1", nullable = true, defaultValue = "TRUE")
	Address jobArea1;
	
	@Schema(description = "근무선호지역2", nullable = true, defaultValue = "TRUE")
	Address jobArea2;
	
	@Schema(description = "근무선호지역3", nullable = true, defaultValue = "TRUE")
	Address jobArea3;
	
	@Column(name = "WORKING_DAYS", nullable = true, length = 20)
	@Schema(description = "근무가능요일", nullable = true, defaultValue = "11111000")
	String workingDays;     // 근무가능요일은 Sting으로	20글자로 구성(1~8만 우선사용) 월(1)~일(7)휴일(8) : 11111000 (월~금 가능)
	
	@Column(name = "WORK_START_TIME", nullable = true)
	@Schema(description = "근무시작가능시간", nullable = true, defaultValue = "09:00")
	LocalTime workStartTime;  
	
	@Column(name = "WORK_START_TIME", nullable = true)
	@Schema(description = "근무종료가능시간", nullable = true, defaultValue = "18:00")
	LocalTime workEndTime;
	
	@Column(name = "WAGE_TYPE", nullable = true)
	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY")
	WageType wageType;
	
	@Column(name = "WAGE_AMOUNT", nullable = true)
	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY02")
	WageAmountType wageAmount;
	
	@Column(name = "ONE_LINE_ME", nullable = true, length = 128)
	@Schema(description = "간단자기소개", nullable = true, defaultValue = "HOURLY02")
	String oneLineMe;
	
	@Column(name = "ABOUT_ME", nullable = true, length = 2048)
	@Schema(description = "자기소개", nullable = true, defaultValue = "HOURLY02")
	String aboutMe;
}
