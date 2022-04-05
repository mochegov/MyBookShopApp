package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.common.CommonUtils;
import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class PostponedController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public PostponedController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/postponed")
    public String postponedPage(@CookieValue(value = "postponedContents", required = false) String postponedContents,
                                HttpServletRequest request,
                                Model model){
        Logger.getLogger(this.getClass().getName()).info("Отложенные книги. Cookie [postponedContents]: " + postponedContents);

        if (postponedContents != null && !postponedContents.equals("")) {
            // Получаем список ID книг, отложенных пользователем
            List<Integer> listIds = Arrays.stream(postponedContents.split("/")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

            // Поиск книг по списку их ID
            List<BookEntity> booksList = bookService.getBooksByIdList(listIds);
            Logger.getLogger(this.getClass().getName()).info("Всего отложено книг: " + booksList.size());

            model.addAttribute("booksList", booksList);

            model.addAttribute("countBooksInCart", CommonUtils.getCountBooksInCookie(request, "cartContents"));
            model.addAttribute("countBooksPostponed", CommonUtils.getCountBooksInCookie(request, "postponedContents"));
        }

        /*
        // Получаем отложенные книги текущего пользователя
        // Как работать с текущим пользователем - мы пока не умеем, поэтому пока будет пользователь с ID=1
        model.addAttribute("booksList", bookService.getPostponed(userService.getUserByID(1)));
        */

        return "postponed";
    }
}
