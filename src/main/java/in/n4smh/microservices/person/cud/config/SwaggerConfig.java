package in.n4smh.microservices.person.cud.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI(BuildProperties buildProperties) {
		final String securitySchemeName = "Basic auth";

		return new OpenAPI()
				.info(new Info()
						.title(buildProperties.getName())
						.description(buildProperties.get("description"))
						.version(buildProperties.getVersion()))
				.addSecurityItem(new SecurityRequirement()
						.addList(securitySchemeName))
				.components(new Components()
						.addSecuritySchemes(securitySchemeName, new SecurityScheme()
								.name(securitySchemeName)
								.type(SecurityScheme.Type.HTTP)
								.scheme("basic")));
	}

	@Bean
	GroupedOpenApi groupedOpenApi(BuildProperties buildProperties) {
		return GroupedOpenApi.builder()
				.group(buildProperties.getArtifact())
				.pathsToMatch("/**")
				.build();
	}
}
