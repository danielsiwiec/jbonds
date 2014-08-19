package org.jbonds;

import com.google.common.collect.ImmutableMap;
import org.jbonds.helper.Stubby;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.StubbyHelper.startStubby;
import static org.jbonds.helper.UrlBuilder.buildLocalUrl;
import static org.junit.Assert.assertThat;

public class JBondsTest {

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
    public void shouldRunAllBondsForList(){
        URL failingPost = getResource("bonds/post/failingResponse.json");
        URL passingGet = getResource("bonds/get/passing.json");
        JBonds jBonds = new JBonds(buildLocalUrl(stubby.getPort()), failingPost, passingGet);
        Map<URL, Boolean> actual = jBonds.run();
        Map<URL, Boolean> expected = ImmutableMap.<URL, Boolean>builder().put(failingPost, false).put(passingGet, true).build();
        assertThat(actual, is(expected));
    }
}
