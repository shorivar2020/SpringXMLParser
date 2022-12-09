package com.service;

import com.client.ProductClient;
import com.entity.Product;
import com.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements ServiceProduct {

    ProductRepository productRepository;
    ProductClient productClient;

    public void createProduct(Product p)
    {
        productRepository.save(p);
    }


    public List<Product> findCourseInfo(){
        return productRepository.saveAll(productClient.getCourses());
    }
}