package com.sp.allinone.config.layout;

import java.lang.annotation.*;

/**
 * Created by i82298 on 12/17/2016.
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
    String value();
}
