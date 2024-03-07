package com.giselle.crud.controllers;

import com.giselle.crud.domains.product.Product;
import com.giselle.crud.domains.product.ProductRepository;
import com.giselle.crud.domains.product.RequestProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProductDTO data) {
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }
}
