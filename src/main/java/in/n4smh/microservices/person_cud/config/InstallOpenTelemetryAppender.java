package in.n4smh.microservices.person_cud.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;

@Configuration
class InstallOpenTelemetryAppender implements InitializingBean {

	/***
	 * OpenTelemetryAppender to know which OpenTelemetry API instance to use
	 */

	private final OpenTelemetry openTelemetry;

	InstallOpenTelemetryAppender(OpenTelemetry openTelemetry) {
		this.openTelemetry = openTelemetry;
	}

	@Override
	public void afterPropertiesSet() {
		OpenTelemetryAppender.install(this.openTelemetry);
	}
}
