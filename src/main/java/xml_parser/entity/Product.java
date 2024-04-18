package xml_parser.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Product{
    @Id
    private Long id;
    private String ean;
    private String partNumber;
    private String name;
    private String title;
    private String language;
    private String itemgroup;
    private String manufacturer;
    private String supplier;
    private String countryOfOrigin;
    private String measureUnit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = RecommendedPrice.class)
    @JoinColumn(name = "recommendedPrice_id")
    private RecommendedPrice recommendedPrice;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Vat.class)
    @JoinColumn(name = "vat_id")
    private Vat vat;
    @Lob
    private String shortDescription;
    @Lob
    private String largeDescription;
    //Documents
    private String imageLink;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = AdditionImageLink.class)
    @JoinColumn(name = "addition_image_link_id")
    private AdditionImageLink additionImageLink;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Document.class)
    @JoinColumn(name = "document_id")
    private Document document;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Certificate.class)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;
    private String video;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Availability.class)
    @JoinColumn(name = "availability_id")
    private Availability availability;
    private String guarantee;
    private String guaranteeType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Conditions.class)
    @JoinColumn(name = "conditions_id")
    private Conditions conditions;
    private String season;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Param.class)
    @JoinColumn(name = "param_id")
    private  Param param;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = UndefinedData.class)
    @JoinColumn(name = "undefined_data_id")
    private UndefinedData undefinedData;
    private String startData;



    public void setParam(String name, String value, String unit){
        param = new Param();
        param.setProductId(this.getId());
        param.setName(name);
        param.setValue(value);
        param.setUnit(unit);
        param.setStartData(this.getStartData());
    }
    public void setVat(String rate, String country){
        vat = new Vat();
        vat.setProductId(this.getId());
        vat.setCountry(country);
        vat.setRate(rate);
        vat.setStartData(this.getStartData());
    }
    public void setRecommendedPrice(String price, String currency){
        recommendedPrice = new RecommendedPrice();
        recommendedPrice.setProductId(this.getId());
        recommendedPrice.setPrice(price);
        recommendedPrice.setCurrency(currency);
        recommendedPrice.setStartData(this.getStartData());
    }
    public void setDocument(String name, String link) {
        document = new Document();
        document.setProductId(this.getId());
        document.setName(name);
        document.setLink(link);
        document.setStartData(this.getStartData());
    }
    public void setConditions(Boolean isNew, Boolean isOutlet, Boolean isSale){
        conditions = new Conditions();
        conditions.setProductId(this.getId());
        conditions.setIsNew(isNew);
        conditions.setIsOutlet(isOutlet);
        conditions.setIsSale(isSale);
        conditions.setStartData(this.getStartData());
    }
    public void setCertificate(String link, String description, String imageLink){
        certificate = new Certificate();
        certificate.setProductId(this.getId());
        certificate.setLink(link);
        certificate.setDescriptions(description);
        certificate.setImageLink(imageLink);
        certificate.setStartData(this.getStartData());
    }
    public void setAvailability(String manufacture, String external, String internal){
        availability = new Availability();
        availability.setProductId(this.getId());
        availability.setManufacturer(manufacture);
        availability.setExternal(external);
        availability.setInternal(internal);
        availability.setStartData(this.getStartData());
    }

    public void  setUndefinedData(String tag){
        undefinedData = new UndefinedData();
        undefinedData.setProductId(this.getId());
        undefinedData.setTag(tag);
        undefinedData.setStartData(this.getStartData());
    }
    public void setAdditionImageLink(String link){
        additionImageLink = new AdditionImageLink();
        additionImageLink.setProductId(this.getId());
        additionImageLink.setLink(link);
        additionImageLink.setStartData(this.getStartData());
    }
}