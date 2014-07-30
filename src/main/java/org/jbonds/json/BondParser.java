package org.jbonds.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbonds.contract.Bond;

import java.io.IOException;

public class BondParser {

    public static Bond parse(String content) throws IOException {
        return new ObjectMapper().readValue(content, Bond.class);
    }
}
