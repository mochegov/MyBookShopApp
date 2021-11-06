package com.example.MyBookShopApp.data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author;
    private String title;
    private Integer priceOld;
    private Integer price;
    private Integer discount;

    @Transient
    private Boolean bestseller;

    @ManyToMany
    @JoinTable(name = "books_in_cart", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private Set<Cart> carts;

    @ManyToMany
    @JoinTable(name = "books_postoned_of_user", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "postoned_books_id"))
    private Set<PostonedBooks> postonedBooks;

    @ManyToMany
    @JoinTable(name = "book_signes", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "sign_id"))
    private Set<Sign> signSet;

    @Transient
    private List<Integer> signs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Sign> getSignSet() {
        return signSet;
    }

    public void setSignSet(Set<Sign> signSet) {
        this.signSet = signSet;
    }

    public List<Integer> getSigns() {
        return signs;
    }

    public void setSigns(List<Integer> signs) {
        this.signs = signs;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<PostonedBooks> getPostonedBooks() {
        return postonedBooks;
    }

    public void setPostonedBooks(Set<PostonedBooks> postonedBooks) {
        this.postonedBooks = postonedBooks;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getBestseller() {
        return bestseller;
    }

    public void setBestseller(Boolean bestseller) {
        this.bestseller = bestseller;
    }
}
