package org.jbonds.json;

import org.jbonds.contract.Contract;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.ResourceReader.resourceAsString;

public class JsonReaderTest {

    @Test
    public void shouldReadRequest() throws IOException {
        Contract contract = JsonReader.parse(resourceAsString("fakes/simple.json"));
        assertThat(contract.getRequest(), is("Request body"));
        assertThat(contract.getResponse(), is("Response body"));
    }

}
