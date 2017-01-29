package com.sp.allinone.service.impl;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.common.SPDTO;
import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.dao.UserProfileDao;
import com.sp.allinone.persistance.dao.UserSecurityMetaDataDao;
import com.sp.allinone.persistance.dao.impl.UserDaoImpl;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserProfile;
import com.sp.allinone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by i82298 on 12/31/2016.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserProfileDao userProfileDao;
    private DataSetParameter dataSetParameter;

    @Autowired
    UserSecurityMetaDataDao userSecurityMetaDataDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(
            DataSetParameter dataSetParameter) {
        this.dataSetParameter = dataSetParameter;
        userDao = new UserDaoImpl(dataSetParameter);

    }

    @Override
    public User getUserByUserName(String username)  {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Boolean saveUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public boolean saveUserToken(String token,User user) {
        return userSecurityMetaDataDao.saveUsersToken(token, user);
    }

    @Override
    public boolean createUserProfile(UserProfile userProfileDto) {
        return userProfileDao.createUserProfie(userProfileDto);
    }

    @Override public Boolean createUser(User user) {
      return   userDao.createUser(user );


    }

    @Override public SPDTO<User> findAll() {
        return userDao.findAll();
    }

    @Override public void setDataSetParameter(
            DataSetParameter dataSetParameter) {
        userDao.setDataSetParameter(dataSetParameter);
    }

}
