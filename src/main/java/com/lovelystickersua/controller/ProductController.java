package com.lovelystickersua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

	private final static String PAGE_PRODUCT = "product";
	private final static String PAGE_HOME = "home";
	private final static String REFRESH = "redirect:/";

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
	public String saveCommodity(@ModelAttribute Product product, @RequestParam MultipartFile image) {
		product.setProductIconPath(pService.saveIcon(image));
		pService.save(product);
		return REFRESH+PAGE_PRODUCT;
	}
//	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
//	public @ResponseBody String[] saveCommodity(@RequestBody Product product) {
//		String [] products = new String[pService.findAll().size()];
//		for(int i = 0; i < pService.findAll().size(); i++){
//			products[i] = pService.findAll().get(i).getName()+":"+pService.findAll().get(i).getPrice();
//		}
//		return products;
//	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id) {
		pService.delete(id);
		return REFRESH+PAGE_PRODUCT;
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String delete() {
		return REFRESH+PAGE_HOME;
	}

}
