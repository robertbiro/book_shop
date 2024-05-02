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
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    //@JoinColumn(name = "user_id")
    //private User user;

    //@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    //private List<Bid> bidList;
}
