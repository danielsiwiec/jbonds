package org.jbonds.helper;

public class UrlBuilder {

    public static String buildUrl(int port, String path){
        return "http://localhost:" + port + path;
    }

    public static String buildLocalUrl(int port){
        return buildUrl(port, null);
    }
}
