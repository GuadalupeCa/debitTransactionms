package com.finance.debitTransactionms.handler;

import com.finance.debitTransactionms.domain.document.DebitTransaction;
import com.finance.debitTransactionms.service.DebitTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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

    public Mono save(ServerRequest serverRequest) {
        Mono<DebitTransaction> credit = serverRequest.bodyToMono(DebitTransaction.class);
        log.info("Update credit");
        return credit.flatMap(p -> ServerResponse
                .status(HttpStatus.CREATED)
                .body(debitTransactionService.save(p), DebitTransaction.class));
    }

    public Mono update(ServerRequest serverRequest) {
        Mono<DebitTransaction> credit = serverRequest.bodyToMono(DebitTransaction.class);
        log.info("Update credit");
        return credit.flatMap(p -> ServerResponse
                .status(HttpStatus.CREATED)
                .body(debitTransactionService.save(p), DebitTransaction.class));
    }

    public Mono deleteById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        log.info("delete credit by id");
        return debitTransactionService.deleteById(id).then(ServerResponse.noContent().build());
    }
}
