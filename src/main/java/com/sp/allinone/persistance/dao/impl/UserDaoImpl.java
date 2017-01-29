package com.sp.allinone.persistance.dao.impl;

import com.sp.allinone.common.BasicDAO;
import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.config.persistance.PlatformConfiguration;
import com.sp.allinone.persistance.dao.UserDao;
import com.sp.allinone.persistance.model.Role;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.persistance.model.UserSecurityMetadata;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i82298 on 12/27/2016.
 */


@Repository
public class UserDaoImpl extends BasicDAO<User> implements UserDao {

    private  DataSetParameter dataSetParameter;

    public UserDaoImpl() {
        super();

    }

    public UserDaoImpl(PlatformConfiguration platformConfiguration) {
        super(platformConfiguration);
    }

    public UserDaoImpl(String spUser) {
        super(spUser);
    }

    public UserDaoImpl(DataSetParameter dataSetParameter) {
        super(dataSetParameter);
        this.dataSetParameter = dataSetParameter;
    }

    @Override
    public Class getModelClass() {
        return User.class;
    }

    public void save(User user) {

        super.save(user);
    }


    @Override
    public User findUserByUsername(String username)  {

            String userQuery = "SELECT u.id, u.username, u.password, r.role FROM user u" +
                    " INNER JOIN user_role ur ON u.id = ur.user_id " +
                    " INNER JOIN roles r ON r.id = ur.role_id " +
                    " WHERE u.username = '" + username + "'";

        User user = new User();
        List<Role> roles = new ArrayList<>();
        RowMapper<User> mapper = (resultSet, i) -> {
        Role role = new Role();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            role.setRole(resultSet.getString("role"));
             roles.add(role);
            return user;
        };
        this.simpleJdbcTepplate.query(userQuery, mapper);
        user.setRoles(roles);
        return user;
    }

    @Override public Boolean createUser(User user) {
      return   super.save(user);
    }




}
