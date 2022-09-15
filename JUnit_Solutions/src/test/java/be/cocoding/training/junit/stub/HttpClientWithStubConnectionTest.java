package be.cocoding.training.junit.stub;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

@Ignore("do not conflict with other test as this one replace url connection with FAKE")
public class HttpClientWithStubConnectionTest {

    @BeforeClass
    public static void setUpClass() {
        URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
    }

    @Test
    public void testGetPayload_Success() throws Exception {
        HttpClient client = new HttpClient();
        String result = client.getPayload(new URL("http://localhost/any-resource"));
        assertEquals("Hello World !", result);
    }

    private static class StubHttpURLConnection extends HttpURLConnection {
        protected StubHttpURLConnection(URL url) {
            super(url);
        }
        public InputStream getInputStream() {
            return IOUtils.toInputStream("Hello World !", StandardCharsets.UTF_8);
        }
        public void disconnect() {}
        public void connect() {}
        public boolean usingProxy() {
            return false;
        }
    }

    private static class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        public URLStreamHandler createURLStreamHandler(String protocol) {
            return new StubHttpURLStreamHandler();
        }
    }
    private static class StubHttpURLStreamHandler extends URLStreamHandler {
        protected URLConnection openConnection(URL url) {
            return new StubHttpURLConnection(url);
        }
    }

}