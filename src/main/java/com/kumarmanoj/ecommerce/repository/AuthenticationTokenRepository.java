package com.kumarmanoj.ecommerce.repository;

import com.kumarmanoj.ecommerce.model.AuthenticationToken;
import com.kumarmanoj.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
