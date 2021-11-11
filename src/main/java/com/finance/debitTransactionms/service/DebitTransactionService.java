package com.finance.debitTransactionms.service;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitTransactionService {
    Flux<DebitTransaction> findAll();
    Mono<DebitTransaction> findById(String id);
    Mono<DebitTransaction> save(DebitTransaction creditTransaction);
    Mono<Void> deleteById(String id);
    Flux<DebitTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber);
}
