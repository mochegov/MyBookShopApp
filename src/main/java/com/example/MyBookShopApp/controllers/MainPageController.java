package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.common.CommonUtils;
import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.BooksRatingAndPopularityService;
import com.example.MyBookShopApp.data.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class MainPageController {
    private final BookService bookService;
    private final BooksRatingAndPopularityService booksRatingAndPopularityService;
    private final TagsService tagsService;

    @Autowired
    public MainPageController(BookService bookService, BooksRatingAndPopularityService booksRatingAndPopularityService, TagsService tagsService) {
        this.bookService = bookService;
        this.booksRatingAndPopularityService = booksRatingAndPopularityService;
        this.tagsService = tagsService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    // Главная страница
    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest request){
        model.addAttribute("recommendedBooks", bookService.getPageOfRecommendedBooks(0, 6).getContent());
        model.addAttribute("recentBooks",      bookService.getPageOfRecentBooks(null,null, 0, 6).getContent());
        model.addAttribute("popularBooks",     bookService.getPopularBooks(0, 6).getContent());
        model.addAttribute("tagsList",         tagsService.getTagsList());
        model.addAttribute("countBooksInCart", CommonUtils.getCountBooksInCookie(request, "cartContents"));
        model.addAttribute("countBooksPostponed", CommonUtils.getCountBooksInCookie(request, "postponedContents"));

        return "index";
    }

    // Запрос UI на получение следующей страницы рекомендованных книг
    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooksPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit){
        return new BooksPageDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    // Запрос UI на получение следующей страницы новых книг
    @GetMapping("/books/nextrecent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam(value = "from", required = false) String fromDate,
                                           @RequestParam(value = "to", required = false) String toDate,
                                           @RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit){
        Logger.getLogger(MainPageController.class.getName()).info("Запрос /books/nextrecent: " +
                                                                 "from: " + fromDate + ", " +
                                                                 "to: " + toDate + ", " +
                                                                 "offset: " + offset + ", " +
                                                                 "limit: " + limit
                                                            );

        return new BooksPageDto(bookService.getPageOfRecentBooks(fromDate, toDate, offset, limit).getContent());
    }

    // Запрос UI на получение следующей страницы популярных книг
    @GetMapping("/books/nextpopular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit){
        Logger.getLogger(MainPageController.class.getName()).info("Запрос /books/nextpopular: " +
                "offset: " + offset + ", " +
                "limit: " + limit
        );

        return new BooksPageDto(bookService.getPopularBooks(offset, limit).getContent());
    }

    // Обработка поискового запроса. Возвращает страницу поиска книг.
    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto, Model model){
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("booksList", bookService.getPageOfSearchResultsBooks(searchWordDto.getExample(), 0, 5).getContent());

        return "search/index";
    }

    // Обработка поискового запроса UI
    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto){
        return new BooksPageDto(bookService.getPageOfSearchResultsBooks(searchWordDto.getExample(), offset, limit).getContent());
    }

    // Запрос на обновление книги или всех книг (пока нет страницы доступной администратору)
    @PostMapping(value = {"/updaterating", "/updaterating/{bookId}"})
    public String updateRatingForAllBooks(@PathVariable(value = "bookId", required = false) Integer bookId){
        BookEntity book;

        Logger.getLogger(MainPageController.class.getName()).info("Запрос /updateraiting/{book}: " + "bookId: " + bookId);

        if (bookId == null) {
            // Не задана конкретная книга. Обновляем рейтинги всех книг.
            booksRatingAndPopularityService.updateRatingAllBooks();
        } else {
            // Задана книга. Находим ее и обновляем ее рейтинг.
            book = bookService.getBookById(bookId);
            if (book != null){
                booksRatingAndPopularityService.updateRating(book, booksRatingAndPopularityService.getCurrentRating(book), false);
            }
        }

        return "redirect:/";
    }
}