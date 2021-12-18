package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByNameIn(Collection<String> names) {
        return this.productRepository.findByNameIn(names);
    }

    @Override
    public Optional<Product> create(ProductDto productDto) {
        Optional<Product> productData = productRepository.findByName(productDto.getName());
        if (productData.isEmpty()) {
            Product product = new Product();
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            this.productRepository.save(product);
        }

        return productData;
    }

    @Override
    public Optional<Product> update(ProductDto productDto) {
        System.out.println(productDto.getName());
        Optional<Product> productData = productRepository.findByName(productDto.getName());
        if (productData.isPresent()) {
            Product product = productData.get();
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            this.productRepository.save(product);
        }

        return productData;
    }
}
