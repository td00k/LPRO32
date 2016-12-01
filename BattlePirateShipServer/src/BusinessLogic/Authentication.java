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


public class Authentication 
{
   /**
    * This class represents the BusinessLogic of the server. It has methods to login and to register the user ( so far ).
    */
   //  Query operation defines
   private static final int REGISTER = 1;
   private static final int LOGIN = 2;
    
   // Error code definitions
   private static final int ERROR = -1;     // code for signaling an error
   private static final int OK = 1;         // code for signaling there were no problems
   private static final int EX_ERROR = -2;  // code for signaling there was an error on an exception
    
   // Handler variable for the DB
   private JDBCHandler DBhandler;

   // Query we are executing on the database
   private String query;
   
        public Authentication() 
        {
            /** This function initializes the Handler Variable so we can execute querys on the DB
             */
            // handler variable for database access
            DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
        }

    public int login(String user, String pass) throws SQLException 
    {
        
        /** This method checks if there is an user in the database with name user
        * and password pass.
        *
        * @param user Username of the user trying to login
        * @param pass Password of the user trying to login ( already encrypted ! )
        *
        * @return 1 on sucess and 0 on failure
        */
        
        // query we are executing on the database. This selects all the users registered on the login table.
        query = "select * " + "from userinfo";
        
        // variable we use to pass to the handler function
        String[] args = new String[20];
        
        // variable to check the return of the handler function
        int handler_check;
        
        // opening the connection to the database
        handler_check = DBhandler.open();
        
        if( handler_check != OK)
        {
            //error opening the connection to the database
            return handler_check;
        }
        
        // placing the data on args so we can pass it to the handler.
        args[0] = user; 
        args[1] = pass; 
        
        // accessing the database
        handler_check = DBhandler.execQuery(LOGIN,query,args);
        System.out.println("Query executed!");
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
        /** This method registers a user in by executing a query on the Database to register the new user.
         * It fails if, for example, there's already a user registered with the same username.
         *
         * @param name name passed to the query
         * @param user username passed to the query
         * @param pass password passed to the query
         * @param email email passed to the query
         * @param question security question passed to the query
         * @param answer answer to the security question passed to the query
         *
         * @return 1 on sucess, 0 on failure
         */
        
        // variable to access the database
        Statement stmt = null;
        
        query = "INSERT INTO userinfo " + "VALUES (DEFAULT,'"+ name +"','"+ user +"','"+ email +"','"+ pass +"','"+ question +"','"+ answer +"')";
        System.out.println("Executing" + query + "On the database");
        
        // variable to check the return of the handler function
        int handler_check = OK;
        
        // opening the connection to the database
        handler_check = DBhandler.open();
        System.out.println("Connection to DB opened register!");
        if(handler_check != OK)
        {
            // error opening the connection to the database
            return handler_check;
        }
        
        
        //accessing the database
        handler_check = DBhandler.execQuery(REGISTER,query,null);
        System.out.println("Query Executed!");
        if( handler_check != OK)
        {
            return handler_check;
        }
        
        // closing connection to the database
        handler_check = DBhandler.close();
        System.out.println("Closing DB Register!");
        
        // We're done!
        return handler_check;     
    }   
}

