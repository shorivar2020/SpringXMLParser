package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString(includeFieldNames=true)
@Entity
@Table(name = "param")
@Getter
@Setter
class Param{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "name")
    public String Name;
    @Column(name = "value")
    public String Value;
    @Column(name = "unit")
    public String Unit;

}