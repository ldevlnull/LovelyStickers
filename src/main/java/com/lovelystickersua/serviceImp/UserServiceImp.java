package com.lovelystickersua.serviceImp;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lovelystickersua.DTO.DTO_UtilMapper;
import com.lovelystickersua.DTO.UserDTO;
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

	@Transactional
	public User userFetch(long ID) {
		return uRepository.fetchUser(ID);
	}

	@Override
	public void saveImage(Principal principal, MultipartFile multipartFile) {
		User user = uRepository.findOne(Long.parseLong(principal.getName()));
		if (!multipartFile.getOriginalFilename().isEmpty()) {
			String path = System.getProperty("catalina.home")+"/resources/user_icon/"
					+ user.getUsername() + "/" + multipartFile.getOriginalFilename();
			user.setPathImage("resources/user_icon/" + user.getUsername() + "/" + multipartFile.getOriginalFilename());
			try {
				File folder = new File(System.getProperty("catalina.home")+"/resources/user_icon/"+user.getUsername()+"/");
				if(folder.exists()) {
					FileUtils.cleanDirectory(folder);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			File file = new File(path);
			file.mkdirs();
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			uRepository.save(user);
		}
	}

	@Override
	public void activateUser(long ID) {
		User user = uRepository.findOne(ID);
		user.setRole(Role.ROLE_USER);
		uRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return uRepository.findByUsername(username);
	}

	@Override
	public List<UserDTO> findAllUserDTOs() {
		return DTO_UtilMapper.listUserToUserDTO(uRepository.findAll());
	}
	

}
