package com.sp.allinone.config.security;

import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by i82298 on 12/27/2016.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private UserDao userDao;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .successHandler(new AppAuthenticationSuccessHandeler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/auth/login")
                .permitAll();

        // @formatter:on

//        final SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter = new XAuthTokenConfigurer(userDetailsServiceBean());
//        http.apply(securityConfigurerAdapter);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService(userDao))
                .passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/static/**","/webjars/**");
    }
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param userDaoo
     * @return
     * @throws UsernameNotFoundException
     */
    private UserDetailsService userDetailsService(final UserDao userDaoo) throws UsernameNotFoundException {
        return (username) -> {
            final User adminUser = userDaoo.findUserByUsername(username);
            if (adminUser == null) {
                throw new UsernameNotFoundException("User " + username + " not found:::::::::::::::::::::::::::::");


            }
            return new SecurityUserDetails(adminUser);
        };
    }
}
