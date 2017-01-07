package com.sp.allinone.config.persistance;

/**
 * Created by i68052 on 12/19/2016.
 */
public class BaseConfigParameter {

    /*SCRUMDEV, SCRUMQA*/
    private ServerPlatform serverPlatform;
    private String schemaName;
    private String appId;
    private String appName;
    private String clientId;
    private String clientName;
    /* Used as a source schema while copying hp and hr tables*/
    private String sourceSchemaName;

//    /* Used as source for CTAS (copying master and QRM config)*/
//    private String sourceCientId;
//    private String sourceAppId;

    public ServerPlatform getServerPlatform() {
        return serverPlatform;
    }

    public void setServerPlatform(
            ServerPlatform serverPlatform) {
        this.serverPlatform = serverPlatform;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSourceSchemaName() {
        return sourceSchemaName;
    }

    public void setSourceSchemaName(String sourceSchemaName) {
        this.sourceSchemaName = sourceSchemaName;
    }

}
