package com.curso.java.springboot.backend.springboot_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.java.springboot.backend.springboot_backend.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
