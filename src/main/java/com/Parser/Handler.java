package com.Parser;

import com.entity.Product;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/*Reactions on XML tag by com.SAX*/
public class Handler extends DefaultHandler {

    //private Root root;
    private Product root;
    private HashSet<Product> products;

    private String currentTagName;
    private String CUR_Price;
    private String CUR_Currency;
    private String CertLink;
    private String CertDescription;
    private String external;
    private String internal;
    private String manufacturer;
    private String isNew;
    private String isSale;
    private String isOutlet;
    private String nameParam;
    private String valueParam;
    private String unitParam;
    private String VATRate;
    private String DocumentName;
    private ArrayList<String> ShortDescription;
    private ArrayList<String> LargeDescription;

    public final List<String> tagsXML = Arrays.asList("B2B", "Product", "ID", "EAN", "PartNumber",
            "Name", "Name2", "Title", "Language", "ITEMGROUP_ID", "Manufacturer", "Supplier",
            "CountryOfOrigin", "MeasureUnit", "RecommendedRetailPriceWithVat", "Price",
            "Currency", "VAT", "Rate", "Country", "ShortDescription", "LargeDescription", "Documents",
            "ImageLink", "AdditionalImageLink", "Document", "Name", "Link", "Certificate", "Description",
            "ImageLink", "Video", "Availability", "internal", "external", "manufacturer",
            "Guarantee", "GuaranteeType", "Conditions", "IsNew", "IsSale", "IsOutlet", "Season",
            "Param", "Name", "Value", "Unit");

    private Boolean isParam = false;
    private Boolean isDoc = false;
    private Boolean isShortDescription = false;
    private Boolean isLargeDescription = false;
    private Boolean isCertificate = false;
    private Boolean isAvailability = false;


    public HashSet<Product> getProducts(){
        return products;
    }

    public Product getOnceProduct(){
        return root;
    }

    @Override
    public void startDocument() {  products = new HashSet<>();}

    @Override
    public void endDocument() {
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {
            currentTagName = qName;
        if(qName.equals("Product")) {
            root = new Product();
            Timestamp t = new Timestamp(System.currentTimeMillis());
            String timestamp = String.valueOf(t);
            root.setStartData(timestamp);
        }if(qName.equals("Param")){
            isParam = true;
        }if(qName.equals("Document")){
            isDoc = true;
        }if(qName.equals("ShortDescription")){
            isShortDescription = true;
            ShortDescription = new ArrayList<>();
        }if(qName.equals("LargeDescription")){
            isLargeDescription = true;
            LargeDescription = new ArrayList<>();
        }if(qName.equals("Availability")){
            isAvailability = true;
        }if(qName.equals("Certificate")){
            isCertificate= true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {
        if(qName != null){
            if(qName.equals("Product")){
                products.add(root);
            }if(qName.equals("Param")){
                isParam = false;
            }if(qName.equals("Document")){
                isDoc = false;
            }if(qName.equals("ShortDescription")){
                isShortDescription = false;
                root.setShortDescription(ShortDescription.stream().map(Object::toString)
                        .collect(Collectors.joining(", ")));
            }if(qName.equals("LargeDescription")){
                isLargeDescription = false;
                root.setLargeDescription(LargeDescription.stream().map(Object::toString)
                        .collect(Collectors.joining(", ")));
            }if(qName.equals("Availability")){
                isAvailability = false;
            }if(qName.equals("Certificate")){
                isCertificate= false;
            }
       }
        currentTagName = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String value = new String(ch, start, length);

        if(currentTagName == null) {
            return;
        }
        if(currentTagName.equals("ID")) {
            root.setID(Long.parseLong(value));
        }
            switch (currentTagName){
                case "EAN":
                    root.setEAN((value));
                    break;
                case "PartNumber":
                    root.setPartNumber((value));
                    break;
                case "Title":
                    root.setTitle((value));
                    break;
                case "Language":
                    root.setLanguage((value));
                    break;
                case "ITEMGROUP_ID":
                    root.setITEMGROUP_ID((value));
                    break;
                case "Manufacturer":
                    root.setManufacturer((value));
                    break;
                case "Supplier":
                    root.setSupplier((value));
                    break;
                case "CountryOfOrigin":
                    root.setCountryOfOrigin((value));
                    break;
                case "MeasureUnit":
                    root.setMeasureUnit((value));
                    break;
                case "Price":
                    CUR_Price = value;
                    break;
                case "Currency":
                    CUR_Currency = value;
                    root.setRecommendedPrice(CUR_Price, CUR_Currency);
                    break;
                case "Rate":
                    VATRate = value;
                    break;
                case "Country":
                    root.setVat(VATRate, value);
                    break;
                case "ShortDescription":
                    if(isShortDescription){
                        ShortDescription.add(value);
                    }
                    break;
                case "LargeDescription":
                    if(isLargeDescription) {
                        LargeDescription.add(value);
                    }
                    break;
                case "Video":
                    root.setVideo(value);
                    break;
                case "Guarantee":
                    root.setGuarantee(value);
                    break;
                case "GuaranteeType":
                    root.setGuaranteeType(value);
                    break;
                case "IsNew":
                    isNew = (value);
                    break;
                case "IsSale":
                    isSale = (value);
                    break;
                case "IsOutlet":
                    isOutlet = (value);
                    root.setConditions(isNew, isSale, isOutlet);
                    break;
                case "Season":
                    root.setSeason(value);
                    break;

            }
         if(currentTagName.equals("Name") && !isParam && !isDoc){
            root.setName(value);
        }else if(currentTagName.equals("ImageLink")&& !isCertificate) {
            root.setImageLink(value);
        }else if(currentTagName.equals("Name") && !isParam && isDoc){
            DocumentName = value;
        }else if(currentTagName.equals("Link")&& !isParam && isDoc){
            root.setDocument(DocumentName, value);
        }else if(currentTagName.equals("Link") && isCertificate){
            this.CertLink = value;
        }else if(currentTagName.equals("Description") && isCertificate){
            this.CertDescription = value;
        }else if(currentTagName.equals("ImageLink") && isCertificate){
            root.setCertificate(CertLink, CertDescription, value);
        }else if(currentTagName.equals("internal") && isAvailability){
            internal = (value);
        }else if(currentTagName.equals("external") && isAvailability){
            external = (value);
        }else if(currentTagName.equals("manufacturer") && isAvailability){
            manufacturer = (value);
            root.setAvailability(internal, external, manufacturer);
        }else if(currentTagName.equals("Name") && isParam){
            nameParam = value;
        }else if(currentTagName.equals("Value")&& isParam){
            valueParam = value;
        }else if(currentTagName.equals("Unit")&& isParam){
            unitParam = value;
            root.setParam(nameParam, valueParam, unitParam);

        }else{
            if(!tagsXML.contains(currentTagName)){
                System.out.println(currentTagName + value);
                root.setUndefinedData(currentTagName, value);
            }
        }
    }

}