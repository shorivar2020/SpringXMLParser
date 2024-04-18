package xml_parser.handler;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import xml_parser.DownloadFileFromUrl;
import xml_parser.IniReader;
import xml_parser.entity.Product;
import xml_parser.service.ProductService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

@Log
@Component
public class FileParserHandler implements CommandLineRunner {
    private static final int ARG_COUNT = 3;
    private final ProductService productService;

    public FileParserHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Get arguments from command line");
        if (args.length == ARG_COUNT) {
            IniReader ini = new IniReader(args[0]);
            String url = ini.getUrl();
            DownloadFileFromUrl downloadFileFromUrl = new DownloadFileFromUrl();
            log.info("Start parsing");
            parse(downloadFileFromUrl.downloadFile(url));
        } else {
            log.warning("An invalid number of arguments was received");
        }
    }

    public void parse(String fileName)
            throws ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            File file = new File(fileName);
            log.info(fileName);
            parser.parse(file, handler); //Parsing file
            HashSet<Product> products = handler.getProducts(); //Take results of parsing
            log.info("XML parser was Success");
            int count = 0;
            for (Product p : products) {
                p.setId((long) count++);
                productService.createProduct(p);
            }
            log.info("All products were saved in DB");
        } catch (SAXException e) {
            log.warning("SAX Exception occurred during parsing");
        } catch (IOException e) {
            log.warning("IO Exception occurred during parsing");
        }
    }
}
