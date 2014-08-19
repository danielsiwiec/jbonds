package org.jbonds.contract;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Response {

    private String body;
    private int status;

    public Response(){
        body = null;
        status = 0;
    }

}
