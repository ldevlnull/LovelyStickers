package com.lovelystickersua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovelystickersua.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
