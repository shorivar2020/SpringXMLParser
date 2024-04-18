package xml_parser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import xml_parser.service.ProductService;

@RequiredArgsConstructor
public class ProductParsingController implements ParsingController{
    private final ProductService productService;
    @GetMapping(value = "/getProduct")
    public String getListInformation() {
        return productService.findCourseInfo().toString();
    }
}