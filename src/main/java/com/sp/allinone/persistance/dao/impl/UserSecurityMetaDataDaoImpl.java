package com.sp.allinone.persistance.dao.impl;

import com.sp.allinone.common.BasicDAO;
import com.sp.allinone.common.TokenType;
import com.sp.allinone.persistance.dao.UserSecurityMetaDataDao;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserSecurityMetadata;
import com.sp.allinone.utils.TokenUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by i82298 on 12/31/2016.
 */
@Repository
public class UserSecurityMetaDataDaoImpl extends BasicDAO<UserSecurityMetadata> implements UserSecurityMetaDataDao {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    SimpleJdbcTemplate jdbcTemplate;

    @Override
    public boolean saveUsersToken(String token, User user) {
        String query = "INSERT INTO security_metadata " +
                "(metadata_key,metadata_value,metadata_type,remark,user_id)" +
                " values(:t_key,:t_value,:t_type,:remark,:user_id)";
        String userToken = TokenUtils.createTokenForUser(token, user);

        try {
            Map<String, Object> args = new HashMap<>();
            args.put("t_key", TokenType.FORGOT_PASSWORD.name());
            args.put("t_value", token);
            args.put("t_type", "FORGOT PASSWORD");
            args.put("remark", "FORGOT PASSWORD");
            args.put("user_id", user.getId());

            logger.info("----------->" + userToken);
            logger.info("----------->" + args);
            jdbcTemplate.update(query, args);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("===========>Error Saving Token" + ex);
            return false;
        }

    }


    @Override
    public Class<UserSecurityMetadata> getModelClass() {
        return UserSecurityMetadata.class;
    }

}
