package in.n4smh.microservices.person.cud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import in.n4smh.microservices.person.shared.entity.RoleEntity;

public interface RoleRepo extends CrudRepository<RoleEntity, String> {
	
	@NativeQuery("select id, name from role")
	List<RoleEntity> findAll();

	@NativeQuery("SELECT r.id, r.name FROM people_role pr JOIN role r ON pr.role_id = r.id WHERE pr.person_id = :person_id")
	List<RoleEntity> findByPersonId(@Param("person_id") String personId);
}
