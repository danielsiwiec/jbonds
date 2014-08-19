package org.jbonds.bond;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResponseAssessorTest {

    private final ResponseAssessor assessor = new ResponseAssessor();

    @Test
    public void shouldMatchForIdenticalResponses() throws IOException {
        assertThat(assessor.assessResponseMatch("body", "body"), is(true));
    }

}