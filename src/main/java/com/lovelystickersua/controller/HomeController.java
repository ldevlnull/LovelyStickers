package com.lovelystickersua.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.UserService;

@Controller
public class HomeController {

	private final static String PAGE = "home";
	private final static String PROFILE = "redirect:/profile";
	private final static String PRODUCT = "redirect:/product";
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ProductService pService;
	
	@RequestMapping(value={"/","/home"}, method=RequestMethod.GET)
	public String home(Model model, Principal principal){
		return PAGE;
	}

	@RequestMapping(value="/buy/{ID}", method=RequestMethod.GET)
	public String home(Principal principal, @PathVariable String ID){	
		User user = uService.userFetch(Long.parseLong(principal.getName()));	
		Product product = pService.productFetch(Long.parseLong(ID));
		product.getUsers().add(user);
		pService.save(product);
		return PRODUCT;
	}
	@RequestMapping(value="/deleteFromCart/{ID}", method = RequestMethod.GET)
	public String delete(Principal principal, @PathVariable long ID){
		Product product = pService.productFetch(ID);
		User user = uService.userFetch(Long.parseLong(principal.getName()));
		product.getUsers().remove(user);
		pService.save(product);
		return PROFILE;
	}
}

