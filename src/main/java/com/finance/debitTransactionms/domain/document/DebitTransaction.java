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
    private Double amount;
    private TypeTransaction typeTransaction;
    private String track;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public DebitTransaction(Debit debit, Double amount, TypeTransaction typeTransaction, String track, Date createAt) {
        this.debit = debit;
        this.amount = amount;
        this.typeTransaction = typeTransaction;
        this.track = track;
        this.createAt = createAt;
    }
}
