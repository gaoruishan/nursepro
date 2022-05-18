package com.base.commlibs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fragment注入
 * @author:gaoruishan
 * @date:202019-05-09/15:07
 * @email:grs0515@163.com
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FindFragment {
    int value();
}
