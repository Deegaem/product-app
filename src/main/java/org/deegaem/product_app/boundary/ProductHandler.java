package org.deegaem.product_app.boundary;

import org.deegaem.product_app.domain.Product;
import org.deegaem.product_app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component

public class ProductHandler {
    private static final Logger logger
            = LoggerFactory.getLogger(ProductHandler.class);
    private final ProductRepository productRepository;

    public ProductHandler(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Mono<ServerResponse> listProducts(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productRepository.findAll(), Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest request) {
        String id = request.pathVariable("product_id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productRepository.findById(id), Product.class);
    }

    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        return request.bodyToMono(Product.class).flatMap(data -> {
            return ServerResponse.ok().body(productRepository.save(data), Product.class);
        });
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        return request.bodyToMono(Product.class).flatMap(data -> {
            return ServerResponse.ok().body(productRepository.save(data), Product.class);
        });
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        String id = request.pathVariable("product_id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(productRepository.deleteById(id), Product.class);
    }
}

