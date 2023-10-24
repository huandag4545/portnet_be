package com.project.portnet_be.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 서버가 응답을 할때 json을 자바스크립트에서 처리할 수 있게 할지 설정하는것
        config.addAllowedOrigin("*"); //모든 IP 응답을 허용하겟다
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용
        config.addAllowedMethod("*"); // 모든 메서드의 요청을 허용

        source.registerCorsConfiguration("/api/**", config); //해당 경로에 들어오는 모든것은 다!

        return new CorsFilter(source);
    }
}
