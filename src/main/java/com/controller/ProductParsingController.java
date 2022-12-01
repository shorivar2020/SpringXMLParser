package com.controller;

import com.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
public class ProductParsingController implements ParsingController{
    private final ProductService productService;

    @GetMapping(value = "/getProduct")
    public String getListInformation() {
        return productService.findCourseInfo().toString();
    }
}