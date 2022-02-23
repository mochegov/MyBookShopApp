package com.example.MyBookShopApp.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Общие утилиты
public class CommonUtils {
    // Получить количество книг в Cookie
    public static Integer getCountBooksInCookie(HttpServletRequest request, String cookieName){
        Integer result = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).findFirst();
            if (optionalCookie.isPresent()) {
                String cookieValues = optionalCookie.get().getValue();
                if (!cookieValues.equals("")) {
                    result = optionalCookie.get().getValue().split("/").length;
                }
            }
        }
        return result;
    }

    // Добавляет в Cookie книгу
    public static void addBookToCookie(HttpServletRequest request,
                                       HttpServletResponse response,
                                       String cookieName,
                                       Integer id) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            // Cookies не найдены
            Cookie cookie = new Cookie(cookieName, id.toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            Logger.getLogger(CommonUtils.class.getName()).info("Cookies не найдены. Создаем Cookie [" + cookieName + "], значение: " + cookie.getValue());
        } else {
            // Cookies найдены, проверяем Cookie с именем "cookieName"
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).findFirst();
            if (!optionalCookie.isPresent()) {
                // Cookie с именем cookieName не найдена
                Cookie cookie = new Cookie(cookieName, id.toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                Logger.getLogger(CommonUtils.class.getName()).info("Cookie [" + cookieName + "] отсутствует, создаем. Значение: " + cookie.getValue());
            } else {
                // Cookie с именем cookieName найдена. Добавляем книгу id, если ее нет
                String cookieValue = optionalCookie.get().getValue();
                if (!cookieValue.equals("")){
                    // Cookie не пустая
                    Boolean alreadyExists = Arrays.stream(cookieValue.split("/")).anyMatch(s -> s.equals(id.toString()));
                    if (!alreadyExists){
                        // Данной книги ещё нет в Cookie, добавляем
                        StringJoiner stringJoiner = new StringJoiner("/");
                        stringJoiner.add(cookieValue).add(id.toString());
                        Cookie cookie = new Cookie(cookieName, stringJoiner.toString());
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        Logger.getLogger(CommonUtils.class.getName()).info("Cookie [" + cookieName + "] найдена, но книги " + id +
                                " нет. Новое значение: " + cookie.getValue());
                    } else {
                        Logger.getLogger(CommonUtils.class.getName()).info("Cookie [" + cookieName + "] найдена, книга " + id +
                                " уже есть в Cookie");
                    }
                } else {
                    // Cookie пустая
                    Cookie cookie = new Cookie(cookieName, id.toString());
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    Logger.getLogger(CommonUtils.class.getName()).info("Cookie [" + cookieName + "] найдена и пустая. Новое значение: " + cookie.getValue());
                }
            }
        }
    }

    // Удалает из Cookie книгу
    public static Boolean removeBookFromCookie(HttpServletRequest request,
                                               HttpServletResponse response,
                                               String cookieName,
                                               Integer id) {
        Boolean isEmpty = true; // Содержимое Cookie после удаления книги

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // Cookies найдены, проверяем Cookie с именем "cookieName"
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).findFirst();
            if (optionalCookie.isPresent()) {
                // Cookie с именем cookieName найдена. Удаляем книгу id, если она есть
                String cookieValue = optionalCookie.get().getValue();
                if (cookieValue.contains(id.toString())) {
                    // Книга id есть в Cookie с именем cookieName. Пересоздаем cookie с удалением книги.
                    List<String> cookieBooks = new ArrayList<>(Arrays.asList(cookieValue.split("/")));
                    List<String> cookieBooksNew = cookieBooks.stream()
                        .filter(s -> !s.equals(id.toString()))
                        .collect(Collectors.toList());
                    if (!cookieBooksNew.isEmpty()) {
                        // После удаления книги список книг не пустой
                        String newCookieValue = cookieBooksNew.stream().collect(Collectors.joining("/"));
                        Cookie cookie = new Cookie(cookieName, newCookieValue);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        isEmpty = false;
                        Logger.getLogger(CommonUtils.class.getName()).info("После удаления книги Cookie [" + cookieName +
                                                                                "] не пустая: " + cookie.getValue());
                    } else {
                        // После удаления книги список книг пустой
                        Cookie cookie = new Cookie(cookieName, "");
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        Logger.getLogger(CommonUtils.class.getClass().getName()).info("После удаления книги Cookie [" + cookieName + "] пустая");
                    }
                }
            }
        }

        return isEmpty;
    }
}