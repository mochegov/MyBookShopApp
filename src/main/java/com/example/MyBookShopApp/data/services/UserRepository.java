package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity getUserEntityById(Integer id);
}