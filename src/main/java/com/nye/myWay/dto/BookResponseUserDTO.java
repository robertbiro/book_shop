package com.nye.myWay.dto;

import com.nye.myWay.entities.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseUserDTO {

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
}
