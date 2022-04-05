package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.dto.BooksPageDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.services.BookService;
import com.example.MyBookShopApp.data.services.TagsService;
import com.example.MyBookShopApp.data.tags.TagEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TagsController {
    private final TagsService tagsService;
    private final BookService bookService;

    public TagsController(TagsService tagsService, BookService bookService) {
        this.tagsService = tagsService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto(){
        return new SearchWordDto();
    }

    @GetMapping("/tags/{id}")
    public String genrePage(@PathVariable("id") Integer id, Model model) {
        TagEntity tag = tagsService.getTagById(id);

        model.addAttribute("tag", tag);
        model.addAttribute("booksList", bookService.getPageOfBooksByTag(tag, 0, 6).getContent());

        return "/tags/index";
    }

    // Запрос UI на получение следующей страницы книг по тэгу
    @GetMapping("/books/nexttag/{id}")
    @ResponseBody
    public BooksPageDto getBooksByTagPage(@PathVariable("id") Integer id,
                                          @RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit){
        TagEntity tag = tagsService.getTagById(id);
        return new BooksPageDto(bookService.getPageOfBooksByTag(tag, offset, limit).getContent());
    }
}
