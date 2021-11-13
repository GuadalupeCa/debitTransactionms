package com.finance.debitTransactionms.repository;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DebitTransactionRepositoryExt extends ReactiveMongoRepository<DebitTransaction, String> {
    Flux<DebitTransaction> findByDebitClientDocumentIdentityNumber(String documentIdentityNumber);
    Flux<DebitTransaction> findByDebitAccount(String account);
}
