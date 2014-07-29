package org.jbonds.jetty;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.jetty.StaticServer.startServer;

public class StaticServerTest {

    @BeforeClass
    public static void initServer() throws Exception {
        startServer("request", "response");
    }

    @Test
    public void shouldReturnPreprogrammedResponseOnGet() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8080");
        CloseableHttpResponse httpResponse = httpClient.execute(get);

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(200));
        String actualResponse = EntityUtils.toString(httpResponse.getEntity());
        assertThat(actualResponse, is("response"));
    }

    @Test
    public void shouldReturnPreprogrammedResponseOnExpectedRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080");
        post.setEntity(new StringEntity("request"));
        CloseableHttpResponse httpResponse = httpClient.execute(post);

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(200));
        String actualResponse = EntityUtils.toString(httpResponse.getEntity());
        assertThat(actualResponse, is("response"));
    }

    @Test
    public void shouldReturnErrorOnUnexpectedRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080");
        post.setEntity(new StringEntity("some other request"));
        CloseableHttpResponse httpResponse = httpClient.execute(post);

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(500));
    }
}
