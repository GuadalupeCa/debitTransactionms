package com.finance.debitTransactionms.service;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitTransactionServiceImpl implements DebitTransactionService{

    @Autowired
    private DebitTransactionService debitTransactionService;

    @Override
    public Flux<DebitTransaction> findAll() {
        return debitTransactionService.findAll();
    }

    @Override
    public Mono<DebitTransaction> findById(String id) {
        return debitTransactionService.findById(id);
    }

    @Override
    public Mono<DebitTransaction> save(DebitTransaction debitTransaction) {
        return debitTransactionService.save(debitTransaction);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return debitTransactionService.deleteById(id);
    }

    @Override
    public Flux<DebitTransaction> findByCreditClientDocumentIdentityNumber(String documentIdentityNumber) {
        return debitTransactionService.findByCreditClientDocumentIdentityNumber(documentIdentityNumber);
    }
}
