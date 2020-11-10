package com.ayodele.readingisgood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@EnableJpaAuditing
@Configuration
@EnableOpenApi
public class AppConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .forCodeGeneration(Boolean.TRUE)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build();
    }

}
