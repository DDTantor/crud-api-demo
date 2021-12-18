package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> productList = productService.getAllProducts();
            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        try {
            Optional<Product> existingProduct = productService.create(productDto);
            if (existingProduct.isPresent()) {
                return new ResponseEntity<>(productDto, HttpStatus.IM_USED);
            }

            return new ResponseEntity<>(productDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        try {
            Optional<Product> updatedProduct = productService.update(productDto);
            if (updatedProduct.isPresent()) {
                return new ResponseEntity<>(productDto, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
