package com.sp.allinone.service;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.common.SPDTO;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserProfile;

import java.util.List;

/**
 * Created by i82298 on 12/31/2016.
 */
public interface UserService {
    User getUserByUserName(String username);

    Boolean saveUser(User user);

    boolean saveUserToken(String Token,User user);

    boolean createUserProfile(UserProfile userProfileDto);

    Boolean createUser(User user);

SPDTO<User> findAll();

    void setDataSetParameter(DataSetParameter dataSetParameter);
}
