package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.common.CommonUtils;
import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class BookshopCartController {

    private final BookService bookService;

    @Autowired
    public BookshopCartController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute(name = "bookCart")
    public List<BookEntity> bookCart(){
        return new ArrayList<>();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("books/cart")
    public String cartPage(@CookieValue(value = "cartContents", required = false) String cartContents,
                           HttpServletRequest request,
                           Model model){
        Logger.getLogger(this.getClass().getName()).info("Корзина книг. Cookie [cartContents]: " + cartContents);

        if (cartContents == null || cartContents.equals("")) {
            model.addAttribute("isCartEmpty", true);
        } else {
            // Получаем список ID книг
            List<Integer> listId = Arrays.stream(cartContents.split("/")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

            // Поиск книг по списку их ID
            List<BookEntity> booksList = bookService.getBooksByIdList(listId);
            Logger.getLogger(this.getClass().getName()).info("Найдено книг: " + booksList.size());

            model.addAttribute("booksList", booksList);
            model.addAttribute("isCartEmpty", false);
            model.addAttribute("totalPrice",    bookService.getTotalPrice(booksList, false));
            model.addAttribute("totalOldPrice", bookService.getTotalPrice(booksList, true));
        }

        model.addAttribute("countBooksInCart", CommonUtils.getCountBooksInCookie(request, "cartContents"));
        model.addAttribute("countBooksPostponed", CommonUtils.getCountBooksInCookie(request, "postponedContents"));

        /*
        UserEntity userEntity = userService.getUserByID(1);
        List<BookEntity> booksList = bookService.getCart(userEntity);
        model.addAttribute("booksList", booksList);
        */

        return "cart";
    }
}
