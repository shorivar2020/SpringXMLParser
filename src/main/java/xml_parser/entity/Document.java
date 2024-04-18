package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String name;
    private String link;
    private String startData;
}
