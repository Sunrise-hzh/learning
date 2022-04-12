package com.learning.jdbc;

import com.learning.jdbc.entity.User;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * 通过调用Connection对象的createStatement()方法创建Statement对象。
 * Statement对象用于执行静态的SQL语句，并且返回执行结果。
 * Statement操作数据表的弊端：
 * --存在拼串操作，繁琐
 * --存在SQL注入问题
 * （SQL注入是利用系统没有对用户输入的数据进行充分的检查，而在用户输入数据中注入
 * 非法的SQL语句或命令，从而利用系统的SQL引擎完成恶意行为的做法。
 * 如：SELECT username,password FROM t_user WHERE username='a' OR 1='AND password=' OR '1' = '1'）
 */
public class StatementTest {
    public static void main(String[] args) {
        testLogin();
    }

    public static void testLogin() {
        Scanner scan = new Scanner(System.in);

        System.out.println("用户名：");
        String username = scan.nextLine();
        System.out.println("密码：");
        String password = scan.nextLine();

        String sql = "SELECT id, username, password FROM user " +
                "WHERE username = '" + username + "' AND password = '" + password + "'";
        User user = get(sql, User.class);
        if (user != null) {
            System.out.println("登录成功");
        } else {
            System.out.println("用户名或密码错误");
        }
    }

    public static <T> T get(String sql, Class<T> clazz) {
        T t = null;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1.加载配置文件
            InputStream is = StatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            // 2.读取配置信息
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String driverName = pros.getProperty("driverName");

            // 3.加载驱动
            Class.forName(driverName);

            // 4.获取连接
            conn = DriverManager.getConnection(url, user, password);

            // 执行sql
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            // 获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            // 获取结果集的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 1.获取列的名称
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 2.根据列名获取对应数据表中的数据
                    Object columnVal = rs.getObject(columnName);
                    // 3.将数据表中得到的数据，封装进对象
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
