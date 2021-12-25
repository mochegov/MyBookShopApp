package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.tags.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<TagEntity, Integer> {}
