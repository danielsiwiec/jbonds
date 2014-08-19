package org.jbonds.helper.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.jbonds.helper.Stubby;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.jbonds.helper.StubbyHelper.startStubby;
import static org.jbonds.helper.UrlBuilder.buildLocalUrl;

public class StubbyHelperTest {

    private static Stubby stubby;

    @BeforeClass
    public static void initServer() throws Exception {
        stubby = startStubby("stubby/stubs.yaml");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        stubby.getStubbyClient().stopJetty();
    }

    @Test
    public void shouldMakeSuccessfulGet() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(buildLocalUrl(stubby.getPort(), "/item/1"));
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.OK_200));
        assertThat("{\"id\" : \"1\", \"description\" : \"milk\"}", equalTo(EntityUtils.toString(httpResponse.getEntity())));

    }

    @Test
    public void shouldMakeSuccessfulPost() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(buildLocalUrl(stubby.getPort(), "/item/1"));
        post.setEntity(new StringEntity("post body"));
        CloseableHttpResponse httpResponse = httpClient.execute(post);

        assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.OK_200));
        assertThat("post response", equalTo(EntityUtils.toString(httpResponse.getEntity())));
    }
}
