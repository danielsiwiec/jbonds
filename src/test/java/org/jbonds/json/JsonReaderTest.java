package org.jbonds.json;

import org.jbonds.contract.Bond;
import org.jbonds.contract.Method;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.ResourceReader.resourceAsString;

public class JsonReaderTest {

    @Test
    public void shouldReadGetBond() throws IOException {
        Bond bond = JsonReader.parse(resourceAsString("contracts/simpleGetBond.json"));
        assertThat(bond.getResponse(), is("GET response"));
        assertThat(bond.getMethod(), is(Method.GET));
    }

    @Test
    public void shouldReadPostBond() throws IOException {
        Bond bond = JsonReader.parse(resourceAsString("contracts/simplePostBond.json"));
        assertThat(bond.getRequest(), is("POST request"));
        assertThat(bond.getResponse(), is("POST response"));
        assertThat(bond.getMethod(), is(Method.POST));
    }

}
