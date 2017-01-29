package com.sp.allinone.common;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by i82298 on 1/8/2017.
 */
public class SPDTO<x> implements Serializable {



    private int recordCount = SPConstants.INT_INITIALIZATION;
   public Collection<x> dto;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public Collection<x> getDto() {
        return dto;
    }

    public void setDto(Collection<x> dto) {
        this.dto = dto;
    }
}
