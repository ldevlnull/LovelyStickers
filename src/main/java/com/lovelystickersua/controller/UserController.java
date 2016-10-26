package com.lovelystickersua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.UserService;

@Controller
public class UserController {

	private final static String PAGE_LOGIN = "loginpage";
	private final static String PAGE_REGISTER = "register";
	private final static String BACK = "redirect:/";
	
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
	
	
}
