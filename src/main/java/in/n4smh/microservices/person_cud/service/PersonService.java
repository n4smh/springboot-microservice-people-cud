package in.n4smh.microservices.person_cud.service;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import in.n4smh.microservices.person_cud.repo.PersonRepo;
import in.n4smh.microservices.person_shared.dto.model.Person;
import in.n4smh.microservices.person_shared.entity.PersonEntity;
import in.n4smh.microservices.person_shared.mapper.IPersonMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService implements IPersonService {

	private PersonRepo personRepo;
	private IPersonRoleService personRoleService;
	private IPersonMapper personMapper;

	public PersonService(final PersonRepo personRepo, final IPersonRoleService personRoleService,
			final IPersonMapper personMapper) {
		this.personRepo = personRepo;
		this.personRoleService = personRoleService;
		this.personMapper = personMapper;
	}

	@Override
	public String addPerson(Person person) throws Exception {

		if (Objects.isNull(person)) {
			throw new Exception("Invalid Person");
		}

		PersonEntity personEntity = personMapper.toPersonEntity(person);

		// To ensure its a insert
		personEntity.setId(null);
		personEntity.setIsActive(1);

		PersonEntity personEntityResponse = personRepo.save(personEntity);

		return personEntityResponse.getId();
	}

	@Override
	public String updatePerson(String id, Person person) throws Exception {
		if (Objects.isNull(person)) {
			throw new Exception("Invalid Person");
		}

		if (StringUtils.isBlank(id) || !personRepo.existsById(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		person.setId(id);

		PersonEntity personEntity = personRepo.save(personMapper.toPersonEntity(person));

		return personEntity.getId();
	}

	@Override
	public String deletePerson(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		if (!personRoleService.deletePersonRoles(id)) {
			throw new Exception(String.format("Failed to delete roles for person: %s", id));
		}

		personRepo.deleteById(id);

		return id;
	}

}
