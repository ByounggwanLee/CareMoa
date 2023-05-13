package com.caremoa.member.domain.model.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED) // AccessLevel.PUBLIC
public class Address {
	@Column(name = "ADDRESS_STATE", nullable = true, length = 20)
	@Schema(description = "도/시", nullable = true , defaultValue = "서울")
	String addressState;
	@Column(name = "ADDRESS_CITY", nullable = true, length = 20)
	@Schema(description = "군/구", nullable = true , defaultValue = "강서")
	String addressCity;
	@Column(name = "ADDRESS_STREET", nullable = true, length = 20)
	@Schema(description = "읍/동", nullable = true , defaultValue = "염창")
	String addressStreet;
}
