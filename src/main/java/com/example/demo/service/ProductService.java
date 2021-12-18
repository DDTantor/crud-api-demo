package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(long id);
    Optional<Product> getProductByName(String name);
    Product save(Product product);
}
