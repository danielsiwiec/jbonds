package org.jbonds.helper;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class ResourceReader {

    public static String resourceAsString(String file) throws IOException {
        return Resources.toString(Resources.getResource(file), Charsets.UTF_8);
    }
}
