package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.model.Category;
import com.kumarmanoj.ecommerce.model.Product;
import com.kumarmanoj.ecommerce.dto.productDTO.ProductDTO;
import com.kumarmanoj.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;
    public Product addProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(product.getPrice());

        product.setCategory(category);
        return productRepository.save(product);
    }

    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getProductName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setId(product.getId());
        return productDTO;
    }

    public List<ProductDTO> getAllProduct() {
       List<Product> allProduct = productRepository.findAll();
       List<ProductDTO> productDTOList = new ArrayList<>();
       allProduct.forEach(product -> {
          ProductDTO productDTO  = getProductDTO(product);
          productDTOList.add(productDTO);
       });
       return productDTOList;
    }

    public Product updateProuct(Integer productId, ProductDTO productDTO, Category category) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            return null;
        }
        Product product = optionalProduct.get();

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        return productRepository.save(product);
    }
}
