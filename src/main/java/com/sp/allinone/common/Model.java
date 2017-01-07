package com.sp.allinone.common;

import java.io.Serializable;

/**
 * Created by i82298 on 12/28/2016.
 */
public abstract class Model implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
