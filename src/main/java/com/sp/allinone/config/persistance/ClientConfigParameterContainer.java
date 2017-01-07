package com.sp.allinone.config.persistance;

import java.util.List;

/**
 * Created by i68052 on 12/22/2016.
 */
public class ClientConfigParameterContainer extends BaseConfigParameter {
    private List<ClientConfigParameter> clientConfigParameter;

    public List<ClientConfigParameter> getClientConfigParameter() {
        return clientConfigParameter;
    }

    public void setClientConfigParameter(
            List<ClientConfigParameter> clientConfigParameter) {
        this.clientConfigParameter = clientConfigParameter;
    }
}
