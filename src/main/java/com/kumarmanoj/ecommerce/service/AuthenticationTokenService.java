package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.model.AuthenticationToken;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired private AuthenticationTokenRepository authenticationRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        authenticationRepository.save(authenticationToken);
    }
    public AuthenticationToken getToken(User user) {
        return authenticationRepository.findByUser(user);
    }
}
