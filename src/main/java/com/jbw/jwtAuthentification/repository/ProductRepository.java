package com.jbw.jwtAuthentification.repository;

import com.jbw.jwtAuthentification.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
