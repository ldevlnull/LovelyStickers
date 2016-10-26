package com.lovelystickersua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovelystickersua.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
