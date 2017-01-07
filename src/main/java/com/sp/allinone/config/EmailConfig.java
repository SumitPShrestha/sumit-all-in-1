package com.sp.allinone.config;

import com.sp.allinone.config.persistance.PlatformConfigurationHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by i82298 on 12/31/2016.
 */
@Configuration
public class EmailConfig {
    private static final String HOST = "mail.host";
    private static final java.lang.String PORT = "mail.port";
    private static final java.lang.String PROTOCOL = "mail.protocol";
    private static final java.lang.String USERNAME = "mail.username";
    private static final java.lang.String PSWD = "mail.password";


    @Autowired
    private  Environment environment;

    @Bean
    public JavaMailSenderImpl conFigureMail() throws IOException {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
        mailSender.setUsername(this.environment.getProperty(USERNAME));
        mailSender.setPassword(this.environment.getProperty(PSWD));

        // JavaMail-specific mail sender configuration, based on javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.timeout", 100000);
        javaMailProperties.put("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
