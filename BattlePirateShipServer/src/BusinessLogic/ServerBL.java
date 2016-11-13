/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;
import DataAccess.JDBCHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */


public class ServerBL 
{
    //  Query operatoin defines
    private static final int REGISTER = 1;
    private static final int LOGIN = 2;
    
    // Error code definitions
   private static final int ERROR = -1;     // code for signaling an error
   private static final int OK = 1;         // code for signaling there were no problems
   private static final int EX_ERROR = -2;  // code for signaling there was an error on an exception
    
   private JDBCHandler DBhandler; 
   private String query;
   
        public ServerBL() 
        {
            //handler variable for database access
            DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
        }

    public int login(String user, String pass) throws SQLException 
    {
        // This method checks if there is an user in the database with name user
        // and password pass.
        
        // User: Username of the user trying to login
        // Pass: Password of the user trying to login
  
        //query we are executing on the database. This selects all the users
        //registered on the login table.
        query = "select * " + "from login";
        
        //variable we use to pass to the handler function
        String[] args = new String[20];
        
        //variable to check the return of the handler function
        int handler_check;
        
        //opening the connection to the database
        handler_check = DBhandler.open();
        if(handler_check != OK)
        {
            //error opening the connection to the database
            return handler_check;
        }
        
        //placing the data on args so we can pass it to the handler.
        args[0] = user; 
        args[1] = pass; 
        
        //accessing the database
        handler_check = DBhandler.execQuery(LOGIN,query,args);
        if( handler_check != OK)
        {
            return handler_check;
        }
        
        //closing connection to the database
        handler_check = DBhandler.close();
        
        return handler_check;     
    }   
    
    public int register(String name, String user, String pass, String email, String question, String answer) throws SQLException
    {
        Statement stmt = null;
        query = "INSERT INTO user_info " + "VALUES (DEFAULT,'"+ user +"','"+ email +"','"+ pass +"','"+ question +"','"+ answer +"')";
        
        //variable to check the return of the handler function
        int handler_check = OK;
        
        //opening the connection to the database
        handler_check = DBhandler.open();
        
        if(handler_check != OK)
        {
            //error opening the connection to the database
            return handler_check;
        }
        
        //accessing the database
        handler_check = DBhandler.execQuery(REGISTER,query,null);
        if( handler_check != OK)
        {
            return handler_check;
        }
        
        //closing connection to the database
        handler_check = DBhandler.close();
        
        return handler_check;     
    }   
}

