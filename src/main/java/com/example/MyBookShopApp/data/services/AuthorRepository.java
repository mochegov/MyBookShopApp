package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.author.AuthorEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    @Override
    List<AuthorEntity> findAll(Sort sort);
}