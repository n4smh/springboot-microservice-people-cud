package in.n4smh.microservices.person_cud.shared;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ApiResponseBody<T> {

	private String traceId;

	private T data;

	private PageMeta meta;

}
