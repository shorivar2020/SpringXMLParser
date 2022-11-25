package com;

import com.entity.Product;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

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
    private String DocName;
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


//    public HashMap<Integer, Root> getProducts(){
//        return products;
//    }

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
//        if(qName.equals("Product")) {
//            root = new Root();
        if(qName.equals("Product")) {
            root = new Product();
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

        if(currentTagName == null){
            return;
//        }else if(currentTagName.equals("ID")) {
//            root.setID(new Integer(value));
        }else if(currentTagName.equals("EAN")){
            root.setEAN((value));
        }else if(currentTagName.equals("PartNumber")){
            root.setPartNumber((value));
        }else if(currentTagName.equals("Name") && !isParam && !isDoc){
            root.setName(value);
        }else if(currentTagName.equals("Title")){
            root.setTitle((value));
        }else if(currentTagName.equals("Language")){
            root.setLanguage((value));
        }else if(currentTagName.equals("ITEMGROUP_ID")){
            root.setITEMGROUP_ID((value));
        }else if(currentTagName.equals("Manufacturer")){
            root.setManufacturer((value));
        }else if(currentTagName.equals("Supplier")){
            root.setSupplier((value));
        }else if(currentTagName.equals("CountryOfOrigin")){
            root.setCountryOfOrigin((value));
        }else if(currentTagName.equals("MeasureUnit")){
            root.setMeasureUnit((value));
        }else if(currentTagName.equals("Price")){
               CUR_Price = value;
        }else if(currentTagName.equals("Currency")){
            CUR_Currency = value;
//            if(CUR_Price != null && CUR_Currency != null) {
            root.setRecommendedPrice(CUR_Price, CUR_Currency);
//            }
        }else if(currentTagName.equals("Rate")){
            VATRate = value;
        }else if(currentTagName.equals("Country")){
            root.setVat(VATRate, value);
        }else if(currentTagName.equals("ShortDescription")){
            if(isShortDescription){
                ShortDescription.add(value);
            }
        }else if(currentTagName.equals("LargeDescription")){
            if(isLargeDescription) {
                LargeDescription.add(value);
            }
        }else if(currentTagName.equals("ImageLink")&& !isCertificate) {
            root.setImageLink(value);
//        }else if(currentTagName.equals("AdditionalImageLink")){
//            root.addAdditionImageLink(value);
        }else if(currentTagName.equals("Name") && !isParam && isDoc){
            DocumentName = value;
//            root.setDocumentName(value);
        }else if(currentTagName.equals("Link")&& !isParam && isDoc){
            root.setDocument(DocumentName, value);
        }else if(currentTagName.equals("Link") && isCertificate){
            this.CertLink = value;
           // System.out.println(isCertificate);
        }else if(currentTagName.equals("Description") && isCertificate){
            this.CertDescription = value;
            //System.out.println(isCertificate);
        }else if(currentTagName.equals("ImageLink") && isCertificate){
            //System.out.println(isCertificate);
            root.setCertificate(CertLink, CertDescription, value);
        }else if(currentTagName.equals("Video")){
            root.setVideo(value);
        }else if(currentTagName.equals("internal") && isAvailability){
            internal = (value);
        }else if(currentTagName.equals("external") && isAvailability){
            external = (value);
        }else if(currentTagName.equals("manufacturer") && isAvailability){
            manufacturer = (value);
            root.setAvailability(internal, external, manufacturer);
        }else if(currentTagName.equals("Guarantee")){
            root.setGuarantee(value);
        }else if(currentTagName.equals("GuaranteeType")){
            root.setGuaranteeType(value);
        }else if(currentTagName.equals("IsNew")){
            isNew = (value);
        }else if(currentTagName.equals("IsSale")){
            isSale = (value);
        }else if(currentTagName.equals("IsOutlet")){
            isOutlet = (value);
            root.setConditions(isNew, isSale, isOutlet);
        }else if(currentTagName.equals("Season")){
            root.setSeason(value);
        }else if(currentTagName.equals("Name") && isParam){
            nameParam = value;
        }else if(currentTagName.equals("Value")&& isParam){
            valueParam = value;
        }else if(currentTagName.equals("Unit")&& isParam){
            unitParam = value;
            root.setParam(nameParam, valueParam, unitParam);

//        }else{
//            if(!tagsXML.contains(currentTagName)){
//                System.out.println(currentTagName + value);
//                root.setUndefinedData(currentTagName, value);
//            }
        }
    }

}