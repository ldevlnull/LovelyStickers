package com.lovelystickersua.serviceImp;


import com.lovelystickersua.DTO.CategoryDTO;
import com.lovelystickersua.DTO.DTO_UtilMapper;
import com.lovelystickersua.POJO.Category;
import com.lovelystickersua.repository.CategoryRepository;
import com.lovelystickersua.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by devnull on 15.11.16.
 */
@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public Category findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public Category fetch(long id) {
        return repository.fetch(id);
    }

    @Override
    public List<CategoryDTO> findAllDTOS() {
        return DTO_UtilMapper.listCategoryToCategoryDTO(repository.findAll());
    }
}
