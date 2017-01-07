package com.sp.allinone.config.persistance;

/**
 * Created by i68052 on 12/20/2016.
 * Models fields of table hawkeyemaster5.m_clients and hawkeyemaster5.M_CLIENTPARAMS
 */
public class ClientConfigParameter {
    private String paramId;
    private String paramName;
    private String paramValue;
    private String paramDesc;
    private boolean defaultParam;
    private boolean active;

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public boolean isDefaultParam() {
        return defaultParam;
    }

    public void setDefaultParam(boolean defaultParam) {
        this.defaultParam = defaultParam;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClientConfigParameter that = (ClientConfigParameter) o;

        return paramId.equals(that.paramId);

    }

    @Override public int hashCode() {
        return paramId.hashCode();
    }

    @Override public String toString() {
        return "ClientConfigParameter{" +
                "paramId='" + paramId + '\'' +
                ", paramValue='" + paramValue + '\'' +
                '}';
    }
}
