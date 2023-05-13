/**
* @packageName   : com.ldaniel.config
* @fileName      : OpenApiConfig.java
* @author        : 이병관
* @date          : 2023.03.27
* @description   :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.03.27        이병관       최초 생성
*/
package com.caremoa.member.app.config;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
* @packageName    : com.caremoa.member.app.config
* @fileName       : OpenApiConfig.java
* @author         : 이병관
* @date           : 2023.05.07
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.07        이병관       최초 생성
*/
@Configuration
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class OpenApiConfig {
	// https://codingnconcepts.com/spring-boot/configure-springdoc-openapi/
	private static final String BEARER_FORMAT = "JWT";
	private static final String SCHEME = "Bearer";
	private static final String SECURITY_SCHEME_NAME = "Security Scheme";

	@Value("${api.info.title: api.info.title}")
	private String title;

	@Value("${api.info.description: api.info.description}")
	private String description;

	@Value("${api.info.version: api.info.version}")
	private String version;

	@Value("${api.info.term-of-service: api.info.terms-of-service}")
	private String termOfService;

	@Value("${api.info.contact.name: api.info.contact.name}")
	private String contactName;

	@Value("${api.info.contact.email: api.info.contact.email}")
	private String contactEmail;

	@Value("${api.info.contact.url: api.info.contact.url}")
	private String contactUrl;

	@Value("${api.info.license.name: api.info.license.name}")
	private String licenseName;

	@Value("${api.info.license.url: api.info.license.url}")
	private String licenseUrl;

	/*
	 * Swagger 화면의 정보를 설정한다.
	 * 
	 * @name_ko Swagger 화면의 정보 설정
	 * 
	 * OpenApiConfig.api()
	 * 
	 * @return OpenAPI 설정 api 정보
	 */

	@Bean
	public OpenAPI api() {
		return new OpenAPI().schemaRequirement(SECURITY_SCHEME_NAME, getSecurityScheme())
				.security(getSecurityRequirement()).info(info());
	}

	/*
	 * Swagger 화면의 전체 Control Group을 설정한다.
	 * 
	 * @name_ko Swagger 화면의 Group을 설정(전체 Control)
	 * 
	 * OpenApiConfig.apiAll()
	 * 
	 * @return GroupedOpenApi 설정 정보
	 */
	@Bean
	public GroupedOpenApi apiAll() {
		return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
	}

	/*
	 * Swagger 공통 Control 화면의 Group을 설정한다.
	 * 
	 * @name_ko Swagger 화면의 Group을 설정(버전이 없는 공통 Control)
	 * 
	 * OpenApiConfig.apiNoVersion()
	 * 
	 * @return GroupedOpenApi 설정 정보
	 */

	@Bean
	public GroupedOpenApi apiNoVersion() {
		return GroupedOpenApi.builder().group("controller").pathsToExclude("/api/**")
				.packagesToScan("com.caremoa.member.controller").build();
	}

	/*
	 * Swagger 화면의 Sample Control Group을 설정한다.
	 * 
	 * @name_ko Swagger 화면의 Group을 설정(Re Control)
	 * 
	 * OpenApiConfig.apiSample()
	 * 
	 * @return GroupedOpenApi 설정 정보
	 */

	@Bean
	public GroupedOpenApi apiRest() {
		return GroupedOpenApi.builder().group("RestApi").pathsToMatch("/api/**").build();
	}

	/*
	 * Swagger 화면의 기본정보를 설정한다.
	 * 
	 * @name_ko Swagger 화면의 기본정보 설정
	 * 
	 * OpenApiConfig.info()
	 * 
	 * @return OpenAPI 기본설정 정보
	 */
	private Info info() {
		return new Info().title(title).description(description).version(version)
				.contact(new Contact().name(contactName).email(contactEmail).url(contactUrl))
				.license(new License().name(licenseName).url(licenseUrl));
	}

	/*
	 * Swagger 화면의 Security정보를 설정한다.
	 * 
	 * @name_ko Swagger 화면의 Security정보 설정
	 * 
	 * OpenApiConfig.getSecurityRequirement()
	 * 
	 * @return OpenAPI Security 설정 정보
	 */
	private List<SecurityRequirement> getSecurityRequirement() {
		SecurityRequirement securityRequirement = new SecurityRequirement();
		securityRequirement.addList(SECURITY_SCHEME_NAME);
		return List.of(securityRequirement);
	}

	/*
	 * Swagger 화면의 Security정보를 설정한다.
	 * 
	 * @name_ko Swagger 화면의 Json Web Token Security정보 설정
	 * 
	 * OpenApiConfig.getSecurityScheme()
	 * 
	 * @return OpenAPI Json Web Token Security정보
	 */
	private SecurityScheme getSecurityScheme() {
		SecurityScheme securityScheme = new SecurityScheme();
		securityScheme.bearerFormat(BEARER_FORMAT);
		securityScheme.type(SecurityScheme.Type.HTTP);
		securityScheme.in(SecurityScheme.In.HEADER);
		securityScheme.scheme(SCHEME);
		return securityScheme;
	}
}
