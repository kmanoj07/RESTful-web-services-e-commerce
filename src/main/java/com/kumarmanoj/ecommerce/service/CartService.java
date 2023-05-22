package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.dto.cart.AddToCartDTO;
import com.kumarmanoj.ecommerce.dto.cart.CartDTO;
import com.kumarmanoj.ecommerce.dto.cart.CartItemDTO;
import com.kumarmanoj.ecommerce.exceptions.CustomException;
import com.kumarmanoj.ecommerce.model.Cart;
import com.kumarmanoj.ecommerce.model.Product;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ProductService productService;

    @Autowired private CartRepository cartRepository;

    public void addToCart(AddToCartDTO addToCartDTO, User user) {
        // validate the product (product_id)
        Product product =  productService.findById(addToCartDTO.getProductId());
        // save the product to cart
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDTO.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepository.save(cart);
    }

    public CartDTO getCartItemList(User user) {
        List<Cart> cartItemList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        double totalCoast = 0;
        for(Cart cart: cartItemList){
            CartItemDTO cartItemDTO = new CartItemDTO(cart);
            totalCoast += cartItemDTO.getQuantity() * cart.getProduct().getPrice();
            cartItemDTOS.add(cartItemDTO);
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItems(cartItemDTOS);
        cartDTO.setTotalCoast(totalCoast);

        return cartDTO;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        // items id belongs to user
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if(optionalCart.isEmpty()){
            throw new CustomException("cart item id is invalid");
        }

        Cart cart = optionalCart.get();
        if(cart.getUser() != user) {
            throw  new CustomException("cart item doest not belong to user: " + cartItemId);
        }

        cartRepository.delete(cart);
    }
}
