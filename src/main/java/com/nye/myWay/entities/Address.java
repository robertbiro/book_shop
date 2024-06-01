package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // which means the database will automatically generate the primary key.
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    @OneToOne
    @JoinColumn(name = "user_id") //Specifies the foreign key column
    private ApplicationUser applicationUser;
}
