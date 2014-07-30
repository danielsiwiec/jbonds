package org.jbonds.helper;

public class UrlBuilder {

    public static String buildUrl(String base, int port, String path){
        return String.format("http://%s:%s%s",base,port,path);
    }
}
