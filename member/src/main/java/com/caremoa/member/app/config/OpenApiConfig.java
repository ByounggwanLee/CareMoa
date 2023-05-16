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
* @description    : SpringDoc OpenAPI 설정을 위한 클래스
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

    /**
     * @methodName    : api
     * @date          : 2023.05.14
     * @description   : Swagger È­¸éÀÇ Á¤º¸¸¦ ¼³Á¤ÇÑ´Ù.
     * @return
    */
    @Bean
    OpenAPI api() {
		return new OpenAPI().schemaRequirement(SECURITY_SCHEME_NAME, getSecurityScheme())
				.security(getSecurityRequirement()).info(info());
	}

    /**
     * @methodName    : apiAll
     * @date          : 2023.05.14
     * @description   : Swagger 
     * @return
    */
    @Bean
    GroupedOpenApi apiAll() {
		return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
	}

    /**
     * @methodName    : apiNoVersion
     * @date          : 2023.05.14
     * @description   : Swagger
     * @return
    */
    @Bean
    GroupedOpenApi apiNoVersion() {
		return GroupedOpenApi.builder().group("controller").pathsToExclude("/api/**")
				.packagesToScan("com.caremoa.member.controller").build();
	}

    /**
     * @methodName    : apiRest
     * @date          : 2023.05.14
     * @description   : Swagger 
     * @return
    */
    @Bean
    GroupedOpenApi apiRest() {
		return GroupedOpenApi.builder().group("RestApi").pathsToMatch("/api/**").build();
	}

	/**
	 * @methodName    : info
	 * @date          : 2023.05.14
	 * @description   : Swagger 화면의 기본정보를 설정한다.
	 * @return
	*/
	private Info info() {
		return new Info().title(title).description(description).version(version)
				.contact(new Contact().name(contactName).email(contactEmail).url(contactUrl))
				.license(new License().name(licenseName).url(licenseUrl));
	}

	/**
	 * @methodName    : getSecurityRequirement
	 * @date          : 2023.05.14
	 * @description   : Swagger 화면의 Security정보를 설정한다.
	 * @return
	*/
	private List<SecurityRequirement> getSecurityRequirement() {
		SecurityRequirement securityRequirement = new SecurityRequirement();
		securityRequirement.addList(SECURITY_SCHEME_NAME);
		return List.of(securityRequirement);
	}

	/**
	 * @methodName    : getSecurityScheme
	 * @date          : 2023.05.14
	 * @description   : Swagger 화면의 Security정보를 설정한다.
	 * @return
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
