package com.lovelystickersua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lovelystickersua.POJO.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	User findByEmail(String email);

	@Query(value = "select u from User u left join fetch u.products c where u.ID =:ID")
	User fetchUser(@Param("ID") long ID);
}
