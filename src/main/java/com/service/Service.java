package com.service;

import com.entity.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;


public interface Service {
    public void createProduct(Product p);
    public List<Product> findCourseInfo();
}
