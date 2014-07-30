package org.jbonds.helper;

import by.stub.client.StubbyClient;
import by.stub.server.JettyFactory;
import com.google.common.io.Resources;

import java.net.URL;

public class StubbyHelper {

    public static Stubby startStubby(String file) throws Exception {
        final URL url = Resources.getResource(file);
        final int SSL_PORT = 4443;
        StubbyClient stubbyClient = new StubbyClient();
        stubbyClient.startJetty(JettyFactory.DEFAULT_STUBS_PORT, SSL_PORT, JettyFactory.DEFAULT_ADMIN_PORT, url.getFile());
        return new Stubby(stubbyClient, JettyFactory.DEFAULT_STUBS_PORT);
    }

}

