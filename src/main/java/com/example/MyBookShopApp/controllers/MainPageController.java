package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("recommendedBooks", bookService.getRecomendedBooks());
        model.addAttribute("recentBooks",      bookService.getRecentBooks());
        model.addAttribute("popularBooks",     bookService.getPopularBooks());

        return "index";
    }
}