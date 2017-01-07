package com.sp.allinone.persistance.dao;

import com.sp.allinone.persistance.model.User;

/**
 * Created by i82298 on 12/27/2016.
 */

@FunctionalInterface
public interface UserDao {
    User findUserByUsername(String username) ;

}
