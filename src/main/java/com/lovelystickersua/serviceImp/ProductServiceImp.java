package com.lovelystickersua.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.repository.ProductRepository;
import com.lovelystickersua.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	public void save(Product product) {
		repository.save(product);
	}

	public Product findOne(Long id) {
		return repository.findOne(id);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}
