package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GenresController {
    private final GenreService genreService;

    @Autowired
    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String genresPage(Model model){
        List<GenreEntity> genres = genreService.getGenresData(0, 1);
        //genreService.PrintGenres(genres);

        model.addAttribute("genres", genres);

        return "/genres/index";
    }
}