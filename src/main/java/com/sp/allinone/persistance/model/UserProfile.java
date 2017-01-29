package com.sp.allinone.persistance.model;

import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;

/**
 * Created by i82298 on 12/30/2016.
 */

@Table(value = "user_info")
public class UserProfile extends Model {
    @Column(value = "user_id", length=100)
    private String userId;
    @Column(value = "first_name", length=100)
    private String firstName;
    @Column(value = "last_name", length=100)
    private String lastName;
    @Column(value = "display_name", length=100)
    private String loginName;
    @Column(value = "modified_by", length=100)
    private String modifiedBy;
    @Column(value = "client_admin", length=100)
    private Boolean clientAdmin;
    @Column(value = "super_admin", length=100)
    private Boolean superAdmin;
    @Column(value = "email", length=100)
    private String email;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getClientAdmin() {
        return clientAdmin;
    }

    public void setClientAdmin(Boolean clientAdmin) {
        this.clientAdmin = clientAdmin;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
