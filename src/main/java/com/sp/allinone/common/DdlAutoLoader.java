package com.sp.allinone.common;

import com.sp.allinone.config.persistance.annotation.Column;
import com.sp.allinone.config.persistance.annotation.Table;
import com.sp.allinone.persistance.model.User;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by i82298 on 1/21/2017.
 */
public class DdlAutoLoader {

    public static Boolean createDataBase(Class clazz) {
  Package pack = Package.getPackage("com.sp.allinone.persistance.model");

        Table t = (Table) clazz.getAnnotation(Table.class);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table ");
        stringBuilder.append(t.value());
        stringBuilder.append("(");
        clazz.getDeclaredFields();
        Arrays.asList( clazz.getDeclaredFields()).stream().map(field -> {
            field.getAnnotation(Column.class);
            stringBuilder.append(" "+field.getName());
//            if (field.getType().equals()) {
//            }
//            stringBuilder.append(" "+field.get)
            return null;
        });



        return null;
    }
}
