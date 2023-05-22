package com.kumarmanoj.ecommerce.controller;

import com.kumarmanoj.ecommerce.common.ApiResponse;
import com.kumarmanoj.ecommerce.dto.productDTO.ProductDTO;
import com.kumarmanoj.ecommerce.model.Product;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.model.WishList;
import com.kumarmanoj.ecommerce.service.AuthenticationTokenService;
import com.kumarmanoj.ecommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/wishlist")
public class WishListController {

    @Autowired private WishListService wishListService;
    @Autowired private AuthenticationTokenService authenticationTokenService;

    // save product in wishlist

    @RequestMapping(path = "/addToWishList", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addToWishList(@RequestParam String token,
                                                     @RequestBody Product product)
    {
       // authenticate the token
        authenticationTokenService.authenticate(token);
       // find the user
        User user = authenticationTokenService.getUser(token);
       // save the product item to wishlist
        WishList wishList = new WishList(user, product);
        wishListService.createWishList(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item added to wish list"), HttpStatus.CREATED);
    }

    // get all wishlist product for a user
    @RequestMapping(path = "/getWishListItem/{token}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getWishListItem(@PathVariable("token") String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        User user = authenticationTokenService.getUser(token);
        List<ProductDTO> productDTOList = wishListService.getWishListByuser(user);
        return new ResponseEntity<List<ProductDTO>>(productDTOList, HttpStatus.OK);
    }
}
