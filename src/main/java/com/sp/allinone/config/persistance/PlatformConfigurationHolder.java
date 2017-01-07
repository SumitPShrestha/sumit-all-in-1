package com.sp.allinone.config.persistance;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by i68052 on 12/19/2016.
 */
public class PlatformConfigurationHolder {


    private Map<ServerPlatform, PlatformConfiguration> configurationMap = new HashMap<>();

    public void addPlatformConfiguration(ServerPlatform serverPlatform,
            PlatformConfiguration platformConfiguration) {
        configurationMap.put(serverPlatform, platformConfiguration);

    }

    public PlatformConfiguration getPlatformConfiguration(
            ServerPlatform serverPlatform) {
       return configurationMap.get(serverPlatform);
    }

    public void destroy(){
        configurationMap.clear();
    }

}
