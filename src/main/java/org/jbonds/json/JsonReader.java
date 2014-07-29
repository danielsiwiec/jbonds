package org.jbonds.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbonds.contract.Contract;

import java.io.IOException;

public class JsonReader {

    public static Contract parse(String content) throws IOException {
        return new ObjectMapper().readValue(content, Contract.class);
    }
}
