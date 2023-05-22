package com.kumarmanoj.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleCustomException(CustomException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationFailedException.class)
    public final ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException authenticationFailedException) {
        return new ResponseEntity<>(authenticationFailedException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductNotExistsException.class)
    public final ResponseEntity<String> handleProductNotExistsException(ProductNotExistsException productNotExistsException) {
        return new ResponseEntity<>(productNotExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
