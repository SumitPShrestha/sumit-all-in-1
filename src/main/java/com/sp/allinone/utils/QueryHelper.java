package com.sp.allinone.utils;

import com.sp.allinone.common.DataSetParameter;
import com.sp.allinone.common.Model;
import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by i82298 on 1/21/2017.
 */
public class QueryHelper {

    public static <T extends Model> String getSaveQuery(T entity) {
        StringBuilder queryBuilder = new StringBuilder();
        Field[] fields = entity.getClass().getDeclaredFields();
        List<String> fieldList = getFieldList(fields, entity);

        queryBuilder.append("INSERT INTO " + getTableName(entity.getClass())
                + " ( ");
        StringBuilder paramBuilder = new StringBuilder();
        Iterator<String> itr = fieldList.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            queryBuilder.append(fieldName);
            paramBuilder.append(":" + fieldName);
            if (itr.hasNext()) {
                queryBuilder.append(", ");
                paramBuilder.append(", ");
            } else {
                queryBuilder.append(" ) ");
                paramBuilder.append(" ) ");
            }

        }
        queryBuilder.append(" VALUES ( ");
        queryBuilder.append(paramBuilder);
        return queryBuilder.toString();
    }

    private static List<String> getFieldList(Field[] fields, Object object) {
        List<String> fieldList = new ArrayList<>();
        Iterator<Field> itr = Arrays.asList(fields).iterator();
        String fieldName = null;
        while (itr.hasNext()) {
            Field field = itr.next();
            try {
                if (field.getType() != List.class) {
                    field.setAccessible(true);
                    if (field.get(object) != null) {
                        fieldList.add(getColumnName(field));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldList;
    }

    public static String getColumnName(Field field) {
        Column col = field.getAnnotation(Column.class);
        if (col != null) {
           return col.value();
        } else {
           return field.getName();
        }
    }

    public static Map<String, Object> getQueryParam(Object object) {
        Map<String, Object> params = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        Iterator<Field> itr = Arrays.asList(fields).iterator();

        while (itr.hasNext()) {
            Field field = itr.next();
            try {
                field.setAccessible(true);
                if (field.get(object) != null) {
                    Column col = field.getAnnotation(Column.class);
                    if (col != null) {
                        params.put(col.value(), field.get(object));

                    } else {
                        params.put(field.getName(), field.get(object));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    public static String getTableName(Class clazz) {
        Table t = (Table) clazz.getAnnotation(Table.class);
        return t.value();
    }


    public static final String getTableWithAlias(String tableName, String alias) {

        return tableName+" "+alias+"";
    }

    public static String getPaginationClause(
            DataSetParameter dataSetParameter) {
        int start = dataSetParameter.getPageNo() == 1 ?
                0 :
                dataSetParameter.getPageNo() * dataSetParameter.getPageSize();

        return "LIMIT " + start + ", " + dataSetParameter.getPageSize();
    }
}
