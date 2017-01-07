package com.sp.allinone.service;

import com.sp.allinone.persistance.model.User;

/**
 * Created by i82298 on 12/31/2016.
 */
public interface UserService {
    User getUserByUserName(String username);

    User saveUser(User user);

    boolean saveUserToken(String Token,User user);
}
