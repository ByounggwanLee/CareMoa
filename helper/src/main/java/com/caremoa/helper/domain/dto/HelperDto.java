package com.caremoa.helper.domain.dto;

import java.time.LocalDate;

import com.caremoa.helper.domain.model.Helper;
import com.caremoa.helper.domain.model.HelperStausType;
import com.caremoa.helper.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @packageName : com.caremoa.helper.domain.dto
 * @fileName : HelperDto.java
 * @author : 이병관
 * @date : 2023.05.24
 * @description : ===========================================================
 *              DATE AUTHOR NOTE
 *              -----------------------------------------------------------
 *              2023.05.24 이병관 최초 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelperDto {
	@Schema(description = "고객ID", nullable = false, defaultValue = "1")
	Long id;

	@Schema(description = "이름", nullable = true, defaultValue = "이병관")
	private String name; // -- 이름

	@Schema(description = "별명", nullable = true, defaultValue = "Daniel")
	private String nickname; // -- 별명

	private Address address; // -- 도/시, 군/구, 읍/동

	@Schema(description = "생년월일", nullable = true, defaultValue = "1968-12-25")
	LocalDate birthday;

	@Schema(description = "도우미별점", nullable = true)
	Integer helperScore;

	@Schema(description = "근무중영상촬영동의여부", nullable = true, defaultValue = "TRUE")
	Boolean camcoderAgree;

	@Schema(description = "경력기간(개월)", nullable = true, defaultValue = "0")
	Integer careerPeriod;

	@Schema(description = "상태", nullable = true, defaultValue = "WRITING")
	HelperStausType status;

	public Helper toModel() {
		return Helper.builder().id(id).name(name).nickname(nickname).address(address).birthday(birthday)
				.helperScore(helperScore).camcoderAgree(camcoderAgree).careerPeriod(careerPeriod).status(status)
				.build();
	}

	public static HelperDto toDto(final Helper helper) {
		return HelperDto.builder().id(helper.getId()).name(helper.getName()).nickname(helper.getNickname())
				.address(helper.getAddress()).birthday(helper.getBirthday()).helperScore(helper.getHelperScore())
				.camcoderAgree(helper.getCamcoderAgree()).careerPeriod(helper.getCareerPeriod())
				.status(helper.getStatus()).build();
	}
}
