package com.kumarmanoj.ecommerce.exceptions;

public class ProductNotExistsException extends IllegalArgumentException {
    public ProductNotExistsException(String message)
    {
        super(message);
    }
}
