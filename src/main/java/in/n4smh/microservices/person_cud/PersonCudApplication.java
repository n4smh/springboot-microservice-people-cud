package in.n4smh.microservices.person_cud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices")
public class PersonCudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonCudApplication.class, args);
	}

	@Bean
	OpenAPI springShopOpenAPI(BuildProperties buildProperties) {
		return new OpenAPI().info(new Info().title(buildProperties.getName())
				.description(buildProperties.get("description")).version(buildProperties.getVersion()));
	}

}
