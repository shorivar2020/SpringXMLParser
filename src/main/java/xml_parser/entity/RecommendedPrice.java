package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class RecommendedPrice {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String price;
    private String currency;
    private String startData;
}
