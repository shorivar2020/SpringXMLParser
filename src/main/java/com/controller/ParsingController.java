package com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ParsingController {
    @GetMapping(value = "/getProduct")
    public String getListInformation();
}
