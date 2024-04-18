package xml_parser.entity;

import lombok.Getter;
import lombok.ToString;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@XmlRootElement(name = "B2B")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDTO implements Serializable {
    @XmlElement(name = "Product")
    private List<Product> products;
}


