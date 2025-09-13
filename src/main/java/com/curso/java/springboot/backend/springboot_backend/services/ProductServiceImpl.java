package com.curso.java.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.java.springboot.backend.springboot_backend.entities.Product;
import com.curso.java.springboot.backend.springboot_backend.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> deleteById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            repository.deleteById(id);
            ;
            return productOptional;
        }
        return Optional.empty();
    }
}
