package com.dao;

import com.model.Emp;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDao {

    private static Statement statement;


    public List<Emp> listEmp() throws Exception{
        List<Emp>  list=new ArrayList<>();
        String sql = "select * from emp";
        ResultSet rs =  statement.executeQuery(sql);

        String result="";
        while(rs.next()){
           String name  = rs.getString("name");
            String role = rs.getString("role");
            Emp emp=new Emp();
            emp.setName(name);
            emp.setRole(role);
            list.add(emp);
        }
        return list;
    }



    static{
        initDb();
    }

    public static void initDb() {
         Connection con;
         String driver = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong";
         String user = "root";
         String password = "fuduhui123";
        try {
             Class.forName(driver);
             con = DriverManager.getConnection(url,user,password);
            statement=con.createStatement();
            if(!con.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }


        } catch(Exception e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
    }
}
