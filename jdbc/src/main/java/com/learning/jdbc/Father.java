package com.learning.jdbc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author huangzihua
 * @date 2021-04-20
 */
public class Father<T> {

    public Father() {
        Class clazz = this.getClass();
        ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
        System.out.println(clazz.getName());
    }
}
