package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
