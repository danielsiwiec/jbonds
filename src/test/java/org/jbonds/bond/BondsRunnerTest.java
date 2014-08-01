package org.jbonds.bond;

import com.google.common.io.Resources;
import org.jbonds.helper.Stubby;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.StubbyHelper.startStubby;

public class BondsRunnerTest {

    private static Stubby stubby;

    @BeforeClass
    public static void init() throws Exception {
        stubby = startStubby("bonds/get/stubby.yaml");
    }

    @AfterClass
    public static void close() throws Exception {
        stubby.getStubbyClient().stopJetty();
    }

    @Test
    public void shouldPassIfGetBondMatches() throws IOException {
        URL bondLocation = Resources.getResource("bonds/get/passing.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:" + stubby.getPort());
        boolean result = bondsRunner.checkBond(bondLocation);
        assertThat(result, is(true));
    }

    @Test
    public void shouldFailIfGetBondMismatchesResponse() throws IOException {
        URL bondLocation = Resources.getResource("bonds/get/failingResponse.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:" + stubby.getPort());
        boolean result = bondsRunner.checkBond(bondLocation);
        assertThat(result, is(false));
    }

    @Test
    public void shouldFailIfGetBondMismatchesStatus() throws IOException {
        URL bondLocation = Resources.getResource("bonds/get/failingStatus.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:" + stubby.getPort());
        boolean result = bondsRunner.checkBond(bondLocation);
        assertThat(result, is(false));
    }

    @Test
    public void shouldPassIfPostBondMatches() throws IOException {
        URL bondLocation = Resources.getResource("bonds/get/passing.json");
        BondRunner bondsRunner = new BondRunner("http://localhost:" + stubby.getPort());
        boolean result = bondsRunner.checkBond(bondLocation);
        assertThat(result, is(true));
    }
}
