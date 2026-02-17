package in.n4smh.microservices.person.cud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain springSecurity(HttpSecurity http) {
		return http
				.csrf(CsrfConfigurer::disable)
				.httpBasic(Customizer.withDefaults())
				.authorizeHttpRequests(request -> {
					request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**").permitAll()
					.anyRequest().authenticated();
				})
				.build();
	}
}
