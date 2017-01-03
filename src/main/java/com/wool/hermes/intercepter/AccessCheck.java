package com.wool.hermes.intercepter;

import java.lang.annotation.*;

/**
 * Created by wanglin on 2016/10/14.
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessCheck {
    String descirption() default "";
}
