package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileSaver {

    public void fileDownloader() throws IOException {
        URL website = new URL("https://i.redd.it/1gdw0jyb93r71.jpg");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("1gdw0jyb93r71.jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
