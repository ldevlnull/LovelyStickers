package com.lovelystickersua.repository;

import com.lovelystickersua.POJO.Category;
import com.lovelystickersua.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by devnull on 15.11.16.
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query(value = "select c from Category c left join fetch c.products p where c.ID =:ID")
    Category fetch(@Param("ID") long ID);
}
