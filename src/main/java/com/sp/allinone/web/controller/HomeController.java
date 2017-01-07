package com.sp.allinone.web.controller;


import static com.sp.allinone.web.Routes.LOGIN;

import com.sp.allinone.config.layout.Layout;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.service.UserService;
import com.sp.allinone.service.impl.MailServiceImpl;
import com.sp.allinone.utils.EmailUtils;
import com.sp.allinone.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;


@Layout(value = "layouts/plane")
@Controller
@RequestMapping("/auth")
class HomeController {
    @Autowired
    MailServiceImpl mailService;
    @Autowired
    UserService userService;


    @RequestMapping(LOGIN)
    String login(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "pages/login";
    }

    @RequestMapping("/dashboard")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "pages/dashboard";
    }



    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    String dashboard(HttpServletRequest request,String username) throws MessagingException {

        User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        String token = UUID.randomUUID().toString();
        String userToken = TokenUtils.createTokenForUser(token, user);
        userService.saveUserToken(token, user);
        String content = EmailUtils.getFoggptPasswordEmailContent(token, user, request);
        mailService.sendMail("sumitpshrestha@gmail.com", "sumitpshrestha@gmail.com", "Password Reset", content);
        return "pages/login";
    }
/*
    @RequestMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }*/
}
