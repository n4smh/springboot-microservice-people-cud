package in.n4smh.microservices.person_cud.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import in.n4smh.microservices.person_cud.repo.PersonRoleRepo;
import in.n4smh.microservices.person_cud.repo.RoleRepo;
import in.n4smh.microservices.person_shared.dto.model.PersonRole;
import in.n4smh.microservices.person_shared.dto.model.Role;
import in.n4smh.microservices.person_shared.entity.PersonRoleEntity;
import in.n4smh.microservices.person_shared.entity.RoleEntity;
import in.n4smh.microservices.person_shared.mapper.IRoleMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonRoleService implements IPersonRoleService {

	private RoleRepo roleRepo;
	private PersonRoleRepo personRoleRepo;
	private IRoleMapper roleMapper;

	public PersonRoleService(final RoleRepo roleRepo, final PersonRoleRepo personRoleRepo,
			final IRoleMapper roleMapper) {
		this.roleRepo = roleRepo;
		this.personRoleRepo = personRoleRepo;
		this.roleMapper = roleMapper;
	}

	// TODO: instead of internal fetch method use feign client to call
	// app-people-query service to fetch the details
	// @Override
	public PersonRole getPersonRoles(String id) throws Exception {

		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		List<RoleEntity> rolesEntity = roleRepo.findByPersonId(id);

		List<Role> roles = rolesEntity.stream().map(roleEntity -> roleMapper.toPersonRole(roleEntity)).toList();

		return PersonRole.builder().personId(id).roles(roles).build();
	}

	@Override
	public List<String> addPersonRoles(String id, List<String> roleIds) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		List<PersonRoleEntity> personRoleEntities = roleIds.stream()
				.map(roleId -> PersonRoleEntity.builder().personId(id).roleId(roleId).build()).toList();

		personRoleRepo.saveAll(personRoleEntities);

		log.debug(personRoleEntities.toString());

		return roleIds;
	}

	@Override
	public List<String> updatePersonRoles(String id, List<String> roleIds) throws Exception {

		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		PersonRole personRoleInDb = getPersonRoles(id);

		List<String> incomingRoles = roleIds;
		List<String> dbRoles = personRoleInDb.getRoles().stream().map(r -> r.getId()).toList();

		List<String> rolesToBeInsert = incomingRoles.stream().filter(r -> !dbRoles.contains(r)).toList();
		List<String> rolesToBeDeleted = dbRoles.stream().filter(r -> !incomingRoles.contains(r)).toList();

		addPersonRoles(id, rolesToBeInsert);
		deletePersonRoles(id, rolesToBeDeleted);

		return roleIds;
	}

	@Override
	public List<String> deletePersonRoles(String id, List<String> roleIds) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		if (CollectionUtils.isEmpty(roleIds)) {
			deletePersonRoles(id);

		} else {
			roleIds.stream().forEach(roleId -> personRoleRepo.deletePersonRole(id, roleId));

		}

		return roleIds;
	}

	@Override
	public boolean deletePersonRoles(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		personRoleRepo.deletePersonRoles(id);

		return true;
	}

}
