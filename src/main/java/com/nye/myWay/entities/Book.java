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
    @JoinColumn(name = "cart_id")
    private Cart cart;
}