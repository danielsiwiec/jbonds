package org.jbonds.helper;

import by.stub.client.StubbyClient;
import lombok.Value;

@Value
public class Stubby{

    private StubbyClient stubbyClient;
    private int port;
}
