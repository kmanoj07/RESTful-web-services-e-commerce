package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.dto.productDTO.ProductDTO;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.model.WishList;
import com.kumarmanoj.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired private WishListRepository wishListRepository;
    @Autowired private ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDTO> getWishListByuser(User user) {
        List<WishList> wishListList =  wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDTO> productDTOList = new ArrayList<>();
        wishListList.forEach(wishList -> {
            ProductDTO productDTO = productService.getProductDTO(wishList.getProduct());
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }
}
