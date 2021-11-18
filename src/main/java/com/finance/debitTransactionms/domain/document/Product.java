package com.finance.debitTransactionms.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;
    private String type;

    private Boolean commissionMaintenance;
    private Integer amountCommissionMaintenance;

    private Boolean maximumLimitMonthlyMovements;
    private Integer quantityMaximumLimitMonthlyMovements;

    private Boolean allowDeposit;
    private Boolean allowWithdrawal;

    private Integer maximumQuantityCredit;
    private Integer maximumQuantityDebit;
}
