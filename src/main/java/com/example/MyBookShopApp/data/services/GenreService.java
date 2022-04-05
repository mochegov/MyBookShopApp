package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GenreService {
    GenreRepository genreRepository;
    Book2GenreRepository book2GenreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository, Book2GenreRepository book2GenreRepository) {
        this.genreRepository = genreRepository;
        this.book2GenreRepository = book2GenreRepository;
    }

    // Найти жанр по его ID
    public GenreEntity getGenryById(Integer id) {
        return genreRepository.getOne(id);
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

    // Формирует полный список жанров вместе с дочерними. Параметры: готовая иерархия жанров, пустой список для получения результата.
    public void getFullGenreList(List<GenreEntity> genresFrom, List<GenreEntity> fillGenreList){
        if (!genresFrom.isEmpty()){
            for (GenreEntity genre: genresFrom){
                fillGenreList.add(genre);
                getFullGenreList(genre.getChildGenres(), fillGenreList);
            }
        }
    }

    // Получение списка жанров вместе с дочерними
    public List<GenreEntity> getGenresData(Integer parentId, Integer level) {
        List<GenreEntity> genreEntities = genreRepository.findGenreEntityByParentIdAndLevel(parentId, level);

        if (!genreEntities.isEmpty()) {
            for (GenreEntity genreEntity : genreEntities) {
                List<GenreEntity> childGenreEntities = getGenresData(genreEntity.getId(),level + 1);
                genreEntity.setChildGenres(childGenreEntities);
            }
        }

        return new ArrayList<>(genreEntities);
    }

    // Получает количество книг по списку жанров
    public Map<GenreEntity, Integer> getBooksCountByGenres(List<GenreEntity> genres){
        List<GenreEntity> fullGenresList = new ArrayList<>();

        // Получим полный сквозной список жанров
        getFullGenreList(genres, fullGenresList);

        // Создаем Map. Ключ - экземпляр жанра, значение - количество книг по данному жанру.
        Map<GenreEntity, Integer> genresMap = fullGenresList.stream()
                .collect(Collectors.toMap(genre -> genre, genre -> book2GenreRepository.countAllByGenre_Id(genre.getId())));

        // Отладочная печать карты с результатами
        //Logger.getLogger(GenreService.class.getName()).info("Статистика по жанрам:");
        //genresMap.entrySet().forEach(genreEntry -> Logger.getLogger(GenreService.class.getName()).info(genreEntry.getKey().getName() + " (" + genreEntry.getValue() + ")"));

        return genresMap;
    }
}