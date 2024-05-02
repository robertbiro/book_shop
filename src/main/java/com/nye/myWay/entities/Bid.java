package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "product_id")
    //private Product product;

    //@ManyToOne
    //@JoinColumn(name = "buyer_id")
    //private Buyer buyer;

    //private Integer amount;
}
