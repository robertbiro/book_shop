package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    //@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    //private List<Product> productList;
}
