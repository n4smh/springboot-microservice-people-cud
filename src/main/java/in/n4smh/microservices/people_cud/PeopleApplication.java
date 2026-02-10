package in.n4smh.microservices.people_cud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices")
public class PeopleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleApplication.class, args);
	}

	@Bean
	OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("People CUD API")
				.description("APIs to create, update & delete people related details").version("v0.0.1"));
	}

}
