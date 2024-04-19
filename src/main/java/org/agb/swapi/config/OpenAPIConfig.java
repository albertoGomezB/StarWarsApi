package org.agb.swapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration class.
 */
@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Welcome to the SWAPI",
                description = "This is an API for the Star Wars universe \uD83D\uDC7D",
                version = "1.0.0",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "\uD83D\uDC08\u200D⬛ Github",
                        url = "https://github.com/albertoGomezB"
                )
        )
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Welcome to the SWAPI")
                .description("This is an API for the Star Wars universe.")
                .contact(new Contact().name("Alberto Gómez"))
                .version("1.0.0"));

    }
}
