package org.jbonds.json;

import org.jbonds.contract.Bond;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.ResourceReader.resourceAsString;

public class JsonReaderTest {

    @Test
    public void shouldReadRequest() throws IOException {
        Bond bond = JsonReader.parse(resourceAsString("fakes/simple.json"));
        assertThat(bond.getRequest(), is("Request body"));
        assertThat(bond.getResponse(), is("Response body"));
    }

}
