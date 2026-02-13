package in.n4smh.microservices.person_cud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;

@Configuration(proxyBeanMethods = false)
public class ContextPropagationConfiguration {

	/***
	 * Spring Boot's auto-configuration looks for TaskDecorator beans and installs
	 * them into the AsyncTaskExecutor. With the ContextPropagatingTaskDecorator in
	 * place, the context is now transferred to new threads, fixing lost trace IDs
	 * in logs and lost spans.
	 * 
	 * Trace ID to be propagated even with @Async / AsyncTaskExecutor
	 * 
	 */

	@Bean
	ContextPropagatingTaskDecorator contextPropagatingTaskDecorator() {
		return new ContextPropagatingTaskDecorator();
	}

}
