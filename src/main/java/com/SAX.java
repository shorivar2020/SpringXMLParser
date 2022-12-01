package com;

import com.Parser.Handler;
import com.entity.Product;
import com.service.ProductService;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/*Parsing XML file through method com.SAX and Insert this information into MYSQL Database*/
public class SAX{

    public void SAX(org.slf4j.Logger log, String fileName, String URL, String USER, String PASS, ProductService Service)
            throws ParserConfigurationException, SAXException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        File file = new File(fileName);
        try{
           log.info("Start XML parser");
            parser.parse(file, handler); //Parsing file
                System.out.println("PARSER END");
            HashSet<Product> products = handler.getProducts(); //Take results of parsing
            log.info("XML parser was Success");
            Iterator<Product> it = products.iterator();
            int count = 0;
            while (it.hasNext()){
                Product p = it.next();
//                System.out.println(p.getVat());
//                System.out.println(p.getRecommendedPrice());
                p.setID((long) count++);
                Service.createProduct(p);
                it.remove();
            }

            log.info("All products was succesfully save in DB");
           // action.destroyConnection(connection); //Close DB
        } catch (SAXException e){
            log.error("OPEN com.SAX Exception in SAXn: ", e);
        } catch (IOException e) {
            log.error("OPEN IO Exception in com.SAX: ", e);
        }
    }
}