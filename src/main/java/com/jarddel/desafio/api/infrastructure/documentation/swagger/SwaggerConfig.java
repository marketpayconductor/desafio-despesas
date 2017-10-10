package com.jarddel.desafio.api.infrastructure.documentation.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITULO = "Desafio API";
    private static final String DESCRICAO = "Documentação da API para acesso aos endpoints do Desafio";
    private static final String VERSAO = "1.0";
    private static final String PACOTE_BASE = "com.jarddel.desafio.api.application";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage(PACOTE_BASE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(TITULO)
            .description(DESCRICAO)
            .version(VERSAO)
            .build();
    }
}
