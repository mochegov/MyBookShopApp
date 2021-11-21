package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PopularController {
    private final BookService bookService;

    @Autowired
    public PopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/popular")
    public String recentBookPage(Model model){
        model.addAttribute("booksList", bookService.getPopularBooks());
        return "books/popular";
    }
}
