package com.lovelystickersua.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.entity.PurchaseOrder;
import com.lovelystickersua.entity.Role;
import com.lovelystickersua.entity.User;
import com.lovelystickersua.service.MailService;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.PurchaseOrderService;
import com.lovelystickersua.service.UserService;

@Controller
public class UserController {

	private final static String PAGE_LOGIN = "loginpage";
	private final static String PAGE_REGISTER = "register";
	private final static String REFRESH = "redirect:/";
	private final static String PAGE_PROFILE = "profile";
	private final static String EMAILS[] = {"numberlnull@gmail.com"};
	private final static String SITE_ADRES = "http://127.0.0.1:8080/";

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

	@RequestMapping(value = { "/home", "/logout" }, method = RequestMethod.POST)
	public String logout() {
		return REFRESH;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return PAGE_REGISTER;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, @RequestParam String username) {
		String ref_link = user.getActivateLink();
/*		for (int i = 0; i < 15; i++) {
			ref_link += (int) (Math.random() * 10);
		}*/
		try {
			user.setUsername(username);
			user.setRole(Role.ROLE_UNACTIVATED_USER);
			uService.save(user);
			String message = "Привіт!\nДякуємо за реєстрацію. Щоб активувати ваш аккаунт перейдіть "+ SITE_ADRES +"activation/"
					+ ref_link + "/" + user.getName();
			mailSender.sendMessage("Реєстрація на сайті lovelystickersua.com", user.getEmail(), message);
			return REFRESH;
		}catch (Exception e){
			return REFRESH;
		}
	}

	@RequestMapping(value = "/activation/{activationLink}/{username}")
	public String activateUser(@PathVariable String activationLink, @PathVariable String username) {
		User user = uService.findByUsername(username);
		if (activationLink.equalsIgnoreCase(user.getActivateLink())) {
			uService.activateUser(Long.parseLong(user.getUsername()));
		}
		return REFRESH;
	}

	@RequestMapping(value = "/resendActivateLink", method=RequestMethod.GET)
	public String resendActivationLink(Principal principal) {
		User user = uService.findOne(Long.parseLong(principal.getName()));
		String ref_link = user.getActivateLink();
		String message = "Привіт! \nВи запросили повторну відправку листа підтвердженння "+ SITE_ADRES +"activation/"
				+ ref_link + "/" + user.getName();
		mailSender.sendMessage("Повторне підтвердження", user.getEmail(), message);
		return REFRESH;
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
		return REFRESH +PAGE_PROFILE;
	}

	@RequestMapping(value = "/createPurchaseOrder", method = RequestMethod.POST)
	public String createPurchaseOrder(Principal principal) {
		long user_ID = Long.parseLong(principal.getName());
		User user = uService.userFetch(user_ID);
		if(!user.getProducts().isEmpty()) {
			List<Product> list = user.getProducts();
			PurchaseOrder purchaseOrder = new PurchaseOrder(user, list);
			poService.save(purchaseOrder);
			mailSender.sendMessage("Ваше замовлення", user.getEmail(), ("Ви замовили: " + purchaseOrder.getProducts() + "\n\nДата: " + purchaseOrder.getOffer_date()).replace((char) (91), (char) (0)).replace((char) (93), (char) (0)));
			for(String email : EMAILS) {
				mailSender.sendMessage("Нове замовлення",  email,
						("Нове замовлення:\nНазва замовлення: " + purchaseOrder.getOffer_name() + "\nДата замовлення: " + purchaseOrder.getOffer_date()
								+ "\nІнформація про замовлення: " + purchaseOrder.getProducts()
								+ "\n\n\nСуммарная ціна: " + purchaseOrder.getTotalPrice() + "$").replace((char) (91), (char) (0)).replace((char) (93), (char) (0)));
			}
			user.setProducts(null);
		}
		return REFRESH +PAGE_PROFILE;
	}

}
