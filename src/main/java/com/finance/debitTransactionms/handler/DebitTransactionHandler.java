package com.finance.debitTransactionms.handler;

import com.finance.debitTransactionms.domain.document.Debit;
import com.finance.debitTransactionms.domain.document.DebitTransaction;
import com.finance.debitTransactionms.service.DebitTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DebitTransactionHandler {
    @Autowired
    private DebitTransactionService debitTransactionService;

    public Mono findAll(ServerRequest serverRequest) {
        log.info("Find all clients");
        return ServerResponse.ok()
                .body(debitTransactionService.findAll(), DebitTransaction.class);
    }

    public Mono findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        log.info("Find by Id: {}", id);
        return ServerResponse.ok().body(debitTransactionService.findById(id), DebitTransaction.class);
    }

    public Mono saveWithdraw(ServerRequest serverRequest) {
        Map<String, Object> response = new HashMap<>();

        Mono<DebitTransaction> debitTransaction = serverRequest.bodyToMono(DebitTransaction.class);
        log.info("Save withdraw");

        Mono<Debit> debitData = debitTransaction.flatMap(d -> WebClient.builder().build().get()
                .uri("http://localhost:8080/debit/account/{account}", d.getDebit().getAccount())
                .retrieve()
                .bodyToMono(Debit.class));

        return debitTransaction.flatMap(withdraw ->
            debitData.flatMap( debit -> {
                if (withdraw.getAmount() <= debit.getBalance()) {
                    debit.setBalance( debit.getBalance() - withdraw.getAmount());
                    debit.setLastMovement(new Date());
                    return debitTransaction.flatMap(d -> WebClient.builder().build().post()
                                    .uri("http://localhost:8080/debit/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(debit)
                                    .retrieve()
                                    .bodyToMono(Debit.class))
                            .then(ServerResponse
                                    .status(HttpStatus.CREATED)
                                    .body(debitTransactionService.save(withdraw), DebitTransaction.class));
                }
                else {
                    response.put("Message", "No cuenta con suficinete saldo.");
                    return ServerResponse.status(HttpStatus.NO_CONTENT)
                            .body(BodyInserters.fromValue(response));
                }
            })
        );
    }

    public Mono saveDeposit(ServerRequest serverRequest) {
        Map<String, Object> response = new HashMap<>();

        Mono<DebitTransaction> debitTransaction = serverRequest.bodyToMono(DebitTransaction.class);
        log.info("Save withdraw");

        Mono<Debit> debitData = debitTransaction.flatMap(d -> WebClient.builder().build().get()
                .uri("http://localhost:8080/debit/account/{account}", d.getDebit().getAccount())
                .retrieve()
                .bodyToMono(Debit.class));

        return debitTransaction.flatMap(withdraw ->
                debitData.flatMap( debit -> {
                    debit.setBalance( debit.getBalance() + withdraw.getAmount());
                    debit.setLastMovement(new Date());
                    return debitTransaction.flatMap(d -> WebClient.builder().build().post()
                                    .uri("http://localhost:8080/debit/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(debit)
                                    .retrieve()
                                    .bodyToMono(Debit.class))
                            .then(ServerResponse
                                    .status(HttpStatus.CREATED)
                                    .body(debitTransactionService.save(withdraw), DebitTransaction.class));

                })
        );
    }

    public Mono update(ServerRequest serverRequest) {
        Mono<DebitTransaction> debitTransaction = serverRequest.bodyToMono(DebitTransaction.class);
        log.info("Update debit transaction");
        return debitTransaction.flatMap(p -> ServerResponse
                .status(HttpStatus.CREATED)
                .body(debitTransactionService.save(p), DebitTransaction.class));
    }

    public Mono findByDebitAccount(ServerRequest serverRequest) {
        String account = serverRequest.pathVariable("account");
        log.info("Find by Account: {}", account);
        return ServerResponse.ok().body(debitTransactionService.findByDebitAccount(account), DebitTransaction.class);
    }
}
