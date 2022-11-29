package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "availability")
public
class Availability {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "internal")
    public String internal;
    @Column(name = "external")
    public String external;
    @Column(name = "manufacturer")
    public String manufacturer;
    @Column(name = "start_data")
    private String StartData;
}