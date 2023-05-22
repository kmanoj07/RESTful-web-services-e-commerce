package com.kumarmanoj.ecommerce.dto.cart;

import java.util.List;

public class CartDTO {
    private List<CartItemDTO> cartItems;
    private double totalCoast;

    public CartDTO() {
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(double totalCoast) {
        this.totalCoast = totalCoast;
    }
}
