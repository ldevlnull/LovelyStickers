package com.lovelystickersua.service;

import java.security.Principal;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.lovelystickersua.DTO.UserDTO;
import com.lovelystickersua.POJO.User;

public interface UserService {

	void save(User user);
	User findOne(long ID);
	List<User> findAll();
	void delete(long ID);
	User userFetch(long ID);
    void saveImage(Principal principal, MultipartFile multipartFile);
    User findByUsername(String username);
	User findByEmail(String email);
	void activateUser(long ID);
	List<UserDTO> findAllUserDTOs();
}
