package com.yottabyte.utils;

import com.yottabyte.config.ConfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

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

    public static int insert(String sql) {
        int id = -1;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            releaseConnection(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void delete(String sql) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            releaseConnection(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        String sql = "insert into Knowledge (name,code,creator_id,creator_name,description,domain_id,solution) values ('sunxj1','sunxj1','1','owner','3','1','test')";
        System.out.println(JdbcUtils.insert(sql));
    }
}

