package com.jbw.jwtAuthentification.controller;

import com.jbw.jwtAuthentification.model.Product;
import com.jbw.jwtAuthentification.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        return service.getProducts();
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

}

