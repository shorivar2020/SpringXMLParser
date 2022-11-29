package com;

import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@lombok.extern.slf4j.Slf4j
@SpringBootApplication
public class Main {

    @Autowired
    private ProductService Service;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
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
        SAX sax = new SAX();
        //sax.SAX(log, dffu.getFileName(), urlDB, username, password);
        sax.SAX(log, dffu, urlDB, username, password, Service);
        System.out.println("ALL GOOD");
    }
}
