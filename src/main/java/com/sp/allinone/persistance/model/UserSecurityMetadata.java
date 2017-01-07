package com.sp.allinone.persistance.model;

import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Table;

/**
 * Created by i82298 on 1/1/2017.
 */

@Table("security_metadata")
public class UserSecurityMetadata extends Model {

    private String key;
    private String value;
    private String type;
    private String remark;
    private String user_id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
