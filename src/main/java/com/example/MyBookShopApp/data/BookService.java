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

    public List<Book> getBooksData(Integer sign) {
        String sqlGetAllBooks = "select * from books";
        String sqlGetBookSignes = "select * from signsofbooks sb where sb.book_id = ";
        String sqlGetBookDiscount = "select * from discounts d where d.book_id = ";

        List<Book> resultBooksList;

        // Получаем все книги с признаками и скидками
        List<Book> books = jdbcTemplate.query(sqlGetAllBooks, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));

            // Получаем признаки книги
            List<Integer> signs = jdbcTemplate.query(sqlGetBookSignes + book.getId(),
                    (ResultSet rsSign, int rowNumSign) -> {
                        return rsSign.getInt("signs_id");
                    });
            book.setSigns(new ArrayList<>(signs));

            // Из признаков книги отдельно получаем признак "Бестселлер" для упрощения работы с шаблоном
            if (book.getSigns().contains(Integer.valueOf(4))) {
                book.setBestseller(true);
            } else {
                book.setBestseller(false);
            };

            // Получаем скидки книги
            List<Integer> discounts = jdbcTemplate.query(sqlGetBookDiscount + book.getId(),
                    (ResultSet rsDiscount, int rowNumDiscount) -> {
                        return rsDiscount.getInt("discount");
                    });
            if (!discounts.isEmpty()) {
                book.setDiscount(discounts.get(0));
            } else {
                book.setDiscount(0);
            }

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
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));

            return book;
        });

        return new ArrayList<>(books);
    }

    // Получить отложенные книги пользователя
    public List<Book> getPostponed(String user) {
        String sqlGetPostponed = "select * from books b " +
                                 "where exists (select 1 from postponed p " +
                                               "where p.book_id = b.id " +
                                                 "and p.user_name = '" + user + "')";

        // Получаем отложенные книги пользователя
        return new ArrayList<>(getBooksByQuery(sqlGetPostponed));
    }

    // Получить книги пользователя в корзине
    public List<Book> getCart(String user) {
        String sqlGetCart = "select * from books b " +
                "where exists (select 1 from cart c " +
                "where c.book_id = b.id " +
                  "and c.user_name = '" + user + "')";

        // Получаем отложенные книги пользователя
        return new ArrayList<>(getBooksByQuery(sqlGetCart));
    }

    // Подсчитывает общую стоимость списка книг
    public String getTotalPrice(List<Book> bookList, Boolean oldPrice) {
        BigDecimal priceTotal = new BigDecimal(0);

        for (Book book: bookList) {
            if (oldPrice) {
                BigDecimal price = new BigDecimal(book.getPriceOld().substring(1));
                priceTotal = priceTotal.add(price);
            } else {
                BigDecimal price = new BigDecimal(book.getPrice().substring(1));
                priceTotal = priceTotal.add(price);
            }
        }

        return ("$" + String.valueOf(priceTotal));
    }
}