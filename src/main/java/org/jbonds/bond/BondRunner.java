package org.jbonds.bond;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbonds.contract.Bond;
import org.jbonds.json.BondParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BondRunner {

    private final CloseableHttpClient httpClient;
    private String serverUrl;

    public BondRunner(String serverUrl) {
        this.serverUrl = serverUrl;
        httpClient = HttpClients.createDefault();
    }

    public boolean checkBond(URL bondsLocation) throws IOException {
        String fileContents = FileUtils.readFileToString(new File(bondsLocation.getPath()));
        Bond bond = BondParser.parse(fileContents);
        HttpGet get = new HttpGet(serverUrl + bond.getPath());
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        String response = EntityUtils.toString(httpResponse.getEntity());
        return response.equals(bond.getResponse()) && httpResponse.getStatusLine().getStatusCode() == bond.getStatus();
    }
}
