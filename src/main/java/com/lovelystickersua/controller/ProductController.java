package com.lovelystickersua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.service.ProductService;

@Controller
public class ProductController {

	private final static String PAGE_PRODUCT = "product";
	private final static String BACK = "redirect:/home";
	private final static String STAY = "redirect:/product";

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

/*	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
	public String saveCommodity(@ModelAttribute Product product) {
		pService.save(product);
		return STAY;
	}*/
	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
	public @ResponseBody String[] saveCommodity(@RequestBody Product product) {
		String [] products = new String[pService.findAll().size()];
		for(int i = 0; i < pService.findAll().size(); i++){
			products[i] = pService.findAll().get(i).getName()+":"+pService.findAll().get(i).getPrice();
		}
		return products;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id) {
		pService.delete(id);
		return STAY;
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String delete() {
		return BACK;
	}

}
