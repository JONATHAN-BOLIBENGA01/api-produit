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

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        return service.getProducts();
    }

    @GetMapping("/getOneProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return service.getOneProduct(id);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        return service.updateProduct(id, productDetails);
    }

    @DeleteMapping("/deleteOneProduct/{ide}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return service.deleteProduct(id);
    }


}

