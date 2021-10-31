package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostponedController {
    private final BookService bookService;

    @Autowired
    public PostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/postponed")
    public String postponedPage(Model model){
        // Получаем отложенные книги текущего пользователя
        // Как работать с текущим пользователем - мы пока не умеем :)
        // Поэтому пока будет mochegov@gmail.com
        model.addAttribute("booksList", bookService.getPostponed("mochegov@gmail.com"));
        return "postponed";
    }
}
