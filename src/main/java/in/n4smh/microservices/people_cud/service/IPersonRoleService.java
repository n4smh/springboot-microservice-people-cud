package in.n4smh.microservices.people_cud.service;

import java.util.List;

public interface IPersonRoleService {

	List<String> addPersonRoles(String id, List<String> roles) throws Exception;

	List<String> updatePersonRoles(String id, List<String> roles) throws Exception;

	List<String> deletePersonRoles(String id, List<String> roles) throws Exception;

	boolean deletePersonRoles(String id) throws Exception;

}
