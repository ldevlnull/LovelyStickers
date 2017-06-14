package com.lovelystickersua.controller;

import com.lovelystickersua.DTO.CategoryDTO;
import com.lovelystickersua.DTO.ProductDTO;
import com.lovelystickersua.POJO.Product;
import com.lovelystickersua.service.CategoryService;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.lovelystickersua.controller.PAGE.*;

@Controller
public class ProductController {

	@Autowired
	private UserService uService;
	@Autowired
	private ProductService pService;
	@Autowired
	private CategoryService cService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String commodity(Model model) {
		List<ProductDTO> productsDB = pService.findAllProductDTOs();
		List<CategoryDTO> categories = cService.findAllDTOS();
		productsDB.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
		categories.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
		model.addAttribute("categories", categories);
		model.addAttribute("products", productsDB);
		model.addAttribute("productMODEL", new Product());
		return PRODUCT.val();
	}
	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
	public String saveCommodity(@ModelAttribute Product product, @RequestParam MultipartFile image) {
		product.setIconPath(pService.saveIcon(image));
		pService.save(product);
		return REFRESH.val()+ PRODUCT.val();
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id) {
		pService.delete(id);
		return REFRESH.val()+PRODUCT.val();
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		return REFRESH.val()+ HOME.val();
	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public @ResponseBody ProductDTO[] getProducts(){
		List<ProductDTO> productDTOs = pService.findAllProductDTOs();
		ProductDTO []products = new ProductDTO[productDTOs.size()];
		for (int i = 0; i <	productDTOs.size(); i++) {
			products[i] = productDTOs.get(i);
		}
		for (ProductDTO p:
			 products) {
			System.out.println(p.toString());
		}
		return products;
	}
}
