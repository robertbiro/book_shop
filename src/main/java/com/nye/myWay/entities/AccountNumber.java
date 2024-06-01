package com.nye.myWay.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class AccountNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    //https://www.geeksforgeeks.org/validating-bank-account-number-using-regular-expressions/
    private String accountNumber;
}
