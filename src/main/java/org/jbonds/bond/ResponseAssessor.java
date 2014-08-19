package org.jbonds.bond;

import java.io.IOException;

public class ResponseAssessor {

    public boolean assessResponseMatch(String actual, String expected) throws IOException {
        return actual.equals(expected);
    }

}