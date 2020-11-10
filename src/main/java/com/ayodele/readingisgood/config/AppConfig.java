package com.ayodele.readingisgood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableJpaAuditing
@Configuration
@EnableOpenApi
public class AppConfig {
}
