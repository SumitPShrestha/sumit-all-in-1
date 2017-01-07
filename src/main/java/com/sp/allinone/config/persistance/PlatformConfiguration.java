package com.sp.allinone.config.persistance;

import java.io.Serializable;

/**
 * Created by i68052 on 12/19/2016.
 */
public class PlatformConfiguration implements Serializable{
    private String databaseUrl;
    private String databaseDriver;
    private String databaseUserName;
    private String databasePassword;
    private String tempTableSpaceName;
    private String dataDirectoryPath;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseDriver() {
        return databaseDriver;
    }

    public void setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    public String getDatabaseUserName() {
        return databaseUserName;
    }

    public void setDatabaseUserName(String databaseUserName) {
        this.databaseUserName = databaseUserName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getTempTableSpaceName() {
        return tempTableSpaceName;
    }

    public void setTempTableSpaceName(String tempTableSpaceName) {
        this.tempTableSpaceName = tempTableSpaceName;
    }

    public String getDataDirectoryPath() {
        return dataDirectoryPath;
    }

    public void setDataDirectoryPath(String dataDirectoryPath) {
        this.dataDirectoryPath = dataDirectoryPath;
    }


}
