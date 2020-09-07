package com.Other;

import java.sql.*;

public class JdbcTest {

    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String username="root";
        String password="hcf123";
        String url="jdbc:mysql://127.0.0.1:3306/alipay";
        String surl="jdbc:mysql:///alipay";
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库链接
            conn = DriverManager.getConnection(surl, username, password);
            /*String mysql="select * from user";
            ps = conn.prepareStatement(mysql);
            rs=ps.executeQuery();*/

            String mysql1="insert into user(id,username,sex) values(?,?,?)";
            ps=conn.prepareStatement(mysql1);
            ps.setString(1,"00003");
            ps.setString(2,"liulili");
            ps.setString(3,"女");
             int row=ps.executeUpdate();
            System.out.println(row+" row affect!");

            /*String mysql1="delete from user where id=?";
            ps=conn.prepareStatement(mysql1);
            ps.setString(1,"00003");
            int row=ps.executeUpdate();
            System.out.println(row+" row affect!");*/

            //处理结果集输出结果集中的每一行数据
           /* while(rs.next()){
                System.out.println( rs.getString("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("sex"));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
