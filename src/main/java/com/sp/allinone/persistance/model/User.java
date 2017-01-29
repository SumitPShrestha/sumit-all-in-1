package com.sp.allinone.persistance.model;

import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by i82298 on 12/27/2016.
 */
@Table(value = "user")

public class User extends Model implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

//    @Column("")
    private int id;
    private int status =1;
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    private List<Role> roles;
}
