package xml_parser.service;

import xml_parser.entity.Product;
import java.util.List;

public interface ProductService {
     void createProduct(Product p);
    List<Product> findCourseInfo();
}
