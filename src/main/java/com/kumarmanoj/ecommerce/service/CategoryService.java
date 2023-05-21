package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.model.Category;
import com.kumarmanoj.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Integer categoryId, Category category) {
        Optional<Category> findCategory  = categoryRepository.findById(categoryId);
        if(!findCategory.isPresent()) {
            return null;
        }
        Category presentCategory = findCategory.get();
        presentCategory.setCategoryName(category.getCategoryName());
        presentCategory.setDescription(category.getDescription());
        presentCategory.setImageUrl(category.getImageUrl());
        return categoryRepository.save(presentCategory);
    }
}
