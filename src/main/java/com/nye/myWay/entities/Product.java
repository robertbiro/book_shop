package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    //private ProductType productType;

    //@OneToMany(mappedBy = "product")
    //private List<Bid> bidList;

    //private Integer currentMaxPrice;

    //@ManyToOne
    //@JoinColumn(name = "buyer_id")
    //private Buyer buyer;

    //@ManyToOne
    //@JoinColumn(name = "seller_id")
    //private Seller seller;

}
