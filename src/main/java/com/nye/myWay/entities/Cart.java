package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Book> books;
}
