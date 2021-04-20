package com.wmk.dao;

import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/20
 * @TIME: 19:35
 **/
@Repository
public class RowJDBC {

    public int addOrderCount(Connection conn) {
        if (conn==null) return 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int row = 0;
        try {
            String sql = "update order_table.`order` set order_count = order_count + 1 where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,1);
            //rs = ps.executeQuery(); //只能查询
            //row = ps.executeUpdate();  //增删改
            boolean isSelect = ps.execute(); //所有都可以
            //true表示执行的是查询语句，false表示执行的是insert,delete,update等等其他语句。
            System.out.println(isSelect);

            row = ps.getUpdateCount();

            //遍历查询结果
//            while (rs.next()){
//                System.out.println(rs.getInt("id"));
//                System.out.println(rs.getString("order_name"));
//                System.out.println(rs.getInt("order_count"));
//            }
        } catch (SQLException e) {
            System.out.println("sql执行异常！！！"+e.getMessage());
        }finally {
//            if (rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return row;

    }

}
