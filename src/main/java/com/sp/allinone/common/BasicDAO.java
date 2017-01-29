package com.sp.allinone.common;

import com.sp.allinone.config.persistance.PlatformConfiguration;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;
import com.sp.allinone.persistance.model.User;
import com.sp.allinone.utils.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.sp.allinone.utils.QueryHelper.getQueryParam;
import static com.sp.allinone.utils.QueryHelper.getSaveQuery;
import static com.sp.allinone.utils.QueryHelper.getTableWithAlias;

/**
 * Created by i82298 on 12/28/2016.
 */
public abstract class BasicDAO<T extends Model> implements DAOBase<T> {

    private DataSetParameter dataSetParameter;
    @Autowired
    @Qualifier("userJdbcTemplate")
    protected SimpleJdbcTemplate simpleJdbcTepplate;
    PlatformConfiguration platformConfiguration;
    DriverManagerDataSource dataSource;

    private Class clazz;

    Logger logger = LoggerFactory.getLogger(getClass());

    public static final String SELECT_FROM = "SELECT * FROM ";
    String entityTable;

    public static final String ID_MUST_NOT_BE_NULL = "Id must not be null";

    public BasicDAO() {
        initialize();
    }

    public BasicDAO(String spUser) {
        initialize();
        if (SPConstants.SP_USER.equals(spUser)) {
            simpleJdbcTepplate = new SimpleJdbcTemplate(this.dataSource);
        }
    }

    public BasicDAO(PlatformConfiguration platformConfiguration) {
        this.platformConfiguration = platformConfiguration;
        initDataSource();
        initialize();
    }

    public BasicDAO(DataSetParameter dataSetParameter) {
        this.dataSetParameter = dataSetParameter;
        initialize();
    }

    private void initDataSource() {
        dataSource = DataSourceFactory.getDataSource(platformConfiguration);
    }

    private void initialize() {
        clazz = getModelClass();
        Table t = (Table) clazz.getAnnotation(Table.class);
        this.entityTable = t.value();
    }

    public T findOne(Integer id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return null;
    }

    public SPDTO<T> findAll() {
        SPDTO<T> dto = new SPDTO<>();
        String findAllSql = getFindAllQuery();
        logger.debug(findAllSql);
        dataSetParameter.setMainQuery(findAllSql);
        List<T> results = new ArrayList<>();

        T object = getNewInstance();
        this.simpleJdbcTepplate.query(findAllSql,
                new RowMapper<T>() {
                    @Override public T mapRow(ResultSet resultSet, int i)
                            throws SQLException {
                      T o =  Cloner.cloneThis(mapToObject(object, resultSet));
                        results.add(o);
                        return object;
                    }
                });
        dto.setDto(results);
        int count = this.simpleJdbcTepplate.queryForInt(getCountQuery());
        dto.setRecordCount(count);

        return dto;
    }

    private String getCountQuery() {
        return "SELECT COUNT(*) FROM " + this.entityTable;
    }

    public void setDataSetParameter(DataSetParameter dataSetParameter) {
        this.dataSetParameter = dataSetParameter;
    }

    public List<PropertyDescriptor> getRequiresFields(BeanInfo beanInfo) {
        return Arrays
                .asList(beanInfo.getPropertyDescriptors()).stream()
                //Convert array into arrayList
                .filter(propertyDescriptor1 -> !"class"
                        .equalsIgnoreCase(
                                propertyDescriptor1.getName()))
                .filter(propertyDescriptor2 -> !propertyDescriptor2
                        .getPropertyType().equals(List.class))
                .collect(Collectors.toList());

    }

    public T getNewInstance() {
        T t = null;
        try {
            t = (T) this.getModelClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    private T mapToObject(T obj, ResultSet resultSet) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getModelClass());
            for (PropertyDescriptor propertyDescriptor : getRequiresFields(
                    beanInfo)) {

                Field field = null;
                field = this.getModelClass()
                        .getDeclaredField(propertyDescriptor.getName());
                field.setAccessible(true);

                Method write = propertyDescriptor.getWriteMethod();

                Class<?> propertyClass = write.getParameterTypes()[0];
                String col = QueryHelper.getColumnName(field);

                if (propertyClass.equals(Double.class) || propertyClass
                        .equals(double.class)) {
                    write.invoke(obj, resultSet.getDouble(col));
                } else if (propertyClass.equals(Long.class) || propertyClass
                        .equals(long.class)) {
                    write.invoke(obj, resultSet.getLong(col));
                } else if (propertyClass.equals(Boolean.class) || propertyClass
                        .equals(boolean.class)) {
                    write.invoke(obj, resultSet.getBoolean(col));
                } else if (propertyClass.equals(Integer.class) || propertyClass
                        .equals(int.class)) {
                    write.invoke(obj, resultSet.getInt(col));
                } else if (propertyClass.equals(String.class)) {
                    write.invoke(obj, resultSet.getString(col));
                } else if (propertyClass.equals(Date.class)) {
                    write.invoke(obj, resultSet.getDate(col));
                }
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.debug(e.getMessage());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.debug(e.getMessage());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public <T extends Model> boolean save(T entity) {

        Assert.isTrue(entity.getClass().equals(getModelClass()),
                this.getClass() + ".getModelClass is not implemented properly");
        String query = getSaveQuery(entity);
        logger.info(query);
        return this.simpleJdbcTepplate
                .update(query, getQueryParam(entity)) != -1;

    }

    public String getFindAllQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM ");
        queryBuilder.append(getTableWithAlias(this.entityTable, "a"));
        queryBuilder.append(getWhereClause(dataSetParameter));

        return queryBuilder.toString();
    }

    protected String getWhereClause(DataSetParameter dataSetParameter) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(" WHERE ");
        queryBuilder.append("1=1 ");
        queryBuilder.append(getPaginationClause());
        return queryBuilder.toString();
    }

    private String getPaginationClause() {

        return QueryHelper.getPaginationClause(this.dataSetParameter);

    }

}
