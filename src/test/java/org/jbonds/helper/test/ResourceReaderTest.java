package org.jbonds.helper.test;

import org.jbonds.helper.ResourceReader;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResourceReaderTest {

    @Test
    public void shouldReadResourceFile() throws IOException {
        String content = ResourceReader.resourceAsString("fakes/test.file");
        assertThat(content, is("Sample content"));
    }
}
