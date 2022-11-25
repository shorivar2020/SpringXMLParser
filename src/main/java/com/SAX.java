package com;

import com.entity.Product;
import com.service.ProductService;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/*Parsing XML file through method com.SAX and Insert this information into MYSQL Database*/
public class SAX{

    public void SAX(org.slf4j.Logger log, String fileName, String URL, String USER, String PASS, ProductService Service)
            throws ParserConfigurationException, SAXException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
//        Action action = new Action(log);
        File file = new File(fileName);
        try{
//            log.info("Start XML parser");
            parser.parse(file, handler); //Parsing file
                System.out.println("PARSER END");
            //HashMap<Integer, Root> products = handler.getProducts(); //Take results of parsing
            HashSet<Product> products = handler.getProducts(); //Take results of parsing
//            log.info("XML parser was Success");
//            Connection connection = action.getConnection(URL, USER, PASS); //Open DB
//            Statement statement = connection.createStatement();
            //action.dropTables(statement);
            //action.NewProduct(statement); //Create Product Table
            Iterator<Product> it = products.iterator();
            int count = 0;
            while (it.hasNext()){
                Product p = it.next();
                p.setID((long) count++);
                Service.createProduct(p);
                it.remove();
                System.out.println(p.toString());
            }
            System.out.println("Hi");
            //DatabaseInsertData did = new DatabaseInsertData();
            //did.InsertData(log, action, statement, products);
           // action.destroyConnection(connection); //Close DB
        } catch (SAXException e){
//            log.error("OPEN com.SAX Exception in SAXn: ", e);
        } catch (IOException e) {
//            log.error("OPEN IO Exception in com.SAX: ", e);
//        } catch (SQLException throwables) {
//            log.error("SQL Exception in com.SAX: ", throwables);
        }
    }
}