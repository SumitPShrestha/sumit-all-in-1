package com.sp.allinone.config.security;

import com.sp.allinone.common.Role;
import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sp.allinone.web.Routes.*;


/**
 * Created by i82298 on 1/6/2017.
 */

@Configuration
public class AppAuthenticationSuccessHandeler implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
   User userToBeAuthorized;
    private boolean isSuperAdmin;
    private boolean isCientAdmin;
    Role role;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication) throws IOException{
        final String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);

    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(Role.ROLE_SUPERADMIN.name())) {
                role = Role.ROLE_SUPERADMIN;
                break;
            } else if (grantedAuthority.getAuthority().contains(Role.ROLE_CLIENTADMIN.name())) {
                role = Role.ROLE_CLIENTADMIN;
                break;
            }else if (grantedAuthority.getAuthority().contains(Role.ROLE_APPADMIN.name())) {
               role = Role.ROLE_APPADMIN;
                break;
            }else if (grantedAuthority.getAuthority().contains(Role.ROLE_EMPLOYEE.name())) {
                role = Role.ROLE_EMPLOYEE;
                break;
            }

        }

        if (role.equals(Role.ROLE_SUPERADMIN)) {
            return WELCOME_PAGE;
        } else if (role.equals(Role.ROLE_CLIENTADMIN)) {
            return CLIENT_ADMIN_DASHBOARD;
        }else if (role.equals(Role.ROLE_APPADMIN)) {
            return APP_ADMIN_DASHBOARD;
        }else if (role.equals(Role.ROLE_EMPLOYEE)) {
            return EMPLOYEE_DASHBOARD;
        }else if (role.equals(Role.ROLE_USER)) {
            return USER_DASHBOARD;
        } else {

            throw new IllegalStateException();
        }
    }




}
