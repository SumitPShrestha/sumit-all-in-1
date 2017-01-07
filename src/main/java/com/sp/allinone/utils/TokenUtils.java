package com.sp.allinone.utils;

import com.sp.allinone.persistance.model.User;

import java.util.Date;

/**
 * Created by i82298 on 12/31/2016.
 */
public class TokenUtils {

    public static String createTokenForUser(String token, User user) {
        StringBuilder tokenBuilder = new StringBuilder();
        Date date = new Date();
        tokenBuilder.append(date.getTime())
                .append(":")
                .append(token)
                .append(":")
                .append(user.getUsername());
        return tokenBuilder.toString();
    }
}
