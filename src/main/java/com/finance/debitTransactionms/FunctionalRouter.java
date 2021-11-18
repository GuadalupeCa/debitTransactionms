package com.finance.debitTransactionms;

import com.finance.debitTransactionms.handler.DebitTransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FunctionalRouter {
    @Bean
    public RouterFunction<ServerResponse> route(DebitTransactionHandler debitTransactionHandler) {
        return RouterFunctions
                .route(GET("/debitTrans").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::findAll)
                .andRoute(GET("/debitTrans/account/{account}").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::findByDebitAccount)
                .andRoute(GET("/debitTrans/{id}").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::findById)
                .andRoute(POST("/debitTrans/saveWithdraw").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::saveWithdraw)
                .andRoute(POST("/debitTrans/saveDeposit").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::saveDeposit);
    }
}

