package com.caremoa.helper.domain.dto;

import java.time.LocalTime;

import javax.persistence.Column;

import com.caremoa.helper.domain.model.HelperJob;
import com.caremoa.helper.domain.model.TakerLevelType;
import com.caremoa.helper.domain.model.WageAmountType;
import com.caremoa.helper.domain.model.WageType;
import com.caremoa.helper.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* @packageName    : com.caremoa.helper.domain.dto
* @fileName       : HelperJobDto.java
* @author         : 이병관
* @date           : 2023.05.24
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.24        이병관       최초 생성
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelperJobDto {
	@Schema(description = "ID", nullable = true)
	Long id;

	@Schema(description = "도우미ID", nullable = false, defaultValue = "1")
	Long helperId;

	@Schema(description = "도우미업무", nullable = true, defaultValue = "10")
	String jobType;

	@Schema(description = "도우미업무", nullable = true, defaultValue = "NEWBORN")
	TakerLevelType takerAge;

	@Schema(description = "근무선호지역1", nullable = true)
	Address jobArea1;

	@Schema(description = "근무선호지역2", nullable = true)
	Address jobArea2;

	@Schema(description = "근무선호지역3", nullable = true)
	Address jobArea3;

	@Schema(description = "근무가능요일", nullable = true, defaultValue = "11111000")
	String workingDays; // 근무가능요일은 Sting으로 20글자로 구성(1~8만 우선사용) 월(1)~일(7)휴일(8) : 11111000 (월~금 가능)

	@Schema(description = "근무시작가능시간", nullable = true, defaultValue = "09:00")
	LocalTime workStartTime;

	@Column(name = "WORK_START_TIME", nullable = true)
	@Schema(description = "근무종료가능시간", nullable = true, defaultValue = "18:00")
	LocalTime workEndTime;

	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY")
	WageType wageType;

	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY02")
	WageAmountType wageAmount;

	@Schema(description = "간단자기소개", nullable = true, defaultValue = "HOURLY02")
	String oneLineMe;

	@Schema(description = "자기소개", nullable = true, defaultValue = "HOURLY02")
	String aboutMe;
	
	@Schema(description = "활성화여부", nullable = true, defaultValue = "true")
	Boolean activeFlag;

	public HelperJob toModel() {
		return HelperJob.builder().id(id).helperId(helperId).jobType(jobType).takerAge(takerAge).jobArea1(jobArea1)
				.jobArea2(jobArea2).jobArea3(jobArea3).workingDays(workingDays).workStartTime(workStartTime)
				.workEndTime(workEndTime).wageType(wageType).wageAmount(wageAmount).oneLineMe(oneLineMe)
				.aboutMe(aboutMe).activeFlag(activeFlag).build();
	}

	public static HelperJobDto toDto(final HelperJob helperJob) {
		return HelperJobDto.builder().id(helperJob.getId()).helperId(helperJob.getHelperId())
				.jobType(helperJob.getJobType()).takerAge(helperJob.getTakerAge()).jobArea1(helperJob.getJobArea1())
				.jobArea2(helperJob.getJobArea2()).jobArea3(helperJob.getJobArea3())
				.workingDays(helperJob.getWorkingDays()).workStartTime(helperJob.getWorkStartTime())
				.workEndTime(helperJob.getWorkEndTime()).wageType(helperJob.getWageType())
				.wageAmount(helperJob.getWageAmount()).oneLineMe(helperJob.getOneLineMe())
				.aboutMe(helperJob.getAboutMe()).activeFlag(helperJob.getActiveFlag()).build();
	}
}