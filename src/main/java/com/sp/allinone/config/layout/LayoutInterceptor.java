package com.sp.allinone.config.layout;

import com.sp.allinone.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by i82298 on 12/17/2016.
 */
public class LayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_LAYOUT = "layouts/master";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Value("spring.application.name")
    private String applicationName;

    private String defaultLayout = DEFAULT_LAYOUT;

    private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

    public void setDefaultLayout(String defaultLayout) {
        Assert.hasLength(defaultLayout);
        this.defaultLayout = defaultLayout;
    }

    public void setViewAttributeName(String viewAttributeName) {
        Assert.hasLength(defaultLayout);
        this.viewAttributeName = viewAttributeName;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
        if (isRedirectOrForward(originalViewName)) {
            return;
        }

        if ("index".equals(originalViewName)) {
            return;
        }


        String layoutName = getLayoutName(handler);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null && !(auth instanceof AnonymousAuthenticationToken) && auth.getName() != null) {
            String name = auth.getName();
            GrantedAuthority highestPrecedenceAuthority = AuthUtils.getHighestPrecidenceAuthority(auth.getAuthorities());


            modelAndView.addObject("highestAuthoritiy",highestPrecedenceAuthority.toString() );
            modelAndView.addObject("authorities", auth.getAuthorities());
            modelAndView.addObject("userName", name);
        }
        modelAndView.setViewName(layoutName);
        modelAndView.addObject("applicationName", applicationName);
        modelAndView.addObject(this.viewAttributeName, originalViewName);
    }

    private boolean isRedirectOrForward(String viewName) {
        return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
    }

    private String getLayoutName(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Layout layout = getMethodOrTypeAnnotation(handlerMethod);
        if (layout == null) {
            return this.defaultLayout;
        } else {
            return layout.value();
        }
    }

    private Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
        if (layout == null) {
            return handlerMethod.getBeanType().getAnnotation(Layout.class);
        }
        return layout;
    }
}
