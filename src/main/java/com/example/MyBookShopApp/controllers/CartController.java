package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.UserService;
import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public CartController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String cartPage(Model model){
        UserEntity userEntity = userService.getUserByID(1);
        List<BookEntity> booksList = bookService.getCart(userEntity);
        model.addAttribute("booksList", booksList);
        model.addAttribute("totalPrice", bookService.getTotalPrice(booksList, false));
        model.addAttribute("totalOldPrice", bookService.getTotalPrice(booksList, true));

        return "cart";
    }
}