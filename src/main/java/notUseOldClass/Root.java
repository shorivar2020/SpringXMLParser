//package notUseOldClass;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.*;
//
///*Struct of Product*/
//@Getter
//@Setter
//public class Root {
//    private Integer ID;
//    private String EAN;
//    private String PartNumber;
//    private String Name;
//    private String Title;
//    private String Language;
//    private Integer ITEMGROUP_ID;
//    private String Manufacturer;
//    private String Supplier;
//    private String CountryOfOrigin;
//    private String MeasureUnit;
//    private Map<String, String> RecommendedRetailPriceWithVat = new HashMap<>();
//    private String VATRate;
//    private ArrayList<String> VATCountry = new ArrayList<>();
//    private ArrayList<String> ShortDescription = new ArrayList<>();
//    private ArrayList<String> LargeDescription = new ArrayList<>();
//    //Documents
//    private ArrayList<String> ImageLink  = new ArrayList<>();
//    private HashSet<String> AdditionalImageLink = new HashSet<>();
//    private HashMap<String, String> Document = new HashMap<>();
////    private Certificate certificates;
//    private String Video;
////    private Availability availability;
//    private String Guarantee;
//    private String GuaranteeType;
////    private Conditions Conditions;
//    private String Season;
////    private HashSet<Param> Param  = new HashSet<>();
//    private String DocumentName;
//    private String DocumentLink;
//
////    public void setUndefinedData(String tag, String data) {
////        this.undefinedData.put(tag, data);
////    }
////
////    private HashMap<String, String> undefinedData = new HashMap<>();
////
////    public void setRecommendedRetailPriceWithVat(String Price, String Currency) {
////        this.RecommendedRetailPriceWithVat.put(Price, Currency);
////    }
////
////    public void setVATCountry(String Country) {
////        this.VATCountry.add(Country);
////    }
////
////
////    public void setShortDescription(String shortDescription) {
////        this.ShortDescription.add(shortDescription);
////    }
////
////    public void setLargeDescription(String largeDescription) {
////        this.LargeDescription.add(largeDescription);
////    }
////
////    public void setImageLink(String imageLink) {
////        this.ImageLink.add(imageLink);
////    }
////
////    public void setAdditionalImageLink(String link) {this.AdditionalImageLink.add(link);}
////
////    public void setCertificates(String Link, String Description, String ImageLink) {
////        this.certificates = new Certificate(Link, Description, ImageLink);
////    }
////
////    public void setAvailability(Integer i, Integer e, Integer m) {
////        this.availability = new Availability(i, e, m);
////    }
////
////    public void setConditions(Boolean New, Boolean Sale, Boolean Outlet) {
////        this.Conditions = new Conditions(New, Sale, Outlet);
////    }
////
////    public void setParam(String Name, String Value, String Unit) {
////        Param param = new Param(Name, Value, Unit);
////        this.Param.add(param);
////    }
//
//}
//
////@Getter
////@Setter
////class Certificate{
////    public String Link;
////    public String Descriptions;
////    public String ImageLink;
////    public Certificate(String link, String descriptions, String imageLink) {
////        this.Link = link;
////        this.Descriptions = descriptions;
////        this.ImageLink = imageLink;
////    }
////}
////
////@Getter
////@Setter
////class Param{
////    public String Name;
////    public String Value;
////    public String Unit;
////    public Param(String name, String value, String unit) {
////        Name = name;
////        Value = value;
////        Unit = unit;
////    }
////}
////
////@Getter
////@Setter
////class Conditions{
////    public Boolean IsNew;
////    public Boolean IsSale;
////    public Boolean IsOutlet;
////    public Conditions(Boolean isNew, Boolean isSale, Boolean isOutlet) {
////        IsNew = isNew;
////        IsSale = isSale;
////        IsOutlet = isOutlet;
////    }
////}

//@Getter
//@Setter
//class Availability{
//    public Integer internal;
//    public Integer external;
//    public Integer manufacturer;
//    public Availability(Integer internal, Integer external, Integer manufacturer) {
//        this.internal = internal;
//        this.external = external;
//        this.manufacturer = manufacturer;
//    }
//}
