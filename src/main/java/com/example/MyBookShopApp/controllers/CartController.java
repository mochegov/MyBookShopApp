package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.UserService;
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
        List<Book> bookList = bookService.getCart(userService.getUserByID(1));
        model.addAttribute("booksList", bookList);
        model.addAttribute("totalPrice", bookService.getTotalPrice(bookList, false));
        model.addAttribute("totalOldPrice", bookService.getTotalPrice(bookList, true));

        return "cart";
    }
}