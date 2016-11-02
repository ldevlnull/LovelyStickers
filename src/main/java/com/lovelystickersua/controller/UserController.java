package com.lovelystickersua.controller;

import java.security.Principal;

import com.lovelystickersua.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.MailService;
import com.lovelystickersua.service.UserService;

@Controller
public class UserController{

	private final static String PAGE_LOGIN = "loginpage";
	private final static String PAGE_REGISTER = "register";
	private final static String BACK = "redirect:/";
	private final static String PAGE_PROFILE = "profile";

	@Autowired
	private UserService uService;
	
	@Autowired
	private MailService mailSender;

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String login() {
		return PAGE_LOGIN;
	}

	@RequestMapping(value = { "/home", "/logout" }, method = RequestMethod.POST)
	public String logout() {
		return BACK;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return PAGE_REGISTER;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {
		String ref_link = "";
		for(int i = 0; i < 15; i++){
			ref_link += (int)(Math.random()*10);
		}
		user.setActivateLink(ref_link);
		user.setRole(Role.ROLE_UNACTIVATED_USER);
		String message = "Thank you for registration. To activate your account follow the link http://127.0.0.1:8080/activate/"+user.getID()+"/"+ref_link;
		mailSender.sendMessage("Registration", user.getEmail(), message);
		uService.save(user);
		return BACK;
	}
	@RequestMapping(value = "/activate/{id}/{activateLink}")
	public String activateUser(@PathVariable int id, @PathVariable String activateLink){
		User user = uService.userFetch(id);
		if(activateLink.equalsIgnoreCase(user.getActivateLink())){
			user.setRole(Role.ROLE_USER);
		}
		return BACK;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		model.addAttribute("user", uService.userFetch(Long.parseLong(principal.getName())));
		return PAGE_PROFILE;
	}

	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	public String saveImage(Principal principal, @RequestParam MultipartFile image){
		if(image != null){
			uService.saveImage(principal, image);
		}
		return "redirect:/profile";	
	}
	

}
