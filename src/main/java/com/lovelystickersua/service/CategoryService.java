package com.lovelystickersua.service;

import com.lovelystickersua.DTO.CategoryDTO;
import com.lovelystickersua.POJO.Category;

import java.util.List;

/**
 * Created by devnull on 15.11.16.
 */
public interface CategoryService {

    void save(Category category);
    Category findOne(long id);
    List<Category> findAll();
    void delete(long id);
    Category fetch(long id);
    List<CategoryDTO> findAllDTOS();
}
