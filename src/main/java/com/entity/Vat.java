package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "vat")
public class Vat {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "rate")
    public String Rate;
    @Column(name = "country")
    public String Country;
    @Column(name = "start_data")
    private String StartData;
}
