package com.kumarmanoj.ecommerce.controller;

import com.kumarmanoj.ecommerce.common.ApiResponse;
import com.kumarmanoj.ecommerce.model.Category;
import com.kumarmanoj.ecommerce.model.Product;
import com.kumarmanoj.ecommerce.dto.productDTO.ProductDTO;
import com.kumarmanoj.ecommerce.repository.CategoryRepository;
import com.kumarmanoj.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    @Autowired private ProductService productService;
    @Autowired private CategoryRepository categoryRepository;

    @RequestMapping(path = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDTO productDTO) {
        // check categoryId is valid or not
        Optional<Category> optionalCategory =  categoryRepository.findById(productDTO.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist!"), HttpStatus.NOT_FOUND);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDTO, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product created"), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/getAllProduct", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productList = productService.getAllProduct();
        return new ResponseEntity<List<ProductDTO>>(productList, HttpStatus.OK);
    }

    @RequestMapping(path = "/updateProduct/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        // check categoryId is valid or not
        Optional<Category> optionalCategory =  categoryRepository.findById(productDTO.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist!"), HttpStatus.NOT_FOUND);
        }
        Category category = optionalCategory.get();
         Product product = productService.updateProuct(productId, productDTO, category);
         if(product == null) {
             return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product not found"), HttpStatus.NOT_FOUND);
         }
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product "+ productId + " updated"), HttpStatus.OK);
    }


}
