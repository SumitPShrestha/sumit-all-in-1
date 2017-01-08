package com.sp.allinone.utils;

import com.sp.allinone.common.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * Created by i82298 on 1/8/2017.
 */
public class AuthUtils {
    public static GrantedAuthority getHighestPrecidenceAuthority(Collection<? extends GrantedAuthority> authorities) {
        Role role = Role.ROLE_USER;

        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(Role.ROLE_SUPERADMIN.name())) {
                role = Role.ROLE_SUPERADMIN;
                break;
            } else if (grantedAuthority.getAuthority().contains(Role.ROLE_CLIENTADMIN.name())) {
                role = Role.ROLE_CLIENTADMIN;
                break;
            } else if (grantedAuthority.getAuthority().contains(Role.ROLE_APPADMIN.name())) {
                role = Role.ROLE_APPADMIN;
                break;
            } else if (grantedAuthority.getAuthority().contains(Role.ROLE_EMPLOYEE.name())) {
                role = Role.ROLE_EMPLOYEE;
                break;
            } else {
                role = Role.ROLE_USER;
            }
        }
        return new SimpleGrantedAuthority(role.name());

    }
}
