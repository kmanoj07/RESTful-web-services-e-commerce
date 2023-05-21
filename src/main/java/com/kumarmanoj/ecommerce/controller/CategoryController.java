package com.kumarmanoj.ecommerce.controller;

import com.kumarmanoj.ecommerce.common.ApiResponse;
import com.kumarmanoj.ecommerce.model.Category;
import com.kumarmanoj.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @RequestMapping(path="/create", method = RequestMethod.POST)
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }
    @RequestMapping(path="/categoryList", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }
    @RequestMapping(path = "/update/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }
}
