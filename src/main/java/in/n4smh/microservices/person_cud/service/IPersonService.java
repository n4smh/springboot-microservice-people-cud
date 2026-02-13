package in.n4smh.microservices.person_cud.service;

import in.n4smh.microservices.person_shared.dto.model.Person;

public interface IPersonService {

	String addPerson(Person person) throws Exception;

	String updatePerson(String id, Person person) throws Exception;

	String deletePerson(String id) throws Exception;

}
