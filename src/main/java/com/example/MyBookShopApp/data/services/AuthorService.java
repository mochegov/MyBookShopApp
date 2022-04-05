package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.author.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<AuthorEntity>> getAuthorsMap() {
        List<AuthorEntity> authors = authorRepository.findAll(Sort.by("name").ascending());

        /*
        for (AuthorEntity author : authors) {
            Logger.getLogger(AuthorService.class.getName()).info(author.getName());
        }
        */

        Map<String, List<AuthorEntity>> authorsMap = authors
                .stream()
                .collect(Collectors.groupingBy((AuthorEntity a) -> a.getName().substring(0, 1)));

        // Как оказалось, после группировки списка результат возвращается в виде HashMap,
        // а нам надо отсортировать по первой букве имени автора
        TreeMap<String, List<AuthorEntity>> sortedAuthorsMap = new TreeMap<>(authorsMap);

        return sortedAuthorsMap;
    }

    // Получить автора по ID
    public AuthorEntity getAuthorById(Integer id){
        return authorRepository.getOne(id);
    }

}