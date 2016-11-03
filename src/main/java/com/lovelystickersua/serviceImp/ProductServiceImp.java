package com.lovelystickersua.serviceImp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.lovelystickersua.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovelystickersua.entity.Product;
import com.lovelystickersua.repository.ProductRepository;
import com.lovelystickersua.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public String saveIcon(MultipartFile multipartFile) {
		String path = System.getProperty("catalina.home") + "/resources/product_icons/"
				+ multipartFile.getOriginalFilename();
		File file = new File(path);
		file.mkdirs();
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "resources/product_icons/"+multipartFile.getOriginalFilename();
	}
}
