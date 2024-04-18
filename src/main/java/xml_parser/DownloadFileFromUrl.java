package xml_parser;

import lombok.Getter;
import lombok.extern.java.Log;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Getter
@Log
public class DownloadFileFromUrl {
    private static final String FILE_NAME = "information.xml";
    public String downloadFile (String url) throws Exception{
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        log.info("File was download");
        return FILE_NAME;
    }
}
