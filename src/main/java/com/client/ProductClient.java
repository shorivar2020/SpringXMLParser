package com.client;

import com.entity.Product;
import com.entity.ProductsDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClient {

    public static final String URL = "information.xml";

    final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getCourses() {
        ProductsDTO response = restTemplate.getForObject(URL, ProductsDTO.class);

        if (response != null) {
            response.getProducts()
                    .forEach(x -> {
                        x.getName().replace(",", ".");
                        System.out.println(x);
                    });

            return response.getProducts();
        }

        return null;
    }
}
