package com.xuesran.tdd.chapter01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Option.
 *
 * @author xueshun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Option {

    /**
     * Value string.
     *
     * @return the string
     */
    String value();
}
