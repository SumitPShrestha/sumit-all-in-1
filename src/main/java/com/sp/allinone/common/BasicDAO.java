package com.sp.allinone.common;

import com.sp.allinone.config.persistance.PlatformConfiguration;
import com.sp.allinone.config.persistance.annotation.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i82298 on 12/28/2016.
 */
public abstract class BasicDAO<T extends Model> implements DAOBase<T> {


    @Autowired
    @Qualifier("userJdbcTemplate")
    protected SimpleJdbcTemplate simpleJdbcTepplate;
    PlatformConfiguration platformConfiguration;
    DriverManagerDataSource dataSource;

    private Class clazz;

    public static final String SELECT_FROM = "SELECT * FROM ";
    String entityTable;

    public static final String ID_MUST_NOT_BE_NULL = "Id must not be null";


    public BasicDAO() {
        initialize();
    }

    public BasicDAO(PlatformConfiguration platformConfiguration) {
        this.platformConfiguration = platformConfiguration;
        initDataSource();
        initialize();
    }

    private void initDataSource() {
        dataSource = DataSourceFactory.getDataSource(platformConfiguration);
    }

    public BasicDAO(String spUser) {
        initialize();
        if (SPConstants.SP_USER.equals(spUser)) {
            simpleJdbcTepplate = new SimpleJdbcTemplate(this.dataSource);
        }
    }

    private Field[] getFields() {
        return this.clazz.getFields();
    }


    private void initialize() {
        clazz = getModelClass();
        Table t = (Table) clazz.getAnnotation(Table.class);
        this.entityTable = t.value();
    }

    @Override
    public T findOne(Integer id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return null;
    }

    @Override
    public List<T> findAll() {
        try {
            T t = (T) this.getModelClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }



    public abstract Class getModelClass();


    RowMapper<T> mapper = (resultSet, i) -> {
        List<Method> setterMethods = Arrays.asList(this.getClass().getMethods())
                .stream()
                .filter(method -> method.getName().contains("set")).collect(Collectors.toList());

        Arrays.asList(this.getFields()).stream().map(
                field -> setterMethods.stream().filter
                        (method -> method.getName().contains(field.getName())).map(method1 ->{

                    if (field.getType().equals(int.class)||field.getType().equals(Integer.class)) {
                        try {
                            method1.invoke(this, resultSet.getInt(field.getName()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                    return method1;
                }));
             return null;
    };

}
