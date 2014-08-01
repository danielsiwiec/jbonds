package org.jbonds.bond;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbonds.contract.Bond;
import org.jbonds.json.BondParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BondExecutor {

    private final CloseableHttpClient httpClient;
    private String serverUrl;

    public BondExecutor(String serverUrl) {
        this.serverUrl = serverUrl;
        httpClient = HttpClients.createDefault();
    }

    public boolean checkBond(URL bondsLocation) throws IOException {
        String fileContents = FileUtils.readFileToString(new File(bondsLocation.getPath()));
        Bond bond = BondParser.parse(fileContents);

        CloseableHttpResponse httpResponse = null;
        String endpoint = serverUrl + bond.getPath();
        switch (bond.getMethod()) {
            case GET:
                HttpGet get = new HttpGet(endpoint);
                httpResponse = httpClient.execute(get);
                break;
            case POST:
                HttpPost post = new HttpPost(endpoint);
                post.setEntity(new StringEntity(bond.getRequest()));
                httpResponse = httpClient.execute(post);
                break;
        }
        String response = EntityUtils.toString(httpResponse.getEntity());
        return response.equals(bond.getResponse()) && httpResponse.getStatusLine().getStatusCode() == bond.getStatus();
    }
}
