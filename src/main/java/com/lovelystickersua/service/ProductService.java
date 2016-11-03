package com.lovelystickersua.service;

import java.util.List;

import com.lovelystickersua.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService{

	void save(Product product);
	Product findOne(Long id);
	List<Product> findAll();
	void delete(Long id);
	String saveIcon(MultipartFile multipartFile);
}
