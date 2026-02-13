package in.n4smh.microservices.person_cud.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.DeserializationFeature;

@Configuration
public class JsonFormaterConfig {
	
	@Bean
	JsonMapperBuilderCustomizer customizer() {
		return builder -> builder.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
	}
}
