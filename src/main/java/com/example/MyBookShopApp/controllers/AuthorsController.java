package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.author.AuthorEntity;
import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.services.AuthorService;
import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap(){
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @GetMapping("/authors/{id}")
    public String authorsPage(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("authorBooks", bookService.getPageBooksByAuthor(authorService.getAuthorById(id), 0, 6).getContent());
        model.addAttribute("amountBooks", bookService.getCountBookByAuthor(authorService.getAuthorById(id)));

        return "/authors/slug";
    }

    @GetMapping("/books/author/{id}")
    public String booksAuthorPage(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("booksList", bookService.getPageBooksByAuthor(authorService.getAuthorById(id), 0, 6).getContent());

        return "/books/author";
    }

    // Запрос UI на получение следующей страницы книг по автору
    @GetMapping("/books/nextauthor/{id}")
    @ResponseBody
    public BooksPageDto getBooksByAuthorPage(@PathVariable("id") Integer id,
                                             @RequestParam("offset") Integer offset,
                                             @RequestParam("limit") Integer limit){
        AuthorEntity author = authorService.getAuthorById(id);
        return new BooksPageDto(bookService.getPageBooksByAuthor(author, offset, limit).getContent());
    }
}