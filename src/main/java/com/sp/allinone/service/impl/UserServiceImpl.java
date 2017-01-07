package com.sp.allinone.service.impl;

import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.dao.UserSecurityMetaDataDao;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by i82298 on 12/31/2016.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserSecurityMetaDataDao userSecurityMetaDataDao;

    @Override
    public User getUserByUserName(String username)  {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public boolean saveUserToken(String token,User user) {
        return userSecurityMetaDataDao.saveUsersToken(token, user);
    }
}
