package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "conditions")
class Conditions{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "is_new")
    public String IsNew;
    @Column(name = "is_sale")
    public String IsSale;
    @Column(name = "is_out_let")
    public String IsOutlet;
//    public Conditions(Boolean isNew, Boolean isSale, Boolean isOutlet) {
//        IsNew = isNew;
//        IsSale = isSale;
//        IsOutlet = isOutlet;
//    }
}
