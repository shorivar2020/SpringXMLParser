package xml_parser.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import xml_parser.client.ProductClientImpl;
import xml_parser.entity.Product;
import xml_parser.repository.ProductRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductClientImpl productClient;

    public void createProduct(Product p)
    {
        productRepository.save(p);
    }
    public List<Product> findCourseInfo(){
        return productRepository.saveAll(productClient.getCourses());
    }
}