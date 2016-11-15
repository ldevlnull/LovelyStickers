package com.lovelystickersua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lovelystickersua.POJO.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p left join fetch p.users u where p.ID =:ID")
	Product productFetch(@Param("ID") long ID);
}
