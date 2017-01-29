package com.sp.allinone.persistance.model;

import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;

/**
 * Created by i82298 on 1/22/2017.
 */
@Table("client-info")
public class ClientInfo extends Model {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }

    @Column(value = "name")
    private String name;
    @Column(value = "client_id")
    private String clientId;
    @Column(value = "module_id")
    private Module moduleId;



}
