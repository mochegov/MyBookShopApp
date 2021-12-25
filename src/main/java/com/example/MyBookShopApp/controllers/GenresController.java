package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.GenreDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class GenresController {
    private final GenreService genreService;
    private final BookService bookService;

    @Autowired
    public GenresController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/genres")
    public String genresPage(Model model){
        List<GenreEntity> genres = genreService.getGenresData(0, 1);

        // Информация о жанрах
        model.addAttribute("genres", genres);

        // Статистика по числу книг в каждом жанре
        model.addAttribute("fullGenresList", genreService.getBooksCountByGenres(genres));

        return "/genres/index";
    }

    @GetMapping("/genre/{id}")
    public String genrePage(@PathVariable("id") Integer id, Model model) {
        GenreEntity genre = genreService.getGenryById(id);
        model.addAttribute("genre", new GenreDto(genre));
        model.addAttribute("booksList", bookService.getPageOfBooksByGenre(genre, 0, 6).getContent());

        return "/genres/slug";
    }

    // Запрос UI на получение следующей страницы книг по жанру
    @GetMapping("/books/nextgenre/{id}")
    @ResponseBody
    public BooksPageDto getBooksByGenrePage(@PathVariable("id") Integer id,
                                            @RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit){
        GenreEntity genre = genreService.getGenryById(id);
        return new BooksPageDto(bookService.getPageOfBooksByGenre(genre, offset, limit).getContent());
    }

}