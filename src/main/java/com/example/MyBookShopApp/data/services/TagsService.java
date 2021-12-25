package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.tags.TagEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {
    private TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    // Получение общего списка тэгов
    public List<TagEntity> getTagsList(){
        return tagsRepository.findAll();
    }

    // Получение тэга по его ID
    public TagEntity getTagById(Integer id){
        return tagsRepository.getOne(id);
    }

}
