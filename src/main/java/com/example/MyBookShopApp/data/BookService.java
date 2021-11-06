package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Получить признаки книги
    private List<Integer> getSigns(Book book) {
        String sqlGetBookSignes = "select * from book_signes bs where bs.book_id = ";

        List<Integer> signs = jdbcTemplate.query(sqlGetBookSignes + book.getId(),
                (ResultSet rsSign, int rowNumSign) -> {
                    return rsSign.getInt("sign_id");
                });
        return (signs);
    }

    public List<Book> getBooksData(Integer sign) {
        List<Book> resultBooksList;

        String sqlGetAllBooks = "select * from books";
        String sqlGetBookSignes = "select * from book_signes bs where bs.book_id = ";

        // Получаем все книги с признаками и скидками
        List<Book> books = jdbcTemplate.query(sqlGetAllBooks, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            book.setDiscount(rs.getInt("discount"));

            // Получаем признаки книги
            book.setSigns(new ArrayList<>(getSigns(book)));

            // Из признаков книги отдельно получаем признак "Бестселлер" для упрощения работы с шаблоном
            if (book.getSigns().contains(Integer.valueOf(4))) {
                book.setBestseller(true);
            } else {
                book.setBestseller(false);
            };

            return book;
        });

        // Возвращаем только те книги, у которых в списке признаков есть "sign"
        resultBooksList = books.stream()
                .filter(book -> book.getSigns().contains(Integer.valueOf(sign)))
                .collect(Collectors.toList());

        return new ArrayList<>(resultBooksList);
    }

    // Получение списка книг с использованием SQL-запроса
    private List<Book> getBooksByQuery(String sql){

        List<Book> books = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getInt("price_old"));
            book.setPrice(rs.getInt("price"));
            book.setDiscount(rs.getInt("discount"));

            // Получаем признаки книги
            book.setSigns(new ArrayList<>(getSigns(book)));

            return book;
        });

        return (new ArrayList<>(books));
    }

    // Получить отложенные книги пользователя
    public List<Book> getPostponed(User user) {
        String sqlGetPostponed = "select * from books b " +
                                 "where exists (select 1 from postoned_books pb, books_postoned_of_user bpu " +
                                               "where pb.user_id = " + user.getId() + " " +
                                                 "and bpu.postoned_books_id = pb.id " +
                                                 "and bpu.book_id = b.id)";

        // Получаем отложенные книги пользователя
        return new ArrayList<>(getBooksByQuery(sqlGetPostponed));
    }

    // Получить книги пользователя в его корзине
    public List<Book> getCart(User user) {
        String sqlGetCart = "select * from books b " +
                            "where exists (select 1 from carts c, books_in_cart bc " +
                                          "where c.user_id = " + user.getId() + " " +
                                           " and bc.cart_id = c.id " +
                                           " and bc.book_id = b.id)";

        // Получаем отложенные книги пользователя
        return new ArrayList<>(getBooksByQuery(sqlGetCart));
    }

    // Подсчитывает общую стоимость списка книг
    public Integer getTotalPrice(List<Book> bookList, Boolean oldPrice) {
        Integer priceTotal = new Integer(0);

        for (Book book: bookList) {
            if (oldPrice) {
                priceTotal = priceTotal + book.getPriceOld();
            } else {
                priceTotal = priceTotal + book.getPrice();
            }
        }

        return priceTotal;
    }
}