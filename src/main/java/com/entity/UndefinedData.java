package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "undefined_data")
public class UndefinedData {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "tag")
    public String Tag;
    @Column(name = "data")
    public String Data;
    @Column(name = "start_data")
    private String StartData;
}
