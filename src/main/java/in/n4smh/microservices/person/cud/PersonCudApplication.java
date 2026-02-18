package in.n4smh.microservices.person.cud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "in.n4smh.microservices.person")
public class PersonCudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonCudApplication.class, args);
	}

}
