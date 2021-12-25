package com.example.MyBookShopApp.data.services;

import com.example.MyBookShopApp.data.author.AuthorEntity;
import com.example.MyBookShopApp.data.book.BookEntity;
import com.example.MyBookShopApp.data.book.links.Book2UserTypeEntity;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.tags.TagEntity;
import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class BookService {

    private BookRepository bookRepository;
    private Book2UserTypeRepository book2UserTypeRepository;

    @Autowired
    public BookService(BookRepository bookRepository, Book2UserTypeRepository book2UserTypeRepository) {
        this.bookRepository = bookRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
    }

    // Получить экземпляр книги по ID
    public BookEntity getBookById(Integer bookId){
        return bookRepository.getOne(bookId);
    }

    // Получение новых книг (дата публикации в течении прошлого года от текущей даты) в постраничном режиме
    // Даты передаются в формате: строка dd.mm.yyyy
    public Page<BookEntity> getPageOfRecentBooks(String fromDate, String toDate, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (fromDate == null) {
            // Если дата начала не задана, используем дату - год назад от текущей даты
            beginDate.add(Calendar.YEAR, - 1);
        } else {
            // Если дата начала задана
            beginDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fromDate.substring(0, 2)));
            beginDate.set(Calendar.MONTH,        Integer.parseInt(fromDate.substring(3, 5)) - 1);
            beginDate.set(Calendar.YEAR,         Integer.parseInt(fromDate.substring(6, 10)));
        }

        if (!(toDate == null)) {
            // Если дата окончания задана
            endDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(toDate.substring(0, 2)));
            endDate.set(Calendar.MONTH,        Integer.parseInt(toDate.substring(3, 5)) - 1);
            endDate.set(Calendar.YEAR,         Integer.parseInt(toDate.substring(6, 10)));
        }

        Logger.getLogger(GenreService.class.getName()).info("getPageOfRecentBooks " +
                "fromDate: "  + fromDate + ", " +
                "toDate: "    + toDate   + ", " +
                "beginDate: " + simpleDateFormat.format(beginDate.getTime()) + ", " +
                "endDate: "   + simpleDateFormat.format(endDate.getTime())
        );

        return bookRepository.findBookByPubDate(beginDate, endDate, nextPage);
    }

    // Получить популярные книги, отсортированные по убыванию рейтинга
    public Page<BookEntity> getPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByRatingIsNotNullOrderByRatingDesc(nextPage);
    }

    // Получение списка рекомендуемых книг с постраничной выдачей данных (на самом деле мы рекомендуем все книги)
    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
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

    public Page<BookEntity> getPageOfSearchResultsBooks(String searchWord, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    // Получение списка книг, относящихся к определенному жанру, с постраничной выдачей данных
    public Page<BookEntity> getPageOfBooksByGenre(GenreEntity genre, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getPageOfBooksByGenreId(genre.getId(), nextPage);
    }

    // Получение списка книг автора с постраничной выдачей данных
    public Page<BookEntity> getPageBooksByAuthor(AuthorEntity author, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getPageBooksByAuthorId(author.getId(), nextPage);
    }

    // Показывает, сколько всего книг у данного автора есть в магазине
    public Integer getCountBookByAuthor(AuthorEntity author){
        return bookRepository.countBookByAuthorId(author.getId());
    }

    // Получение списка книг, относящихся к определенному тэгу, с постраничной выдачей данных
    public Page<BookEntity> getPageOfBooksByTag(TagEntity tag, Integer offset, Integer limit){
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getPageOfBooksByTagId(tag.getId(), nextPage);
    }
}