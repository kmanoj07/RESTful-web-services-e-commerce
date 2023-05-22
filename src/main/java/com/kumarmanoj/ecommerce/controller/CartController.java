package com.kumarmanoj.ecommerce.controller;

import com.kumarmanoj.ecommerce.common.ApiResponse;
import com.kumarmanoj.ecommerce.dto.cart.AddToCartDTO;
import com.kumarmanoj.ecommerce.dto.cart.CartDTO;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.service.AuthenticationTokenService;
import com.kumarmanoj.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    // post cart api
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDTO addToCartDTO,
                                                 @RequestParam String token)
    {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        User user = authenticationTokenService.getUser(token);
        cartService.addToCart(addToCartDTO, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    // get all cart items for a user
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public ResponseEntity<CartDTO> getCartItems(@RequestParam String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        User user = authenticationTokenService.getUser(token);
        CartDTO cartDTO = cartService.getCartItemList(user);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    // delete a cart item for a user
    @RequestMapping(path = "/delete/{cartItemId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Integer cartItemId
    , @RequestParam String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        User user = authenticationTokenService.getUser(token);

        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "cart item deleted " + cartItemId), HttpStatus.OK);
    }
}
