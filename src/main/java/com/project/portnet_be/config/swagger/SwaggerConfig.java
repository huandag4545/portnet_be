package com.project.portnet_be.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig{


    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
    @Bean
    public GroupedOpenApi chatOpenApi(){
        String[] path = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("API 기동 v1")
                .pathsToMatch(path)
                .build();
    }

    private Info apiInfo(){
        return new Info()
                .title("PortNet API")
                .description("Portnet API Docs")
                .version("1.0.0");
    }



}
