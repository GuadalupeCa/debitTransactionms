package com.finance.debitTransactionms.repository.impl;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import com.finance.debitTransactionms.repository.DebitTransactionRepository;
import com.finance.debitTransactionms.repository.DebitTransactionRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class DebitTransactionImpl implements DebitTransactionRepository {
    @Autowired
    private DebitTransactionRepositoryExt debitTransactionRepositoryExt;

    @Override
    public Flux<DebitTransaction> findAll() {
        return debitTransactionRepositoryExt.findAll();
    }

    @Override
    public Mono<DebitTransaction> findById(String id) {
        return debitTransactionRepositoryExt.findById(id);
    }

    @Override
    public Mono<DebitTransaction> save(DebitTransaction debitTransaction) {
        return debitTransactionRepositoryExt.save(debitTransaction);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return debitTransactionRepositoryExt.deleteById(id);
    }

    @Override
    public Flux<DebitTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber) {
        return debitTransactionRepositoryExt.findByDebitClientDocumentIdentityNumber(documentIdentityNumber);
    }

    @Override
    public Flux<DebitTransaction> findByDebitAccount(String account) {
        return debitTransactionRepositoryExt.findByDebitAccount(account);
    }
}
