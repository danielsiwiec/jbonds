package org.jbonds.contract;

import lombok.Value;

@Value
public class Bond {

    private String request;
    private Method method;
    private String path;
    private Response response;


    public Bond(){
        request = null;
        method = null;
        path = null;
        response = null;
    }
}
