package xml_parser.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Param{
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String name;
    private String value;
    private String unit;
    private String startData;
}