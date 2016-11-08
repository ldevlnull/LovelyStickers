package com.lovelystickersua.controller;

import java.security.Principal;
import java.security.SecureRandom;
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
	private final static String PAGE_USERS = "users";
	private final static String REFRESH = "redirect:/";
	private final static String PAGE_PROFILE = "profile";
	private final static String EMAILS[] = {"numberlnull@gmail.com"};
	private final static String SITE_ADRESS = "http://127.0.0.1:8080/";

	@Autowired
	private ProductService pService;

	@Autowired
	private UserService uService;

	@Autowired
	private MailService mailSender;

	@Autowired
	private PurchaseOrderService poService;
	
	@RequestMapping(value = "/users")
	public String users(Model model){
		model.addAttribute("users", uService.findAllUserDTOs());
		return PAGE_USERS;
	}

	@RequestMapping(value = {"/loginpage"}, method = RequestMethod.GET)
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
		/* generate confirm link */
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder ref_link = new StringBuilder("");
		char random_symbol[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(int i = 0; i < 6; i++){
			ref_link.append(secureRandom.nextInt());
			ref_link.append(random_symbol[(int)(Math.random()*52)]);
		}
		/* end generate confirm link */
		try {
            user.setPathImage("resources/user_icon/anonymous.jpg");
			user.setActivateLink(ref_link.toString());
			user.setUsername(username);
			user.setRole(Role.ROLE_UNACTIVATED_USER);
			uService.save(user);
			String message = "Привіт!\nДякуємо за реєстрацію. Щоб активувати ваш аккаунт перейдіть "+ SITE_ADRESS +"activation/"
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
		if (activationLink.equals(user.getActivateLink())) {
			uService.activateUser(Long.parseLong(user.getUsername()));
		}
		return REFRESH+PAGE_LOGIN;
	}

	@RequestMapping(value = "/resendActivateLink", method=RequestMethod.GET)
	public String resendActivationLink(Principal principal) {
		User user = uService.findOne(Long.parseLong(principal.getName()));
		String ref_link = user.getActivateLink();
		String message = "Привіт! \nВи запросили повторну відправку листа підтвердженння "+ SITE_ADRESS +"activation/"
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
			/* CONSTRUCT MESSAGE OF PURCHASE TO USER */
			StringBuilder message = new StringBuilder("");
			message.append("Ви замовили: ");
			message.append(purchaseOrder.getProducts());
			message.append("\n\nДата: ");
			message.append(purchaseOrder.getOffer_date());
			for(int i = 0; i < message.length(); i++){
				if(message.charAt(i) == (char)(91) || message.charAt(i) == (char)(93)){
					message.deleteCharAt(i);
				}
			}
			System.out.println(message.toString());
			/* END MESSAGE */
			mailSender.sendMessage("Ваше замовлення", user.getEmail(), message.toString());
			for(String email : EMAILS) {
				mailSender.sendMessage("Нове замовлення",  email,
						("Нове замовлення:\t\nЗамовник:"+user.getName()+"\nНазва замовлення: " + purchaseOrder.getOffer_name().replace(" ", "") + "\nДата замовлення: " + purchaseOrder.getOffer_date()
								+ "\nІнформація про замовлення: " + purchaseOrder.getProducts()
								+ "\n\n\nСуммарная ціна: " + purchaseOrder.getTotalPrice() + "$").replace((char) (91), (char) (0)).replace((char) (93), (char) (0)));
			}
			user.setProducts(null);
		}
		return REFRESH +PAGE_PROFILE;
	}

}
