package com.learning.jdbc;

import com.learning.jdbc.entity.User;
import com.learning.jdbc.util.JDBCUtils;
import com.mysql.cj.MysqlType;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

/**
 * @author huangzihua
 * @date 2021-04-20
 */
public class PreparedStatementTest {
    public static void main(String[] args) {
        // 新增
//        String insertSql = "INSERT INTO user(username,password) values(?,?)";
//        update(insertSql, "admin", "123456");

        // 更新
//        String updateSql = "UPDATE user SET username=?,password=? WHERE id=?";
//        update(updateSql, "hhh","999",1);

        // 删除
//        String deleteSql = "DELETE FROM user WHERE id = ?";
//        update(deleteSql, "3");

        // 查询
        String querySql = "SELECT * FROM user WHERE username=? AND password=?";
        User hhh = getInstance(User.class, querySql, "hhh", "999");
        System.out.println(hhh.toString());
    }

    public static <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            // 1.加载配置文件
//            InputStream is = PreparedStatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
//            Properties prop = new Properties();
//            prop.load(is);
//
//            // 2.获取配置信息
//            String user = prop.getProperty("user");
//            String password = prop.getProperty("password");
//            String url = prop.getProperty("url");
//            String driverName = prop.getProperty("driverName");
//
//            // 3.加载驱动类
//            Class.forName(driverName);

//            // 4.获取连接
//            conn = DriverManager.getConnection(url, user, password);

            // 使用工具类获取连接
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            // 5.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 6.执行语句
            rs = ps.executeQuery();

            // 7.获取结果的元数据
            ResultSetMetaData psmd = rs.getMetaData();

            // 8.获取结果
            int columnCount = psmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名
                    String columnLabel = psmd.getColumnName(i + 1);
                    // 使用反射给对象赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }

            // 使用工具类关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    public static void update(String sql, Object ... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
//            // 1.加载配置文件
//            InputStream is = PreparedStatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
//            Properties prop = new Properties();
//            prop.load(is);
//
//            // 2.获取配置信息
//            String url = prop.getProperty("url");
//            String user = prop.getProperty("user");
//            String password = prop.getProperty("password");
//            String driverName = prop.getProperty("driverName");
//            // 加载MySQL驱动类
//            Class.forName(driverName);
//
//            // 3.获取连接
//            conn = DriverManager.getConnection(url, user, password);
            conn = JDBCUtils.getConnection();

            // 4.获取preparedStatement实例（或预编译SQL语句）
            ps = conn.prepareStatement(sql);

            // 5.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 6.执行SQL语句
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
