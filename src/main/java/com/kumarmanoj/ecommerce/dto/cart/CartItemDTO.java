package com.kumarmanoj.ecommerce.dto.cart;

import com.kumarmanoj.ecommerce.model.Cart;
import com.kumarmanoj.ecommerce.model.Product;

public class CartItemDTO {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItemDTO(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());
    }

}
