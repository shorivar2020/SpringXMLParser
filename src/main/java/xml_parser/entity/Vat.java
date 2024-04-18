package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Vat {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String rate;
    private String country;
    private String startData;
}
