package com.sp.allinone.persistance.dao.impl;

import com.sp.allinone.common.BasicDAO;
import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.dao.UserProfileDao;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * Created by i82298 on 1/8/2017.
 */

@Repository
public class UserProfileDaoImpl extends BasicDAO<UserProfile>
        implements UserProfileDao {

    @Autowired
    UserDao userDao;

    @Override
    public Class getModelClass() {
        return UserProfile.class;
    }

    @Override
    public boolean createUserProfie(UserProfile userProfileDto) {

        this.save(userProfileDto);

        return false;
    }
}
