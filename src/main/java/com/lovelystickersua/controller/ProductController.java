package com.lovelystickersua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.service.ProductService;

@Controller
public class ProductController {

	private final static String PAGE_PRODUCT = "product";
	private final static String BACK = "redirect:/product";

	@Autowired
	private ProductService pService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String commodity(Model model) {
		List<Product> productsDB = pService.findAll();
		productsDB.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		model.addAttribute("products", productsDB);
		model.addAttribute("productMODEL", new Product());
		return PAGE_PRODUCT;
	}

	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
	public String saveCommodity(@ModelAttribute Product product) {
		pService.save(product);
		return BACK;
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id){
		pService.delete(id);
		return BACK;
	}
	
}
