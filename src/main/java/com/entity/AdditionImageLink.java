package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "addition_image_link")
public class AdditionImageLink {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "link")
    public String link;
    @Column(name = "start_data")
    private String StartData;
}
