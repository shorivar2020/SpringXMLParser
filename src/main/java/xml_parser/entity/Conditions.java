package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Conditions{
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Boolean isNew;
    private Boolean isSale;
    private Boolean isOutlet;
    private String startData;
}
