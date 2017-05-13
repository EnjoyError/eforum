package org.eforum.repository;

import org.eforum.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByName(String name);

	User findByEmail(String email);

	User findByNameAndPassword(String name, String password);
}