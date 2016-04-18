package com.hengsu.uliketu.core.annotation;

import java.lang.annotation.*;

/**
 * Created by haiquanli on 15/11/20.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    public static final int USER=0;
    public static final int ADMIN=0;
    public static final int SUPER_ADMIN=0;

    int [] roles();
}
