package xml_parser.handler;

import lombok.extern.java.Log;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import xml_parser.entity.Product;

import java.util.*;
import java.util.function.Consumer;

@Log
public class XMLHandler extends DefaultHandler {
    private Product product;
    private final HashSet<Product> products = new HashSet<>();
    private String currentTagName;
    private String curPrice;
    private String certLink;
    private String certDescription;
    private String external;
    private String internal;
    private Boolean isNew;
    private Boolean isSale;
    private String nameParam;
    private String valueParam;
    private String vatRate;
    private String documentName;
    private ArrayList<String> shortDescription;
    private ArrayList<String> largeDescription;

    private final List<String> tagsXML = Arrays.asList("B2B", "Product", "ID", "EAN", "PartNumber",
            "Name", "Name2", "Title", "Language", "ITEMGROUP_ID", "Manufacturer", "Supplier",
            "CountryOfOrigin", "MeasureUnit", "RecommendedRetailPriceWithVat", "Price",
            "Currency", "VAT", "Rate", "Country", "ShortDescription", "LargeDescription", "Documents",
            "AdditionalImageLink", "Document", "Name", "Link", "Certificate", "Description",
            "ImageLink", "Video", "Availability", "internal", "external", "manufacturer",
            "Guarantee", "GuaranteeType", "Conditions", "IsNew", "IsSale", "IsOutlet", "Season",
            "Param", "Name", "Value", "Unit");

    private Boolean isParam;
    private Boolean isDoc;
    private Boolean isShortDescription;
    private Boolean isLargeDescription;
    private Boolean isCertificate;
    private Boolean isAvailability;

    @Override
    public void startDocument() {
        products.clear();
    }

    @Override
    public void endDocument() {/*Don't use*/}

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentTagName = qName;
        switch (qName) {
            case "Product" -> {
                product = new Product();
                String timestamp = String.valueOf(System.currentTimeMillis());
                product.setStartData(timestamp);
            }
            case "Param" -> isParam = true;
            case "Document" -> isDoc = true;
            case "ShortDescription" -> {
                isShortDescription = true;
                shortDescription = new ArrayList<>();
            }
            case "LargeDescription" -> {
                isLargeDescription = true;
                largeDescription = new ArrayList<>();
            }
            case "Availability" -> isAvailability = true;
            case "Certificate" -> isCertificate = true;
            default -> log.fine("Don't recognise start tag");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName != null && isTagHandled(qName)) {
            switch (qName) {
                case "Product" -> products.add(product);
                case "Param" -> isParam = false;
                case "Document" -> isDoc = false;
                case "ShortDescription" -> {
                    isShortDescription = false;
                    setDescriptions(product::setShortDescription, shortDescription);
                }
                case "LargeDescription" -> {
                    isLargeDescription = false;
                    setDescriptions(product::setLargeDescription, largeDescription);
                }
                case "Availability" -> isAvailability = false;
                case "Certificate" -> isCertificate = false;
                default -> log.fine("Don't recognise end tag");
            }
        }
        currentTagName = null;
    }

    private void setDescriptions(Consumer<String> setter, List<String> descriptionList) {
        setter.accept(String.join(", ", descriptionList));
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (currentTagName == null || !isTagHandled(currentTagName)) return;
        switch (currentTagName) {
            case "ID" -> product.setId(Long.parseLong(value));
            case "EAN" -> product.setEan(value);
            case "PartNumber" -> product.setPartNumber(value);
            case "Title" -> product.setTitle(value);
            case "Language" -> product.setLanguage(value);
            case "ITEMGROUP_ID" -> product.setItemgroup(value);
            case "Manufacturer" -> product.setManufacturer(value);
            case "Supplier" -> product.setSupplier(value);
            case "CountryOfOrigin" -> product.setCountryOfOrigin(value);
            case "MeasureUnit" -> product.setMeasureUnit(value);
            case "Price" -> curPrice = value;
            case "Currency" -> product.setRecommendedPrice(curPrice, value);
            case "Rate" -> vatRate = value;
            case "Country" -> product.setVat(vatRate, value);
            case "ShortDescription" -> addDescription(shortDescription, value, isShortDescription);
            case "LargeDescription" -> addDescription(largeDescription, value, isLargeDescription);
            case "Video" -> product.setVideo(value);
            case "Guarantee" -> product.setGuarantee(value);
            case "GuaranteeType" -> product.setGuaranteeType(value);
            case "IsNew" -> isNew = Boolean.valueOf(value);
            case "IsSale" -> isSale = Boolean.valueOf(value);
            case "IsOutlet" -> product.setConditions(isNew, isSale, Boolean.valueOf(value));
            case "Season" -> product.setSeason(value);
            case "AdditionalImageLink" -> product.setAdditionImageLink(value);
            default -> log.warning("Don't recognize tag");
        }
        handleSpecialCases(value);
    }

    private boolean isTagHandled(String tagName) {
        return tagsXML.contains(tagName) || tagName.equals("Name") || tagName.equals("ImageLink") || tagName.equals("Link")
                || tagName.equals("Description") || tagName.equals("internal") || tagName.equals("external")
                || tagName.equals("manufacturer") || tagName.equals("Name") || tagName.equals("Value") || tagName.equals("Unit");
    }

    private void addDescription(List<String> descriptionList, String value, boolean condition) {
        if (condition) descriptionList.add(value);
    }

    private void handleSpecialCases(String value) {
        if (currentTagName.equals("Name") && !isParam) {
            if (isDoc) documentName = value;
            else product.setName(value);
        } else if (currentTagName.equals("ImageLink") && !isCertificate) product.setImageLink(value);
        else if (currentTagName.equals("Link") && !isParam && isDoc) product.setDocument(documentName, value);
        else if (currentTagName.equals("Link") && isCertificate) certLink = value;
        else if (currentTagName.equals("Description") && isCertificate) certDescription = value;
        else if (currentTagName.equals("ImageLink") && isCertificate) product.setCertificate(certLink, certDescription, value);
        else if (currentTagName.equals("internal") && isAvailability) internal = value;
        else if (currentTagName.equals("external") && isAvailability) external = value;
        else if (currentTagName.equals("manufacturer") && isAvailability) product.setAvailability(internal, external, value);
        else if (currentTagName.equals("Name") && isParam) nameParam = value;
        else if (currentTagName.equals("Value") && isParam) valueParam = value;
        else if (currentTagName.equals("Unit") && isParam) product.setParam(nameParam, valueParam, value);
        else product.setUndefinedData(value);
    }

    HashSet<Product> getProducts() {
        return products;
    }

    Product getOnceProduct() {
        return product;
    }
}
