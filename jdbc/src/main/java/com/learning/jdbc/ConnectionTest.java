package com.learning.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author huangzihua
 * @date 2021-04-19
 */
public class ConnectionTest {

    /*
     连接方式一：显式出现了第三方数据库的API
     */
    public void testConn01() {
        try {
            // 1.提供java.sql.Driver接口实现类的对象
            Driver driver = new com.mysql.cj.jdbc.Driver();

            // 2.提供url，指明具体操作的数据库
            String url = "jdbc:mysql://localhost:3306/my_test";

            // 3.提供Properties对象，指明用户名和密码
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");

            // 4.调用driver的connect()，获取连接
            Connection conn = driver.connect(url, info);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     连接方式二：使用反射实例化Driver。
     不在代码中体现第三方数据库的API，体现了面向接口编程思想。
     */
    public void testConn02() {
        try {
            // 1.实例化Driver
            String className = "com.mysql.cj.jdbc.Driver";
            Class clazz = Class.forName(className);
            Driver driver = (Driver) clazz.newInstance();

            // 2.提供url，指明具体操作的数据库
            String url = "jdbc:mysql://localhost:3306/my_test";

            // 3.提供Properties对象，指明用户名和密码
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");

            // 4.调用driver的connect()，获取连接
            Connection conn = driver.connect(url, info);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     连接方式三：使用DriverManager实现数据库的连接。体会获取连接必要的4个基本要素。
     */
    public void testConn03() {
        try {
            // 1.数据库连接的4个基本要素
            String url = "jdbc:mysql://localhost:3306/my_test";
            String user = "root";
            String password = "root";
            String driverName = "com.mysql.cj.jdbc.Driver";

            // 2.实例化Driver
            Class clazz = Class.forName(driverName);
            Driver driver = (Driver) clazz.newInstance();

            // 3.注册驱动
            DriverManager.registerDriver(driver);

            // 4.获取连接
            Connection conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     连接方式四：不必显式的注册驱动了。
     因为在DriverManager的源码中已经存在静态代码块，实现了驱动的注册。
     */
    public void testConn04() {
        try {
            // 1.数据库连接的4个基本要素
            String url = "jdbc:mysql://localhost:3306/my_test";
            String user = "root";
            String password = "root";
            String driverName = "com.mysql.cj.jdbc.Driver";

            // 2.加载驱动
            Class.forName(driverName);

            // 3.获取连接
            Connection conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     连接方式五：使用配置文件的方式保存配置信息，在代码中加载配置文件。
     使用配置文件的好处：
         1.实现了代码和数据的分离，如果需要修改配置信息，直接在配置文件中修改，不需要深入代码；
         2.如果修改了配置信息，省去重新编译的过程。
     */
    public void testConn05() {
        try {
            // 1.加载配置文件
            InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            // 2.读取配置信息
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String driverName = pros.getProperty("driverName");

            // 2.加载驱动
            Class.forName(driverName);

            // 3.获取连接
            Connection conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // 1.提供java.sql.Driver接口实现类的对象
            Driver driver = new com.mysql.cj.jdbc.Driver();

            // 2.提供url，指明具体操作的数据库
            String url = "jdbc:mysql://localhost:3306/my_test";

            // 3.提供Properties对象，指明用户名和密码
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");

            // 4.调用driver的connect()，获取连接
            Connection conn = driver.connect(url, info);

            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
