package com.nye.myWay.dto;

import com.nye.myWay.entities.BookCategory;
import com.nye.myWay.entities.Cart;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseAdminDTO {

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
