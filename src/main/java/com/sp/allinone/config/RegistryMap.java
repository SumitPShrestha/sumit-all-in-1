package com.sp.allinone.config;

import java.util.HashMap;

/**
 * Created by i82298 on 1/1/2017.
 */
public class RegistryMap extends HashMap<String,Object> {

    private static RegistryMap registryMap;


    public static RegistryMap getInstance() {
        if (registryMap == null) {
            registryMap = new RegistryMap();
        }
        return registryMap;
    }

}
