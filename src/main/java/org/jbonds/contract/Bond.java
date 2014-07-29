package org.jbonds.contract;

import lombok.Value;

@Value
public class Bond {

    private String request;
    private String response;

    public Bond(){
        request = "";
        response = "";
    }
}
