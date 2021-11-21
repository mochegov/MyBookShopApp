package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserTypeEntity;
import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private Book2UserTypeRepository book2UserTypeRepository;

    @Autowired
    public BookService(BookRepository bookRepository, Book2UserTypeRepository book2UserTypeRepository) {
        this.bookRepository = bookRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
    }

    // Получить новые книги. Считаем, что новые - это те, которые изданы (дата публикации) с начала 2020 года
    public List<BookEntity> getRecentBooks(){
        List<BookEntity> bookEntities = bookRepository.findBookEntityWithDateOfPublicAfter2020();

        return bookEntities;
    }

    // Получить рекомендуемые книги. Считаем, что, если стоимость книги превышает 300 руб., то мы будем ее рекомендовать.
    public List<BookEntity> getRecomendedBooks() {
        List<BookEntity> bookEntities = bookRepository.findByPriceAfter(300);

        return bookEntities;
    }

    // Получить популярные книги
    public List<BookEntity> getPopularBooks() {
        List<BookEntity> bookEntities = bookRepository.findPopularBook();

        return bookEntities;
    }

    // Получить все книги
    public List<BookEntity> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        return bookEntities;
    }

    // Получить отложенные книги пользователя
    public List<BookEntity> getPostponed(UserEntity userEntity) {
        Book2UserTypeEntity book2UserType = book2UserTypeRepository.getFirstByCode("KEPT");
        List<BookEntity> bookEntities = bookRepository.findBooksByUser(book2UserType.getId(), userEntity.getId());
        return new ArrayList<>(bookEntities);
    }

    // Получить книги пользователя в его корзине
    public List<BookEntity> getCart(UserEntity userEntity) {
        Book2UserTypeEntity book2UserType = book2UserTypeRepository.getFirstByCode("CART");
        List<BookEntity> bookEntities = bookRepository.findBooksByUser(book2UserType.getId(), userEntity.getId());
        return new ArrayList<>(bookEntities);
    }

    // Подсчитывает общую стоимость списка книг
    public Integer getTotalPrice(List<BookEntity> bookList, Boolean oldPrice) {
        Integer priceTotal = new Integer(0);

        for (BookEntity book: bookList) {
            if (oldPrice) {
                priceTotal = priceTotal + book.getOldPrice();
            } else {
                priceTotal = priceTotal + book.getPrice();
            }
        }

        return priceTotal;
    }
}