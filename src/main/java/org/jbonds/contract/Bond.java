package org.jbonds.contract;

import lombok.Value;

@Value
public class Bond {

    private String request;
    private String response;
    private Method method;
    private String path;
    private int status;

    public Bond(){
        request = null;
        response = null;
        method = null;
        path = null;
        status = 0;
    }
}
