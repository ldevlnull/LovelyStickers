package com.lovelystickersua.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.UserService;

@Controller
public class UserController {

	private final static String PAGE_LOGIN = "loginpage";
	private final static String PAGE_REGISTER = "register";
	private final static String BACK = "redirect:/";
	private final static String PAGE_PROFILE = "profile";
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value="/loginpage", method=RequestMethod.GET)
	public String login(){
		return PAGE_LOGIN;
	}
	
	@RequestMapping(value={"/home", "/logout"}, method=RequestMethod.POST)
	public String logout(){
		return BACK;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("user", new User());
		return PAGE_REGISTER;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user){
		uService.save(user);
		return BACK;
	}
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile(Principal principal, Model model){
		model.addAttribute("user", uService.userFetch(Long.parseLong(principal.getName())));
		return PAGE_PROFILE;
	}
	@RequestMapping(value="/saveImage", method=RequestMethod.POST)
	public String saveImage(@RequestParam("image") MultipartFile image, Principal principal){
		User user = uService.userFetch(Long.parseLong(principal.getName()));		
		String path = "resources//"+user.getUsername()+"//"+image.getOriginalFilename();
		File file = new File(path);
		file.mkdirs();	
		try {
			image.transferTo(file);
		} catch (Exception e) {
			System.out.println("haha lox");
		}
		user.setPathImage(path);
		return BACK+PAGE_PROFILE;
	}
	
}
