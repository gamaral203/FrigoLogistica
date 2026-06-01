package com.frigocezar.logistica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpeanApiConfig {

    @Bean

    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API do frigologística")
                                .description("API do Frigologistica")
                                .version("1.0.0")
                );
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
