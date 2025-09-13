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
    /**
     * Obtiene la lista de todos los productos almacenados en la base de datos.
     * @return Lista de productos
     */
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    /**
     * Busca un producto por su identificador único.
     * @param id Identificador del producto
     * @return Un Optional con el producto si existe, o vacío si no se encuentra
     */
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    /**
     * Guarda un producto en la base de datos. Si el producto ya existe, lo actualiza.
     * @param product Producto a guardar o actualizar
     * @return El producto guardado o actualizado
     */
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    /**
     * Elimina un producto por su identificador único.
     * @param id Identificador del producto a eliminar
     * @return Un Optional con el producto eliminado si existía, o vacío si no se encontró
     */
    public Optional<Product> deleteById(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            repository.deleteById(id);
            return productOptional;
        }
        return Optional.empty();
    }
}
