package com.nye.myWay.dto;

import com.nye.myWay.entities.BookCategory;
import com.nye.myWay.entities.Cart;
import jakarta.persistence.ManyToOne;

public class BookDTO {

    private String title;
    private String author;
    private String description;
    private String isbn;
    private double price;
    private String publishingDate;
    private String publishingPlace;
    private BookCategory bookCategory;
    private String language;
    private String publisher;
    private int quantity;
    @ManyToOne
    private Cart cart;
}
