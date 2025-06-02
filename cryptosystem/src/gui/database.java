/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class database {
     Connection con;
    public database() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fingerprint","root","admin");
        System.out.println("Connected");
    }
     public int register(String name, String loc, String need, String user, String pass) throws SQLException{
        
         String i="insert into registration values(null,'"+name+"','"+loc+"','"+need+"','"+user+"','"+pass+"')";
                PreparedStatement ps2=con.prepareStatement(i);
                int r=ps2.executeUpdate();
                System.out.println(r);
         return r;
         
        
    }
     public ResultSet Login(String user, String pass) throws SQLException
      {
       
        String q=" select * from registration where user='"+user+"' and pass='"+pass+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
        return n;
    }
}
