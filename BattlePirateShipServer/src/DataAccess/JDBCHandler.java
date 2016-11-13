/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

/**
 *
 * @author Vitor
 */
public class JDBCHandler 
{
    //  Database credentials
   private static  String JDBC_DRIVER;  
   private static  String DB_URL;
   private static  String USER;
   private static  String PASS;
   
   //  Query Operation defines
   private static final int REGISTER = 1;  
   private static final int LOGIN = 2;
   // we need to add more..
   
   // Error code definitions
   private static final int ERROR = -1;     // code for signaling an error
   private static final int OK = 1;         // code for signaling there were no problems
   private static final int EX_ERROR = -2;  // code for signaling there was an error on an exception
   
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
          return EX_ERROR;
       }
       catch(Exception e)
       {
          //Handle errors for Class.forName
          e.printStackTrace();
          return EX_ERROR;
       } 
       return OK;
    }
    
    public int execQuery (int type, String query, String args[]) throws SQLException
    {
            
            Statement stmt = null;
            
            //variable we are going to return
            int error=1;
            
            //Execute a query
            stmt = conn.createStatement();
            
            switch(type)
            {
                case REGISTER: 
                                try
                                {
                                    stmt.executeUpdate(query);
                                }
                                catch(SQLException e)
                                {
                                    error = EX_ERROR;
                                }
                                
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
                                         return OK;
                                     }
                                 }
                                 //user not found in database
                                 stmt.close();
                                 error = ERROR;
                             } 
                             catch (SQLException e ) 
                             {
                                //SQL error
                                error = EX_ERROR;
                             } 
                             break;
                           
            }
      // returns the appropriate value
      return error;
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
             return EX_ERROR;
          }
          return OK;
    }
    
}