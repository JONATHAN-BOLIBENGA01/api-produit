package com.jbw.jwtAuthentification.service;

import com.jbw.jwtAuthentification.model.Product;
import com.jbw.jwtAuthentification.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Product> createProduct(Product product) {
       Product productcreated = repository.save(product);
       return new ResponseEntity<>(productcreated, HttpStatus.CREATED);
    }
}
