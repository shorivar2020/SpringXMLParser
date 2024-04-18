package xml_parser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ParsingController {
    @GetMapping(value = "/getProduct")
    String getListInformation();
}
