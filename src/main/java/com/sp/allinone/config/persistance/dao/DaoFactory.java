package com.sp.allinone.config.persistance.dao;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.service.UserService;
import com.sp.allinone.service.impl.UserServiceImpl;

/**
 * Created by i82298 on 12/27/2016.
 */
public class DaoFactory {

    public static UserService getUserDao(DataSetParameter dataSetParameter) {
        return new UserServiceImpl(dataSetParameter);
    }
}
