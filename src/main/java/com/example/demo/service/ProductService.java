package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Validated
public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductByName(String name);
    List<Product> getProductsByNameIn(Collection<String> names);
    Optional<Product> create(ProductDto product);
    Optional<Product> update(ProductDto productDto);
}
