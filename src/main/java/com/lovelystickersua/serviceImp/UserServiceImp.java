package com.lovelystickersua.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lovelystickersua.entity.Role;
import com.lovelystickersua.entity.User;
import com.lovelystickersua.repository.UserRepository;
import com.lovelystickersua.service.UserService;

@Service("userDetailsService")
public class UserServiceImp implements UserService, UserDetailsService {

	@Autowired
	UserRepository uRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return uRepository.findByUsername(username);
	}

	public void save(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		uRepository.save(user);
	}

	public User findOne(long id) {
		return uRepository.findOne(id);
	}

	public List<User> findAll() {
		return uRepository.findAll();
	}

	public void delete(long id) {
		uRepository.delete(id);
	}

}
