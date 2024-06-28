package com.smarthome.platform.upc.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration class.
 * This class is responsible for configuring the OpenAPI documentation for the application.

 */

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI smartHomeManagementPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("SmartHomeManagement - Exercise 1 - UPC")
                        .description("Smart Home Management Platform application RESTful API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Smart Home Management Platform wiki Documentation")
                        .url("https://smart-home-management-platform.wiki.github.io/docs"));
        return openApi;
    }
}
