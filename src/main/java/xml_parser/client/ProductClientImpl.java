package xml_parser.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xml_parser.entity.Product;
import xml_parser.entity.ProductsDTO;

import java.util.List;
import java.util.Objects;

@Component
public class ProductClientImpl implements ProductClient {
    private static final String URL = "information.xml";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getCourses() {
        ProductsDTO response = restTemplate.getForObject(URL, ProductsDTO.class);
        return Objects.requireNonNull(response).getProducts().stream()
                .peek(x -> x.setName(x.getName().replace(",", ".")))
                .toList();
    }
}
