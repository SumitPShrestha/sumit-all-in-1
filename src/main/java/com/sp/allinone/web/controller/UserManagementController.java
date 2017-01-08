package com.sp.allinone.web.controller;

import com.sp.allinone.config.layout.Layout;
import com.sp.allinone.config.web.AbsctractController;
import com.sp.allinone.service.UserService;
import com.sp.allinone.service.impl.MailServiceImpl;
import com.sp.allinone.web.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static com.sp.allinone.web.Routes.*;


@Layout(value = "layouts/master")
@Controller
@RequestMapping(USER_MANAGEMENT)
class UserManagementController extends AbsctractController {
    @Autowired
    MailServiceImpl mailService;
    @Autowired
    UserService userService;

    @RequestMapping(CREATE_USER)
    ModelAndView welcome(ModelAndView mv, Principal currentUser) {
        String name[] = currentUser.getName().split("@");
        mv.setViewName("pages/welcome");
        return mv;
    }

    @RequestMapping(SUPER_ADMIN_DASHBOARD)
    ModelAndView login(ModelAndView mv, Principal currentUser) {

        mv.addObject("user", currentUser);
        mv.setViewName("pages/super-admin-dashboard");
        return mv;
    }

    @RequestMapping(CLIENT_ADMIN_DASHBOARD)
    ModelAndView dashboard1(ModelAndView mv, Principal currentUser) {
        mv.addObject("user", currentUser);
        mv.setViewName("pages/client-admin-dashboard");
        return mv;
    }

    @RequestMapping(APP_ADMIN_DASHBOARD)
    ModelAndView dashboard2(ModelAndView mv, Principal currentUser) {
        mv.addObject("user", currentUser);
        mv.setViewName("pages/app-admin-dashboard");
        return mv;
    }

    @RequestMapping(EMPLOYEE_DASHBOARD)
    ModelAndView dashboard3(ModelAndView mv, Principal currentUser) {
        mv.addObject("user", currentUser);
        mv.setViewName("pages/employee-dashboard");
        return mv;
    }

    @RequestMapping(Routes.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

}
