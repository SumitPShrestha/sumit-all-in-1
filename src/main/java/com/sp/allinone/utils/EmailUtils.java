package com.sp.allinone.utils;

import com.sp.allinone.persistance.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by i82298 on 12/31/2016.
 */
public class EmailUtils {

    public static String getFoggptPasswordEmailContent(String token, User user, HttpServletRequest request) {
        StringBuilder url = new StringBuilder();
        url.append("https://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort()
                ).append("/")
                .append(request.getContextPath())
                .append("/reset-password/")
                .append(token);

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder
                .append("Hello")
                .append(user.getUsername())
                .append(",\n")
                .append("We have received your request to reset your password.")
                .append("please click on the the link <a href=\"")
                .append(url)
                .append("\" >Click Here</a>")
                .append("\n")
                .append("Thank you\n")
                .append(user.getUsername());
        return contentBuilder.toString();

    }
}
