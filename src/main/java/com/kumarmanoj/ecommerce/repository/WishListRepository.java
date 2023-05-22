package com.kumarmanoj.ecommerce.repository;

import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
