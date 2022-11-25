package com.controller;

import com.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductParsingController {
    private final ProductService productService;

    @GetMapping(value = "/getProduct")
    public String getListInformation() {
        return productService.findCourseInfo().toString();
    }
}
