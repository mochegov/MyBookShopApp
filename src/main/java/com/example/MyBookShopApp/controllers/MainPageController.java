package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookService;
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
        model.addAttribute("recommendedBooks", bookService.getBooksData(1));
        model.addAttribute("recentBooks",      bookService.getBooksData(2));
        model.addAttribute("popularBooks",     bookService.getBooksData(3));

        return "index";
    }
}