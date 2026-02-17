package in.n4smh.microservices.person.cud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

	private String SWAGGER_CENTRAL_SERVER_URI = "downstream.swagger-central.server.uri";

	private Environment env;

	CrosConfig(Environment env) {
		this.env = env;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(env.getProperty(SWAGGER_CENTRAL_SERVER_URI));
	}

}
