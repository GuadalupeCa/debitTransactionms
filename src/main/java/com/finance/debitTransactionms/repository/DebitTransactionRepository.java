package com.finance.debitTransactionms.repository;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitTransactionRepository {
    Flux<DebitTransaction> findAll();
    Mono<DebitTransaction> findById(String id);
    Mono<DebitTransaction> save(DebitTransaction debit);
    Mono<Void> deleteById(String id);
    Flux<DebitTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber);
    Flux<DebitTransaction> findByAccount(String account);
}
