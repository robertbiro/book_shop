package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // TODO: 2024. 06. 02.
    //URL
    private String author;
    private String description;
    //Egy könyv mindegyik kiadása és változata (kivéve az utánnyomást) EGYEDI ISBN azonosítót kap
    //ISBN 963-389-831-5
    private String isbn;
    private double price;
    private String publishingDate;
    private String publishingPlace;
    private BookCategory bookCategory;
    private String language;
    private String publisher;
    private int quantity;
}
