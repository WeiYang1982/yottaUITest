package com.yottabyte.utils;

import com.yottabyte.config.ConfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        ConfigManager config = new ConfigManager();
        String url = "jdbc:mysql://" + config.get("rizhiyi_server_host") + ":3306/" + config.get("dbName") + "?useUnicode=true&characterEncoding=UTF8";
        String user = config.get("dbUser");
        String pass = config.get("dbPassword");
        String driver = config.get("dbDriver");
        System.out.println(url);
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放数据库连接
     *
     * @param conn
     * @param st
     */
    public static void releaseConnection(Connection conn, Statement st) {
        try {
            if (conn != null)
                conn.close();
            if (st != null)
                st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放数据库连接（包含结果集的处理）
     *
     * @param conn
     * @param rs
     * @param st
     */
    public static void releaseConnection(Connection conn, ResultSet rs, Statement st) {
        try {
            if (conn != null)
                conn.close();
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加
     *
     * @param sql
     */
    public static void insert(String sql) {
        try {
            Connection conn = getConnection();
            Statement stmt1 = conn.createStatement();
            int count = stmt1.executeUpdate(sql);
            System.out.println("增加了 " + count + " 条数据");
            releaseConnection(conn, stmt1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     *
     * @param sql
     */
    public static void delete(String sql) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            releaseConnection(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     *
     * @param sql
     */
    public static List<String> query(String sql) {
        Connection conn = getConnection();
        Statement stmt;
        ResultSet rs;
        List<String> resultList = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    resultList.add(rs.getString(i));
                }
            }
            releaseConnection(conn, rs, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static void main(String[] args) {
        String sql = "select * from SavedSchedule;";
        JdbcUtils.query(sql);
    }
}

