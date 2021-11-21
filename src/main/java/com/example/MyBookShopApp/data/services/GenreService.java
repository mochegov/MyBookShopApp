package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GenreService {
    GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Печать иерархической структуры жанров для отладки
    public void PrintGenres(List<GenreEntity> genres){
        if (!genres.isEmpty()){
            for (GenreEntity genre: genres){
                String ShiftLeft = "";
                for (int i = 0; i <= genre.getLevel(); i++) {
                    ShiftLeft = ShiftLeft + "  ";
                }
                Logger.getLogger(GenreService.class.getName()).info(ShiftLeft + genre.getName());
                PrintGenres(genre.getChildGenres());
            }
        }
    }

    public List<GenreEntity> getGenresData(Integer parentId, Integer level) {
        List<GenreEntity> genreEntities = genreRepository.findGenreEntityByParentIdAndLevel(parentId, level);

        if (!genreEntities.isEmpty()) {
            for (GenreEntity genreEntity : genreEntities) {
                List<GenreEntity> childGenreEntities = getGenresData(genreEntity.getId(), level + 1);
                genreEntity.setChildGenres(childGenreEntities);
            }
        }

        return new ArrayList<>(genreEntities);
    }
}