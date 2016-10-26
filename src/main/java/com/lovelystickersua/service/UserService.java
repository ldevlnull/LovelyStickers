package com.lovelystickersua.service;

import java.util.List;

import com.lovelystickersua.entity.User;

public interface UserService {

	void save(User user);
	User findOne(long id);
	List<User> findAll();
	void delete(long id);
}
