package com.curso.java.springboot.backend.springboot_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.curso.java.springboot.backend.springboot_backend.entities.Product;
import com.curso.java.springboot.backend.springboot_backend.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

// Controlador REST para gestionar productos
public class ProductController {
    // Servicio que maneja la lógica de negocio de productos
    final private ProductService service;

    // Constructor que inyecta el servicio de productos
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Obtiene la lista de todos los productos.
     * 
     * @return ResponseEntity con la lista de productos y estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Obtiene los detalles de un producto por su id.
     * 
     * @param id identificador del producto
     * @return ResponseEntity con el producto encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) {
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo producto.
     * 
     * @param product objeto producto recibido en el cuerpo de la petición
     * @return ResponseEntity con el producto creado y estado 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    /**
     * Actualiza un producto existente por su id.
     * 
     * @param product objeto producto con los nuevos datos
     * @param id      identificador del producto a actualizar
     * @return ResponseEntity con el producto actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()) {
            Product productDb = optionalProduct.orElseThrow();
            productDb.setDescription(product.getDescription());
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productDb));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Elimina un producto por su id.
     * 
     * @param id identificador del producto a eliminar
     * @return ResponseEntity con el producto eliminado o 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Optional<Product> optionalProduct = service.deleteById(id);
        if (optionalProduct.isPresent()) {
            Product productDeleted = optionalProduct.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(productDeleted);
        }
        return ResponseEntity.notFound().build();
    }

}
