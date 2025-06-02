/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fingerprints;

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
       public int Key(int id, String key) throws SQLException{
        
         String i="insert into keyss values(null,'"+id+"','"+key+"')";
                PreparedStatement ps2=con.prepareStatement(i);
                int r=ps2.executeUpdate();
                System.out.println(r);
         return r;
         
        
    }
        public int insertAnalysis(int id, float key,int num) throws SQLException{
        
         String i="insert into analysis values(null,'"+num+"','"+key+"','"+id+"')";
                PreparedStatement ps2=con.prepareStatement(i);
                int r=ps2.executeUpdate();
                System.out.println(r);
         return r;
         
        
    }
        public void TRunaNAL(int id) throws SQLException{
        
                String i="TRUNCATE analysis";
                PreparedStatement ps2=con.prepareStatement(i);
                int r=ps2.executeUpdate();
                System.out.println(r);
         
         
        
    }
        public int Key1(int id, String key) throws SQLException{
        
         String i="insert into downloadkey values(null,'"+id+"','"+key+"')";
                PreparedStatement ps2=con.prepareStatement(i);
                int r=ps2.executeUpdate();
                System.out.println(r);
         return r;
         
        
    }
        public int Files(int id, String file){
            int r=0;
        try{
         String i="insert into file values(null,?,'"+id+"')";
                PreparedStatement ps2=con.prepareStatement(i);
                ps2.setString(1, file);
                r=ps2.executeUpdate();
                System.out.println(r);
        
         
        }catch(Exception e){
            System.out.println("exc"+e);
        }
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
     public ResultSet GetFile(int id) throws SQLException
      {
       
        String q=" select * from file where log_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
          System.out.println("jasmiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        return n;
    }
     public ResultSet GetFile1() throws SQLException
      {
       
        String q=" select * from file as f join registration as r on r.id=f.log_id";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
          System.out.println("jasmiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        return n;
    }
      public ResultSet GetAnalysis(int id) throws SQLException
      {
       
        String q=" select * from analysis where u_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
          System.out.println("jasmiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        return n;
    }
      public ResultSet GetKey(int id) throws SQLException
      {
       
        String q=" select * from keyss where user_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
        return n;
    }
      public ResultSet GetKey1(int id) throws SQLException
      {
       
        String q=" select * from downloadkey where use_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
        return n;
    }
      public ResultSet GetUSERS() throws SQLException
      {
       
        String q=" select * from keyss as k join registration as r on r.id=k.user_id";
        PreparedStatement ps=con.prepareStatement(q);
        ResultSet n=ps.executeQuery();
        System.out.println("resultSet");
        return n;
    }
      
       public void DeleteKey(int id) throws SQLException
      {
       
        String q=" delete from keyss where  user_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        ps.executeUpdate();
        System.out.println("resultSet");
        
    }
       public int DeleteKeys(int id) throws SQLException
      {
       
        String q=" delete from downloadkey where  use_id='"+id+"'";
        PreparedStatement ps=con.prepareStatement(q);
        int n=ps.executeUpdate();
        System.out.println("resultSet");
        return n;
    }
}
