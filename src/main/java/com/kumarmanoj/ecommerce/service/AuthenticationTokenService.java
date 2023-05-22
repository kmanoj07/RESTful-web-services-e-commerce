package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.exceptions.AuthenticationFailedException;
import com.kumarmanoj.ecommerce.model.AuthenticationToken;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationTokenService {
    @Autowired private AuthenticationTokenRepository authenticationRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        authenticationRepository.save(authenticationToken);
    }
    public AuthenticationToken getToken(User user) {
        return authenticationRepository.findByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken =  authenticationRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        // token is not null anymore
        return authenticationToken.getUser();
    }
    public void authenticate(String token) {
        // null check
        if(Objects.isNull(token)){
            // throw exception
            throw new AuthenticationFailedException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailedException("token not valid");
        }
    }
}
