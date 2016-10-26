package com.lovelystickersua.service;

import java.util.List;

import com.lovelystickersua.entity.Product;

public interface ProductService{

	void save(Product product);
	Product findOne(Long id);
	List<Product> findAll();
	void delete(Long id);
}
