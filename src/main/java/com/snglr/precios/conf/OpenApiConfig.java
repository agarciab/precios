package com.snglr.precios.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@SecurityScheme(
	    name = "oauth2",
	    scheme = "bearer",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT"
	)
public class OpenApiConfig {
	
    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.project.version}") String version) {
        return new OpenAPI()
                .info(new Info()
                    .title("Tarifa Service API")
                    .version(version)
                    .description("API REST para consultar tarifas basadas en fecha, producto y brand (cadena).")
                    .contact(new Contact().name("Àlex Garcia")
                                          .email("alex.garcia.bote@gmail.com")));
    }
}
