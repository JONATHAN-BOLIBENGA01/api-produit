package com.jbw.jwtAuthentification.service;

import com.jbw.jwtAuthentification.model.Product;
import com.jbw.jwtAuthentification.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Product> getOneProduct(Long id) {
        Optional<Product> product = repository.findById(id); //optional parce que le produit peut ou ne pas exister
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Product> updateProduct(Long id, Product productDetails) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()){
            Product exixtingProduct = product.get();
            exixtingProduct.setName(productDetails.getName());
            exixtingProduct.setPrice(productDetails.getPrice());
            exixtingProduct.setDescription(productDetails.getDescription());

            Product updateProduct = repository.save(exixtingProduct);

            return  new ResponseEntity<>(updateProduct, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            repository.delete(product.get());
           String msg = "product is deleted";
           return new ResponseEntity<>(msg, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
