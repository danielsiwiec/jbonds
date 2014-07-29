package org.jbonds.test;

import com.google.common.io.Resources;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.jetty.StaticServer.startServer;

public class BondsRunnerTest {

    @BeforeClass
    public static void initServer() throws Exception {
        startServer("request", "GET response");
    }

    @Test
    public void shouldPassIfGetBondMatches() throws IOException {
        URL contract = Resources.getResource("contracts/simpleGetBond.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:8080");
        boolean result = bondsRunner.checkGetBonds(contract);
        assertThat(result, is(true));
    }
 
    @Test
    @Ignore
    public void shouldPassIfPostBondMatches() throws IOException {
        URL contract = Resources.getResource("contracts/simplePostBond.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:8080");
        boolean result = bondsRunner.checkGetBonds(contract);
        assertThat(result, is(true));
    }
}
