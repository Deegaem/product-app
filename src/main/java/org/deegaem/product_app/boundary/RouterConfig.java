package org.deegaem.product_app.boundary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> productsRouten(ProductHandler productHandler) {
        return RouterFunctions.route()
                .GET("/products", productHandler::listProducts)
                .GET("/products/{product_id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), productHandler::getProduct)
                .POST("/products", RequestPredicates.contentType(MediaType.APPLICATION_JSON), productHandler::saveProduct)
                .PUT("/products", RequestPredicates.contentType(MediaType.APPLICATION_JSON), productHandler::updateProduct)
                .DELETE("/products/{product_id}", RequestPredicates.accept(MediaType.TEXT_PLAIN), productHandler::deleteProduct)
                .build();
    }
}