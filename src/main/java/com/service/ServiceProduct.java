package com.service;

import com.entity.Product;
import org.springframework.stereotype.Service;
import java.util.List;


public interface ServiceProduct {
     void createProduct(Product p);
    List<Product> findCourseInfo();
}
