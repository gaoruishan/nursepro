package com.base.commlibs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 视图注入
 * @author:gaoruishan
 * @date:202019-05-09/09:17
 * @email:grs0515@163.com
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FindView {
    int value();
}
