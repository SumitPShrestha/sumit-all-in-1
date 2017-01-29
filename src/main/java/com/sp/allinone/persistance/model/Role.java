package com.sp.allinone.persistance.model;

import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;

/**
 * Created by i82298 on 12/30/2016.
 */

@Table(value = "role")
public class Role extends Model {

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
