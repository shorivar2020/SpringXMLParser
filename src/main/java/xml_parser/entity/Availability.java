package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Availability {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String internal;
    private String external;
    private String manufacturer;
    private String startData;
}