/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vitor
 */
public class JDBCHandler {
    
   private static  String JDBC_DRIVER;  
   private static  String DB_URL;

   //  Database credentials
   private static  String USER;
   private static  String PASS;
   
   //  Query Operation defines
   private static final int REGISTER = 1;
   private static final int LOGIN = 2;
   // we need to add more..
   
   // Connection to DB
   static Connection conn;
    
    // Creates a connection 
    public JDBCHandler(String JDBC_DRIVER, String DB_URL, String USER, String PASS) 
    {   
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }
    
    public int open()
    {
        try
        {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
       catch(SQLException se)
       {
          //Handle errors for JDBC
          se.printStackTrace();
          return -1;
       }
       catch(Exception e)
       {
          //Handle errors for Class.forName
          e.printStackTrace();
          return -1;
       } 
        return 1;
    }
    
    public int executeQuery (int type, String query, String args[]) throws SQLException
    {
            
            Statement stmt = null;
        
            //Execute a query
            stmt = conn.createStatement();
            
            switch(type)
            {
                case REGISTER: 
                                stmt.executeUpdate(query);
                                    
                                if(stmt!=null)
                                stmt.close();
                                break;
                case LOGIN:
                                   try 
                                   {
                                       ResultSet rs = stmt.executeQuery(query);
                                       String username;
                                       String password;
                                       int uid;
                                       //going through the whole table in database
                                       while ( rs.next() ) 
                                       {
                                           //getting the id, username and password
                                           uid = rs.getInt("user_id");
                                           username = rs.getString("username");
                                           password = rs.getString("password");

                                           //comparing the results to see if the user is in the database
                                           if( username.equals(args[0]) && password.equals(args[1]) )
                                           {
                                               // We found the user in the database
                                               stmt.close();
                                               return 1;
                                           }
                                       }
                                       // Didn't find user in database, now we continue 
                                   } 
                                catch (SQLException e ) 
                                {
                                //JOptionPane.showMessageDialog(null,"SQL Exception");
                                } 
                                break;
            }
            
            return 1;
    }
    
    public int close()
    {
          try
          {
             if(conn!=null)
                conn.close();
          }
          
          catch(SQLException se)
          {
             se.printStackTrace();
             return -1;
          }
          return 1;
    }
    
}
