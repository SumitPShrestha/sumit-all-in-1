package com.sp.allinone.config.persistance.dao;

import com.sp.allinone.config.RegistryMap;
import com.sp.allinone.config.persistance.PlatformConfiguration;
import com.sp.allinone.config.persistance.PlatformConfigurationHolder;
import com.sp.allinone.config.persistance.ServerPlatform;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by i68052 on 12/19/2016.
 */
@Configuration
@Component
@PropertySource({"classpath:application.properties"})
public class DaoConfig {

    @Autowired
    private Environment env;

    @Bean
    public PlatformConfigurationHolder getPlatformConfigurationHolder() {
        PlatformConfigurationHolder platformConfigurationHolder = new PlatformConfigurationHolder();
        PlatformConfiguration userPlatformConfig = new PlatformConfiguration();
        userPlatformConfig.setDatabaseDriver(env.getProperty("db.driver"));
        userPlatformConfig.setDatabasePassword(env.getProperty("db.password"));
        userPlatformConfig.setDatabaseUrl(env.getProperty("db.url"));
        userPlatformConfig.setDatabaseUserName(env.getProperty("db.username"));
        platformConfigurationHolder.addPlatformConfiguration(ServerPlatform.SP_USER, userPlatformConfig);
        return platformConfigurationHolder;
    }

    @Bean(name = "scrumDevDataSource", destroyMethod = "close")
    @Primary
    public DataSource getSpUserDataSource() {
        BasicDataSource ds = new BasicDataSource();
        PlatformConfigurationHolder platformConfigurationHolder = getPlatformConfigurationHolder();
        PlatformConfiguration platformConfiguration = platformConfigurationHolder.getPlatformConfiguration(ServerPlatform.SP_USER);
        ds.setDriverClassName(platformConfiguration.getDatabaseDriver());
        ds.setUrl(platformConfiguration.getDatabaseUrl());
        ds.setUsername(platformConfiguration.getDatabaseUserName());
        ds.setPassword(platformConfiguration.getDatabasePassword());
        return ds;
    }

    @Bean(name = "userJdbcTemplate")
    public SimpleJdbcTemplate getScrumDevJdbcTemplate() {
        SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(getSpUserDataSource());

        RegistryMap.getInstance().put("USER_JDBC_TEMPLATE", jdbcTemplate);
        return jdbcTemplate;

    }
}
