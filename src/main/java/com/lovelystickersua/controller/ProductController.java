package com.lovelystickersua.controller;

import java.util.Arrays;
import java.util.List;

import com.lovelystickersua.DTO.CategoryDTO;
import com.lovelystickersua.DTO.UserDTO;
import com.lovelystickersua.POJO.User;
import com.lovelystickersua.service.CategoryService;
import com.lovelystickersua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lovelystickersua.DTO.ProductDTO;
import com.lovelystickersua.POJO.Product;
import com.lovelystickersua.service.ProductService;

import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value = "/productStore/{id}")
	public String productStore(@RequestParam String id, Model model ){
		Product product = pService.findOne(Long.parseLong(id));
		model.addAttribute("product", product);
		return STORE.val();
	}
	@RequestMapping(value = "/newProduct", method = RequestMethod.POST)
	public String saveCommodity(@ModelAttribute Product product, @RequestParam MultipartFile image) {
		product.setIconPath(pService.saveIcon(image));
		pService.save(product);
		return REFRESH.val()+ PRODUCT.val();
	}
//	@RequestMapping(value = "/newProduct?${_csrf.parameterName}=${_csrf.token}", method = RequestMethod.POST)
//	public @ResponseBody String[] saveCommodity(@RequestBody Product product) {
//		String [] products = new String[pService.findAll().size()];
//		for(int i = 0; i < pService.findAll().size(); i++){
//			products[i] = pService.findAll().get(i).getName()+":"+pService.findAll().get(i).getPrice()+":"+pService.findAll().get(i).getIconPath();
//		}
//		return products;
//	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id) {
		pService.delete(id);
		return REFRESH.val()+PRODUCT.val();
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back() {
		return REFRESH.val()+ HOME.val();
	}

	@RequestMapping(value = "/checkCart", method = RequestMethod.POST)
	public @ResponseBody boolean checkCart(@RequestBody UserDTO userDTO){
		User user = uService.userFetch(uService.findByEmail(userDTO.getEmail()).getID());
		System.out.println(user.getProducts().size());
		if(user.getProducts().size() == 0){
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping(value = "/store")
	public String store(){
		return STORE.val();
	}

}
