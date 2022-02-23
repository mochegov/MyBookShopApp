package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.common.CommonUtils;
import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.dto.ChangeBookStatusDto;
import com.example.MyBookShopApp.data.dto.SearchWordDto;
import com.example.MyBookShopApp.data.services.BookRepository;
import com.example.MyBookShopApp.data.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Controller
public class BooksController {
    private final BookService bookService;
    private final ResourceStorage resourceStorage;
    private final BookRepository bookRepository;

    @Autowired
    public BooksController(BookService bookService, ResourceStorage resourceStorage, BookRepository bookRepository) {
        this.bookService = bookService;
        this.resourceStorage = resourceStorage;
        this.bookRepository = bookRepository;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    // Обработка запроса на получение контента книги
    @GetMapping(value = "/books/{id}")
    public String bookPage(@PathVariable(value = "id") Integer id,
                           @CookieValue(value = "cartContents", required = false) String cartContents,
                           HttpServletRequest request,
                           Model model) {
        Logger.getLogger(this.getClass().getName()).info("Получение контента книги id: " + id + ", cartContents: " + cartContents);

        BookEntity book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("tags", book.getTagsByBook());
        model.addAttribute("countBooksInCart", CommonUtils.getCountBooksInCookie(request, "cartContents"));
        model.addAttribute("countBooksPostponed", CommonUtils.getCountBooksInCookie(request, "postponedContents"));
        return "/books/slug";
    }

    // Обработка запроса на сохранение обложки книги
    @PostMapping(value = "/books/{id}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) throws IOException {

        String savePath = resourceStorage.saveNewBookImage(file, id);
        BookEntity book = bookService.getBookById(id);
        book.setImage(savePath);
        bookRepository.save(book);

        return "redirect:/books/" + id;
    }

    // Обработка запроса на загрузку книги
    @GetMapping("/books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = resourceStorage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("Путь к файлу с книгой: " + path);

        MediaType mediaType = resourceStorage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("Тип MIME файла: " + mediaType);

        byte[] data = resourceStorage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("Размер файла в байтах: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }

    // Изменение статуса книги
    @PostMapping("/books/changeBookStatus/{id}")
    @ResponseBody
    public ChangeBookStatusDto handleChangeBookStatus(@PathVariable("id") Integer id,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Model model) {

        Logger.getLogger(this.getClass().getName()).info("Обновление статуса книги id: " + id);

        String status = request.getParameter("status");
        if ((status != null) && !status.equals("")) {
            if (status.equals("CART")) {
                // Покупка книги
                Logger.getLogger(this.getClass().getName()).info("Покупка книги id: " + id);
                CommonUtils.addBookToCookie(request, response, "cartContents", id);
                model.addAttribute("isCartEmpty", false);
                return new ChangeBookStatusDto(true, "");

            } else if (status.equals("UNLINK")) {
                // Удаление книги из корзины
                Logger.getLogger(this.getClass().getName()).info("Удаление из корзины книги id: " + id);
                Boolean isEmpty = CommonUtils.removeBookFromCookie(request, response, "cartContents", id);
                model.addAttribute("isCartEmpty", isEmpty);
                return new ChangeBookStatusDto(true, "");

            } else if (status.equals("KEPT")) {
                // Отложить книгу
                Logger.getLogger(this.getClass().getName()).info("Отложить книгу id: " + id);
                CommonUtils.addBookToCookie(request, response, "postponedContents", id);
                return new ChangeBookStatusDto(true, "");

            } else if (status.equals("UNLINKPOST")) {
                // Удаление книги из списка отложенных книг
                Logger.getLogger(this.getClass().getName()).info("Удаление из списка отложенных книги id: " + id);
                CommonUtils.removeBookFromCookie(request, response, "postponedContents", id);
                return new ChangeBookStatusDto(true, "");

            } else {
                Logger.getLogger(this.getClass().getName()).info("Неизвестный статус: " + status);
                return new ChangeBookStatusDto(false, "Неизвестный статус: " + status);
            }
        }

        return new ChangeBookStatusDto(false, "Новый статус не определен!");
    }

    @PostMapping("/rateBook")
    @ResponseBody
    public ChangeBookStatusDto handleChangeBookStatus(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Model model) {

        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Integer value = Integer.valueOf(request.getParameter("value"));
        Logger.getLogger(this.getClass().getName()).info("Установка рейтинга: " + value + " для книги id: " + bookId);



        return new ChangeBookStatusDto(true, "");
    }
}
