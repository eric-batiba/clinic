package com.btb.sante.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "Clinic Management OpenAPI spec",
        description = "Generate and export OpenAPI spec according to the Batiba eric OutputStream ",
        version = "1.0",
         contact = @Contact(
                 name = "Batiba eric",
                 email = "nguefbatiba@gmail.com")),
        servers = {
            @Server(url = "http://localhost:8080/api/v1", description = "LOCAL ENV"),
            @Server(url = "http://localhost:5867", description = "DEV ENV")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    bearerFormat = "JWT",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
