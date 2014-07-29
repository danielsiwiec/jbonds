package org.jbonds.test;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbonds.contract.Bond;
import org.jbonds.json.JsonReader;

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

    public boolean checkGetBonds(URL bondsLocation) throws IOException {
        String filePath = bondsLocation.getPath();
        String fileContents = FileUtils.readFileToString(new File(filePath));
        Bond bond = JsonReader.parse(fileContents);
        HttpGet get = new HttpGet(serverUrl);
        String response = EntityUtils.toString(httpClient.execute(get).getEntity());
        return response.equals(bond.getResponse());
    }
}
