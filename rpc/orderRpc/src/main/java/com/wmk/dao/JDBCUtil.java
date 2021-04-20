package com.wmk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/20
 * @TIME: 20:06
 **/
public class JDBCUtil {
    private static String url = "jdbc:mysql://121.196.105.239:3306/order_table?serverTimezone=GMT%2B8";
    private static String username = "root";
    private static String password = "root";
    private static Connection conn = null;

    public static Connection creatConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("创建连接异常！！！");
        }
        return conn;
    }
}
