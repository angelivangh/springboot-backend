package com.curso.java.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import com.curso.java.springboot.backend.springboot_backend.entities.Product;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> deleteById(Long id);
}
