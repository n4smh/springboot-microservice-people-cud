package in.n4smh.microservices.person.cud.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TraceIdFilter extends OncePerRequestFilter {

	/***
	 * Placing trace ID to response header
	 * 
	 */

	private final Tracer tracer;

	TraceIdFilter(Tracer tracer) {
		this.tracer = tracer;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String traceId = getTraceId();
		if (traceId != null) {
			response.setHeader("X-Trace-Id", traceId);
		}
		filterChain.doFilter(request, response);
	}

	public @Nullable String getTraceId() {
		TraceContext context = this.tracer.currentTraceContext().context();
		return context != null ? context.traceId() : null;
	}

}
