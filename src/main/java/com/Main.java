package com;

import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;


@lombok.extern.slf4j.Slf4j
@SpringBootApplication
public class Main {
    private static String path;

    @Autowired
    private ProductService Service;

    public static void main(String... args) {
        path = args[0];
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
        ctx.close();
    }

    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods() throws Exception {
        log.info("Read ini file");
        IniReader ini = new IniReader(log, path);
        String url = ini.getUrl();
        log.info("Download xml file");
        DownloadFileFromUrl dffu = new DownloadFileFromUrl(log, url);
        log.info("Parser xml file");
        SAX sax = new SAX();
        sax.SAX(log, dffu.getFileName(), Service);
        log.info("Work was finished");
    }
}
