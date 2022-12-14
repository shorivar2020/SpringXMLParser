package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "certificate")
public class Certificate{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_id")
    private Long ProductId;
    @Column(name = "link")
    public String Link;
    @Column(name = "descriptions")
    public String Descriptions;
    @Column(name = "image_link")
    public String ImageLink;
    @Column(name = "start_data")
    private String StartData;
}