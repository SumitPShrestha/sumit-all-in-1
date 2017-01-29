package com.sp.allinone.persistance.dao;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.common.SPDTO;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserProfile;

import java.util.List;

/**
 * Created by i82298 on 12/27/2016.
 */

public interface UserDao {
    User findUserByUsername(String username);

    Boolean createUser(User user);

SPDTO<User> findAll();

    void setDataSetParameter(DataSetParameter dataSetParameter);
}
