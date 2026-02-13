package in.n4smh.microservices.person_cud.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.n4smh.microservices.person_shared.entity.PersonRoleEntity;

@Repository
public interface PersonRoleRepo extends CrudRepository<PersonRoleEntity, String> {

	@Modifying
	@Transactional
	@NativeQuery("DELETE FROM people_role WHERE person_id = :person_id AND role_id = :role_id")
	void deletePersonRole(@Param("person_id") String personId, @Param("role_id") String roleId);

	@Modifying
	@Transactional
	@NativeQuery("DELETE FROM people_role WHERE person_id = :person_id")
	void deletePersonRoles(@Param("person_id") String personId);
}
