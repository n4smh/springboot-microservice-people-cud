package in.n4smh.microservices.person_cud.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import in.n4smh.microservices.person_cud.config.TraceIdFilter;
import in.n4smh.microservices.person_cud.service.IPersonRoleService;
import in.n4smh.microservices.person_cud.shared.ApiResponseBody;
import in.n4smh.microservices.person_shared.dto.model.PersonRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/person")
@Tag(name = "Person Role", description = "Person Role operations" )
public class PersonRoleController {

	private IPersonRoleService personRoleService;
	private TraceIdFilter traceIdFilter;

	public PersonRoleController(final IPersonRoleService personRoleService, final TraceIdFilter traceIdFilter) {
		this.personRoleService = personRoleService;
		this.traceIdFilter = traceIdFilter;
	}

	@PostMapping("/{id}/roles")
	public ResponseEntity<ApiResponseBody<List<String>>> addPersonRoles(@PathVariable String id,
			@RequestBody PersonRole personRoles) throws Exception {

		List<String> roleIds = personRoleService.addPersonRoles(id,
				personRoles.getRoles().stream().map(r -> r.getId()).toList());

		return ResponseEntity
				.ok(ApiResponseBody.<List<String>>builder().traceId(traceIdFilter.getTraceId()).data(roleIds).build());
	}

	@PostMapping("/{id}/roles/{roleId}")
	public ResponseEntity<ApiResponseBody<List<String>>> addPersonRoles(@PathVariable String id,
			@PathVariable String roleId) throws Exception {

		List<String> roleIds = personRoleService.addPersonRoles(id, Arrays.asList(roleId));

		return ResponseEntity
				.ok(ApiResponseBody.<List<String>>builder().traceId(traceIdFilter.getTraceId()).data(roleIds).build());
	}

	@PutMapping("/{id}/roles")
	public ResponseEntity<ApiResponseBody<List<String>>> updatePersonRoles(@PathVariable String id,
			@RequestBody PersonRole roles) throws Exception {

		List<String> roleIds = personRoleService.updatePersonRoles(id,
				roles.getRoles().stream().map(r -> r.getId()).toList());

		return ResponseEntity
				.ok(ApiResponseBody.<List<String>>builder().traceId(traceIdFilter.getTraceId()).data(roleIds).build());
	}

	@DeleteMapping("/{id}/roles")
	public ResponseEntity<ApiResponseBody<List<String>>> deletePersonRoles(@PathVariable String id,
			@RequestBody PersonRole roles) throws Exception {

		List<String> roleIds = personRoleService.deletePersonRoles(id,
				roles.getRoles().stream().map(r -> r.getId()).toList());

		return ResponseEntity
				.ok(ApiResponseBody.<List<String>>builder().traceId(traceIdFilter.getTraceId()).data(roleIds).build());
	}

	@DeleteMapping("/{id}/roles/{roleId}")
	public ResponseEntity<ApiResponseBody<List<String>>> deletePersonRoles(@PathVariable String id,
			@PathVariable String roleId) throws Exception {

		List<String> roleIds = personRoleService.deletePersonRoles(id, Arrays.asList(roleId));

		return ResponseEntity
				.ok(ApiResponseBody.<List<String>>builder().traceId(traceIdFilter.getTraceId()).data(roleIds).build());
	}
}
