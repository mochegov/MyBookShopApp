package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostponedController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public PostponedController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/postponed")
    public String postponedPage(Model model){
        // Получаем отложенные книги текущего пользователя
        // Как работать с текущим пользователем - мы пока не умеем, поэтому пока будет пользователь с ID=1
        model.addAttribute("booksList", bookService.getPostponed(userService.getUserByID(1)));
        return "postponed";
    }
}
