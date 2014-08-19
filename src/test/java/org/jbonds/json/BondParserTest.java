package org.jbonds.json;

import org.jbonds.contract.Bond;
import org.jbonds.contract.Method;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jbonds.helper.ResourceReader.resourceAsString;

public class BondParserTest {

    @Test
    public void shouldReadGetBond() throws IOException {
        Bond bond = BondParser.parse(resourceAsString("fakes/get.json"));
        assertThat(bond.getResponse().getBody(), is("Person id: 1"));
        assertThat(bond.getMethod(), is(Method.GET));
        assertThat(bond.getPath(), is("/person/1"));
        assertThat(bond.getResponse().getStatus(), is(200));
    }

    @Test
    public void shouldReadPostBond() throws IOException {
        Bond bond = BondParser.parse(resourceAsString("fakes/post.json"));
        assertThat(bond.getRequest(), is("POST request"));
        assertThat(bond.getResponse().getBody(), is("POST response"));
        assertThat(bond.getMethod(), is(Method.POST));
        assertThat(bond.getResponse().getStatus(), is(201));
    }

}
