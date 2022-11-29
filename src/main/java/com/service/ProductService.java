package com.service;

import com.client.ProductClient;
import com.entity.Product;
import com.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements com.service.Service {

    private final ProductRepository productRepository;
    private final ProductClient productClient;

    public void createProduct(Product p)
    {
        productRepository.save(p);
    }

    public List<Product> findCourseInfo(){
        return productRepository.saveAll(productClient.getCourses());
    }
}