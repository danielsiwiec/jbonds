package org.jbonds;

import com.google.common.base.Function;
import org.jbonds.bond.BondExecutor;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.toMap;

public class JBonds {

    private final BondExecutor bondExecutor;
    private List<URL> resources;

    public JBonds(String serverUrl, URL... resources) {
        this.bondExecutor = new BondExecutor(serverUrl);
        this.resources = Arrays.asList(resources);
    }

    public Map<URL, Boolean> run() {
        return toMap(resources, new Function<URL, Boolean>() {
            @Override
            public Boolean apply(URL input) {
                try {
                    return bondExecutor.checkBond(input);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }
}
