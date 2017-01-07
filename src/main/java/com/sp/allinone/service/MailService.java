package com.sp.allinone.service;

import com.sp.allinone.service.impl.MailServiceImpl;

import javax.mail.MessagingException;

/**
 * Created by i82298 on 1/1/2017.
 */


public interface MailService {
//    public void configureMailServer(String username, String password);

    public void sendMail(String from, String to, String subject, String content ) throws MessagingException, MessagingException;

//    public void sendMail(EmailDTO emailDTO) throws MessagingException;
}