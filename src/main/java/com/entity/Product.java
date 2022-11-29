package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString(includeFieldNames=true)
@Entity
@Table(name = "product")
public class Product{
    @Id
    private Long ID;
    @Column(name = "ean")
    private String EAN;
    @Column(name = "part_number")
    private String PartNumber;
    @Column(name = "doc_name")
    private String Name;
    @Column(name = "title")
    private String Title;
    @Column(name = "doc_language")
    private String Language;
    @Column(name = "item_group_id")
    private String ITEMGROUP_ID;
    @Column(name = "manufacturer")
    private String Manufacturer;
    @Column(name = "supplier")
    private String Supplier;
    @Column(name = "country_of_origin")
    private String CountryOfOrigin;
    @Column(name = "measure_unit")
    private String MeasureUnit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = RecommendedPrice.class)
    @JoinColumn(name = "recommended_price")
    private RecommendedPrice RecommendedPrice;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Vat.class)
    @JoinColumn(name = "vat")
    private Vat vat;
    @Lob
    @Column(name = "short_description")
    private String ShortDescription;
    @Lob
    @Column(name = "large_description")
    private String LargeDescription;
    //Documents
    @Column(name = "image_link")
    private String ImageLink;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = AdditionImageLink.class)
    @JoinColumn(name = "id_addition_image_link")
    private AdditionImageLink additionImageLink;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Document.class)
    @JoinColumn(name = "id_document")
    private Document document;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Certificate.class)
    @JoinColumn(name = "id_certificate")
    private Certificate certificate;
    @Column(name = "video")
    private String video;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Availability.class)
    @JoinColumn(name = "id_availability")
    private Availability availability;
    @Column(name = "guarantee")
    private String Guarantee;
    @Column(name = "guarantee_type")
    private String GuaranteeType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Conditions.class)
    @JoinColumn(name = "id_conditions")
    private Conditions conditions;
    @Column(name = "Season")
    private String season;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Param.class)
    @JoinColumn(name = "id_param")
    private  Param param;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = UndefinedData.class)
    @JoinColumn(name = "id_undefined_data")
    private UndefinedData undefinedData;

    @Column(name = "start_data")
    private String StartData;



    public void setParam(String name, String value, String unit){
        param = new Param();
        param.setProductId(this.ID);
        param.setName(name);
        param.setValue(value);
        param.setUnit(unit);
        param.setStartData(this.StartData);
    }
    public void setVat(String rate, String country){
        vat = new Vat();
        vat.setProductId(this.ID);
        vat.setCountry(country);
        vat.setRate(rate);
        vat.setStartData(this.StartData);
    }
    public void setRecommendedPrice(String price, String currency){
        RecommendedPrice = new RecommendedPrice();
        RecommendedPrice.setProductId(this.ID);
        RecommendedPrice.setPrice(price);
        RecommendedPrice.setCurrency(currency);
        RecommendedPrice.setStartData(this.StartData);
    }
    public void setDocument(String name, String link) {
        document = new Document();
        document.setProductId(this.ID);
        document.setName(name);
        document.setLink(link);
        document.setStartData(this.StartData);
    }
    public void setConditions(String isNew, String isOutlet, String isSale){
        conditions = new Conditions();
        conditions.setProductId(this.ID);
        conditions.setIsNew(isNew);
        conditions.setIsOutlet(isOutlet);
        conditions.setIsSale(isSale);
        conditions.setStartData(this.StartData);
    }
    public void setCertificate(String link, String description, String imageLink){
        certificate = new Certificate();;
        certificate.setProductId(this.ID);
        certificate.setLink(link);
        certificate.setDescriptions(description);
        certificate.setImageLink(imageLink);
        certificate.setStartData(this.StartData);
    }
    public void setAvailability(String Manufacture, String External, String Internal){
        availability = new Availability();
        availability.setProductId(this.ID);
        availability.setManufacturer(Manufacture);
        availability.setExternal(External);
        availability.setInternal(Internal);
        availability.setStartData(this.StartData);
    }

    public void  setUndefinedData(String Tag, String Data){
        undefinedData = new UndefinedData();
        undefinedData.setProductId(this.ID);
        undefinedData.setTag(Tag);
        undefinedData.setData(Data);
        undefinedData.setStartData(this.StartData);
    }
//    public void addAdditionImageLink(String link){
//        additionImageLink.setId(this.ID);
//        additionImageLink.setLink(link);
//    }
}