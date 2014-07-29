package org.jbonds.test;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbonds.contract.Contract;
import org.jbonds.json.JsonReader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BondsRunner {

    private final CloseableHttpClient httpClient;
    private String serverUrl;

    public BondsRunner(String serverUrl) {
        this.serverUrl = serverUrl;
        httpClient = HttpClients.createDefault();
    }

    public boolean checkGetBonds(URL bondsLocation) throws IOException {
        String filePath = bondsLocation.getPath();
        String fileContents = FileUtils.readFileToString(new File(filePath));
        Contract contract = JsonReader.parse(fileContents);
        HttpGet get = new HttpGet(serverUrl);
        String response = EntityUtils.toString(httpClient.execute(get).getEntity());
        return response.equals(contract.getResponse());
    }
}