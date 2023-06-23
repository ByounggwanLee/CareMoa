package com.caremoa.helper.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @packageName    : com.caremoa.helper.app.config
* @fileName       : WebConfig.java
* @author         : 이병관
* @date           : 2023.06.07
* @description    : https://letsmakemyselfprogrammer.tistory.com/89
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.06.07        이병관       최초 생성
*/
@Configuration
//@EnableWebMvc // -- LocalDateTime이 깨져서 제외함
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(3000);
    }
}