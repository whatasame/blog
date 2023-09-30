package com.github.whatasame.springdataredis.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }
}
