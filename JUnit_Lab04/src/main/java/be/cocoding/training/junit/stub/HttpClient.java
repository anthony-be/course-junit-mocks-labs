package be.cocoding.training.junit.stub;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    public String getPayload(URL url) {
        String payload;
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            payload = IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            payload = null;
        }
        return payload;
    }
}
