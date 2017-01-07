package com.sp.allinone.persistance.dao;

import com.sp.allinone.common.TokenType;
import com.sp.allinone.persistance.model.User;

/**
 * Created by i82298 on 12/31/2016.
 */
public interface UserSecurityMetaDataDao {
    boolean saveUsersToken(String token, User user);
}
