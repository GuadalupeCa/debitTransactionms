package com.finance.debitTransactionms.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debit {
    @Id
    private String id;

    private Client client;
    private List<Client> representatives;
    private List<Client> signers;
    private String account;
    private Product product;
    private double balance;
    private double initialBalance;
    private double accruedInterest;
    private double interestEarned;
    private double annualEffectiveRate;
    private double annualEffectiveRateReturn;
    private Integer numberRepresentatives;
    private Integer numberSigners;
    private String currency;
    private boolean status = true;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cancelAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastMovement;

}
