package be.cocoding.training.junit.stub;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HttpClientTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
        Server server = new Server(8080);
        Context successContext = new Context(server, "/testGetPayload-Success");
        successContext.setHandler(new TestGetPayloadSuccessHandler());
        Context contentNotFoundContext = new Context(server,"/testGetPayload-NotFound");
        contentNotFoundContext.setHandler(new TestGetPayloadNotFoundHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

    @After
    public void tearDown() {
        // Empty
    }

    @Test
    public void getPayload_Success() throws Exception {
        HttpClient client = new HttpClient();
        String actual = client.getPayload(new URL(
                "http://localhost:8080/testGetPayload-Success"));
        assertEquals("Hello World !", actual);
    }

    @Test
    public void getPayload_NotFound() throws Exception {
        HttpClient client = new HttpClient();
        String actual = client.getPayload(new URL(
                "http://localhost:8080/testGetPayload-NotFound"));
        assertNull(actual);
    }

    private static class TestGetPayloadSuccessHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {
            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer(4096);
            writer.write("Hello World !");
            writer.flush();
            response.setContentLength(writer.size());
            writer.writeTo(out);
            writer.destroy();
            out.flush();
        }
    }

    private static class TestGetPayloadNotFoundHandler extends AbstractHandler {
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException {
            response.sendError(404);
        }
    }
}