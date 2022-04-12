package com.learning.jdbc.dao;

import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库基础类
 */
public class BaseDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> type;

    public BaseDAO() {
        Class clazz = this.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        this.type = (Class<T>) types[0];
    }

    public int update(Connection conn, String sql, Object... args) {
        int count = 0;
        try {
            count = queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
