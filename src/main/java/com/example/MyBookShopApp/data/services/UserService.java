package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получить пользователя по ID
    public UserEntity getUserByID(Integer id) {
        UserEntity userEntity = userRepository.getUserEntityById(id);
        return userEntity;
    }
}
