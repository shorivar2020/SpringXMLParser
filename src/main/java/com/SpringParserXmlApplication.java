package com;

import com.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Scanner;

@lombok.extern.slf4j.Slf4j
@SpringBootApplication
public class SpringParserXmlApplication {

    @Autowired
    private ProductService Service;

    public static void main(String[] args) {
        SpringApplication.run(SpringParserXmlApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String path = scanner.nextLine();
//        IniReader ini = new IniReader(path);
//        String url = ini.getUrl();
//        String urlDB = ini.getUrlDB();
//        String username = ini.getUsername();
//        String password = ini.getPassword();
//        DownloadFileFromUrl dffu = new DownloadFileFromUrl(log, url);
        String urlDB = "jdbc:postgresql://localhost:5432/ParserXML";
        String username = "postgres";
        String password = "3400";
        String dffu = "information.xml";
//        Product p = new Product();
//        p.setTitle("test");
//        p.setLanguage("test");
//        p.setEAN("aaaaaa");
//        p.setPartNumber("sssssssssss");
//        p.setName("sssssssss");
//        p.setManufacturer("ssssss");
//        p.setCountryOfOrigin("aaaaaaa");
//        p.setMeasureUnit("aaaaaaa");
//        p.setID(14334567);
//        p.setITEMGROUP_ID(1234);
//        p.setSupplier("ssssssss");
//
//        Service.createProduct(p);
        SAX sax = new SAX();
        //sax.SAX(log, dffu.getFileName(), urlDB, username, password);
        sax.SAX(log, dffu, urlDB, username, password, Service);
//        Product product = new Product();
//        product.setId(123);
//        product.setEAN("12345667");
//        product.setPartNumber("123355678888");
//        product.setName("CoolKnifes");
//        product.setTitle("KNIFEEEES");
//        product.setLanguage("ENG");
//        pService.createProduct(product);
        System.out.println("ALL GOOD");
    }
}
