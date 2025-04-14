package com.jbw.jwtAuthentification.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produits")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private String description;
}
