package com.learning.jdbc.batch;

import com.learning.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author huangzihua
 * @date 2021-04-20
 */
public class BatchTest {
    public static void main(String[] args) {
//        batchInsertByStatement();
//        batchInsertByPreparedStatement();
//        batchInsert();  // 耗时：163861毫秒
        batchInsert2();     // 耗时：14116毫秒
    }


    public static void batchInsertByStatement() {
        try {
            Connection conn = JDBCUtils.getConnection();
            Statement st = conn.createStatement();
            for (int i = 1; i <= 20000; i++) {
                String sql = "INSERT INTO user(username) VALUES(" + i + ")";
                st.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void batchInsertByPreparedStatement() {
        long start = System.currentTimeMillis();
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "insert into user(username) values(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setString(1, "name_" + i);
                ps.executeUpdate();
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时:" + (end - start));

            JDBCUtils.closeResource(conn, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void batchInsert() {
        long start = System.currentTimeMillis();

        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "insert into user(username,password) values(?,'123')";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setString(1, "name_" + i);
                ps.addBatch();
                if (i % 500 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start));
            JDBCUtils.closeResource(conn, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void batchInsert2() {
        long start = System.currentTimeMillis();

        try {
            Connection conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(username,password) values(?,'123')";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 1000000; i++) {
                ps.setString(1, "name_" + i);
                ps.addBatch();
                if (i % 500 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start));
            JDBCUtils.closeResource(conn, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
