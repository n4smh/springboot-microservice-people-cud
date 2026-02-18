package in.n4smh.microservices.person.cud.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.n4smh.microservices.person.parent_webmvc.config.TraceIdFilter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

	private TraceIdFilter traceIdFilter;

	public GlobalExceptionHandler(final TraceIdFilter traceIdFilter) {
		this.traceIdFilter = traceIdFilter;
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ProblemDetail> handleSql(Exception ex) {

		log.error(ex.getClass() + ": " + ex.getMessage());

		ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "DB error");
		pd.setProperty("traceId", traceIdFilter.getTraceId());

		return ResponseEntity.of(pd).build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGeneric(Exception ex) {

		log.error(ex.getClass() + ": " + ex.getMessage());

		ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Application error");
		pd.setProperty("traceId", traceIdFilter.getTraceId());

		return ResponseEntity.of(pd).build();
	}

}
