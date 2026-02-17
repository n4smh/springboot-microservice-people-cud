package in.n4smh.microservices.person.cud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import in.n4smh.microservices.person.cud.config.TraceIdFilter;
import in.n4smh.microservices.person.cud.service.IPersonService;
import in.n4smh.microservices.person.cud.shared.ApiResponseBody;
import in.n4smh.microservices.person.shared.dto.model.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Person operations" )
public class PersonController {

	private IPersonService personService;
	private TraceIdFilter traceIdFilter;

	public PersonController(final IPersonService personService, final TraceIdFilter traceIdFilter) {
		this.personService = personService;
		this.traceIdFilter = traceIdFilter;
	}

	@PostMapping
	public ResponseEntity<ApiResponseBody<String>> addPerson(@RequestBody Person person) throws Exception {

		String personId = personService.addPerson(person);

		return ResponseEntity
				.ok(ApiResponseBody.<String>builder().traceId(traceIdFilter.getTraceId()).data(personId).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseBody<String>> modifyPerson(@PathVariable String id, @RequestBody Person person)
			throws Exception {

		String personId = personService.updatePerson(id, person);

		return ResponseEntity
				.ok(ApiResponseBody.<String>builder().traceId(traceIdFilter.getTraceId()).data(personId).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseBody<String>> modifyPerson(@PathVariable String id) throws Exception {

		String personId = personService.deletePerson(id);

		return ResponseEntity
				.ok(ApiResponseBody.<String>builder().traceId(traceIdFilter.getTraceId()).data(personId).build());
	}

}
