package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    List<GenreEntity> findGenreEntityByParentIdAndLevel(Integer parentId, Integer level);
}