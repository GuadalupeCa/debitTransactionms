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
                .andRoute(GET("/debitTrans/{id}").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::findById)
                .andRoute(POST("/debitTrans/save").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::save)
                .andRoute(PUT("/debitTrans/update").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::update)
                .andRoute(DELETE("/debitTrans/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), debitTransactionHandler::deleteById);
    }
}

