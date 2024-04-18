package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AdditionImageLink {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String link;
    private String startData;
}
