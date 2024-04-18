package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class UndefinedData {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String tag;
    private String startData;
}
