package com.sp.allinone.web.controller;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.common.SPDTO;
import com.sp.allinone.config.layout.Layout;
import com.sp.allinone.config.persistance.dao.DaoFactory;
import com.sp.allinone.config.web.AbsctractController;
import com.sp.allinone.persistance.model.ClientInfo;
import com.sp.allinone.persistance.model.Role;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserProfile;
import com.sp.allinone.service.UserService;
import com.sp.allinone.service.impl.MailServiceImpl;
import com.sp.allinone.web.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.sp.allinone.web.Routes.*;

@Layout(value = "layouts/master")
@Controller
@RequestMapping(USER_MANAGEMENT) class UserManagementController
        extends AbsctractController {
    @Autowired
    MailServiceImpl mailService;
    @Autowired
    UserService userService;
    SPDTO<User> usersDto = new SPDTO<>();

    @RequestMapping(CREATE_USER) ModelAndView welcome(ModelAndView mv,
            Principal currentUser) {
        User user = new User();
        UserProfile userProfileDto = new UserProfile();
        mv.addObject("myName", "sumit");
        mv.addObject("userProfile", userProfileDto);
        mv.addObject("user", user);
        mv.addObject("createUser", true);
        mv.addObject("clientInfo", new ClientInfo() {
        });
        mv.setViewName("pages/user-management");
        return mv;
    }

    @RequestMapping(value = CREATE_USER, method = RequestMethod.POST) ModelAndView createUser(
            ModelAndView mv, UserProfile userProfileDto, User user) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        userProfileDto.setModifiedBy(auth.getName());
        //        userService.createUser(user);
        //        userService.createUserProfile(userProfileDto);
        mv.addObject("userProfile", userProfileDto);
        mv.setViewName("pages/user-management");
        return mv;
    }

    @RequestMapping(VIEW_USERS) ModelAndView viewUser(ModelAndView mv,
            DataSetParameter dataSetParameter) {
        mv.addObject("createUser", false);
        dataSetParameter.setOrder("ASC");
        dataSetParameter.setPageNo(1);
        dataSetParameter.setPageSize(10);
        dataSetParameter.setOrderField("id");
        //        userService = DaoFactory.getUserDao(dataSetParameter);
        userService.setDataSetParameter(dataSetParameter);
        SPDTO<User> usersDto = userService.findAll();
        mv.addObject("users", usersDto);
        mv.setViewName("pages/user-management");
        return mv;
    }

}
