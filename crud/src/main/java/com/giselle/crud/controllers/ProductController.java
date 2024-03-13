package com.giselle.crud.controllers;

import com.giselle.crud.domain.product.CreateProductRequest;
import com.giselle.crud.domain.product.Product;
import com.giselle.crud.domain.product.ProductRepository;
import com.giselle.crud.domain.product.UpdateProductRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productRepository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductRequest product) {
        Product newProduct = new Product(product);
        productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid UpdateProductRequest data) {
        Optional<Product> optionalProduct = productRepository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            /* return ResponseEntity.notFound().build();*/
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional

    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();

        }
    }
}


