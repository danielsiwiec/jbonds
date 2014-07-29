package org.jbonds.contract;

import lombok.Value;

@Value
public class Bond {

    private String request;
    private String response;
    private Method method;

    public Bond(){
        request = null;
        response = null;
        method = null;
    }
}
