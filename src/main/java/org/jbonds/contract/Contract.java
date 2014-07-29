package org.jbonds.contract;

import lombok.Value;

@Value
public class Contract {

    private String request;
    private String response;

    public Contract(){
        request = "";
        response = "";
    }
}
