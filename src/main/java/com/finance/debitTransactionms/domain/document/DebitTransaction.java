package com.finance.debitTransactionms.domain.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@ToString
@Document(collection = "debitTransaction")
public class DebitTransaction {
    @Id
    private String id;
    private Debit debit;
    private String account;
    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public DebitTransaction(Debit debit, String account, Double amount, Date createAt) {
        this.debit = debit;
        this.account = account;
        this.amount = amount;
        this.createAt = createAt;
    }
}
