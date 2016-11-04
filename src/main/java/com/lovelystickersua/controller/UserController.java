package com.lovelystickersua.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.entity.PurchaseOrder;
import com.lovelystickersua.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.MailService;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.PurchaseOrderService;
import com.lovelystickersua.service.UserService;

@Controller
public class UserController {

	private final static String PAGE_LOGIN = "loginpage";
	private final static String PAGE_REGISTER = "register";
	private final static String BACK = "redirect:/";
	private final static String PAGE_PROFILE = "profile";

	@Autowired
	private ProductService pService;

	@Autowired
	private UserService uService;

	@Autowired
	private MailService mailSender;

	@Autowired
	private PurchaseOrderService poService;

	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String login() {
		return PAGE_LOGIN;
	}

	@RequestMapping(value = { "/home", "/logout", "/activate" }, method = RequestMethod.POST)
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
		for (int i = 0; i < 15; i++) {
			ref_link += (int) (Math.random() * 10);
		}
		try {
			user.setActivateLink(ref_link);
			user.setRole(Role.ROLE_UNACTIVATED_USER);
			uService.save(user);
			String message = "Привет!\nСпасибо за регистрацию. Чтобы активировать ваш аккаунт перейдите по ссылке http://lovelystickersua.com/activation/"
					+ ref_link + "/" + user.getName();
			mailSender.sendMessage("Регистрация на сайте lovelystickersua.com", user.getEmail(), message);
			return BACK;
		}catch (Exception e){
			return BACK;
		}
	}

	@RequestMapping(value = "/activation/{activationLink}/{username}")
	public String activateUser(@PathVariable String activationLink, @PathVariable String username) {
		User user = uService.findByUsername(username);
		System.out.println(user);
		System.out.println("link"+activationLink);
		System.out.println("user"+user.getActivateLink());
		if (activationLink.equalsIgnoreCase(user.getActivateLink())) {
			uService.activateUser(Long.parseLong(user.getUsername()));
		}
		return BACK;
	}

	@RequestMapping(value = "/resendActivateLink", method=RequestMethod.GET)
	public String resendActivationLink(Principal principal) {
		User user = uService.findOne(Long.parseLong(principal.getName()));
		String ref_link = user.getActivateLink();
		String message = "Привет! \nВы запросили повторную отправку ссылки для подтверждения http://lovelystickersua.com/activation/"
				+ ref_link + "/" + user.getName();
		mailSender.sendMessage("Повторное подтверждение", user.getEmail(), message);
		return BACK;
	}
	

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		model.addAttribute("user", uService.userFetch(Long.parseLong(principal.getName())));
		return PAGE_PROFILE;
	}

	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	public String saveImage(Principal principal, @RequestParam MultipartFile image) {
		if (image != null) {
			uService.saveImage(principal, image);
		}
		return "redirect:/profile";
	}

	@RequestMapping(value = "/createPurchaseOrder", method = RequestMethod.POST)
	public String createPurchaseOrder(Principal principal) {
		long user_ID = Long.parseLong(principal.getName());
		User user = uService.userFetch(user_ID);
		if(!user.getProducts().isEmpty()) {
			List<Product> list = user.getProducts();
			PurchaseOrder purchaseOrder = new PurchaseOrder(user, list);
			poService.save(purchaseOrder);
			mailSender.sendMessage("Ваш заказ", user.getEmail(), ("Вы заказали: " + purchaseOrder.getProducts() + "\n\nДата: " + purchaseOrder.getOffer_date()).replace((char) (91), (char) (0)).replace((char) (93), (char) (0)));
			mailSender.sendMessage("Новый заказ", "numberlnull@gmail.com",
					("Новый заказ:\nИмя заказа:" + purchaseOrder.getOffer_name() + "\nДата заказа: " + purchaseOrder.getOffer_date()
							+ "\nИнформация о заказе: " + purchaseOrder.getProducts()
							+ "\n\n\nСуммарная цена: " + purchaseOrder.getTotalPrice() + "$").replace((char) (91), (char) (0)).replace((char) (93), (char) (0)));
			for (Product product : list) {
				product.setUser(null);
				pService.save(product);
			}
		}
		return "redirect:/"+PAGE_PROFILE;
	}

}
