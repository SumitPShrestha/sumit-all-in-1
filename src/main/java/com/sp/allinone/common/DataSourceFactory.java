package com.sp.allinone.common;

import com.sp.allinone.config.persistance.BaseConfigParameter;
import com.sp.allinone.config.persistance.PlatformConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import javax.sql.*;

/**
 * Created by i82298 on 12/30/2016.
 */
public class DataSourceFactory {

    public static DriverManagerDataSource getDataSource(PlatformConfiguration platformConfiguration) {
        Assert.notNull(platformConfiguration, "===========> data Source cannot be null");
        DriverManagerDataSource dataSource1 = new DriverManagerDataSource(platformConfiguration.getDatabaseUrl(),
                platformConfiguration.getDatabaseUserName(),
                platformConfiguration.getDatabasePassword());
        dataSource1.setDriverClassName(platformConfiguration.getDatabaseDriver());
        return dataSource1;

    }


}
