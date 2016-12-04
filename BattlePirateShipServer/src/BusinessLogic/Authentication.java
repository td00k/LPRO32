package BusinessLogic;

import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;
import DataAccess.JDBCHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

 /**
    * This class represents the Authentication of the server. 
    * <p>
    * It has the main method 'run' which calls the other methods present in this class based on the information we received from the protocol.
    * So far, the login and register methods have been implemented.
    */

public class Authentication 
{
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
   
   /** This method initializes the Handler Variable so we can execute a query on the DB
             */
   
        public Authentication() 
        {
            // handler variable for database access
            DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
        }

     
    /** This method is the main method of this class.
     * <p>
     * It receives a series of strings from the protocol, and calls a method to  execute what we received.
     * 
     * @param Input information received from the protocol
     * 
     * @return an appropriate String[] which contains the information to be based based on what was asked for us to execute and if it was a success or not.
     */
    public String[] run(String[] Input)
    {
        
       int check;
       String[] toreturn = new String[20];
       switch ( Integer.parseInt(Input[0]) )
       {
           case REGISTER: 
                          check = register(Input[1],Input[2],Input[3],Input[4],Input[5],Input[6]);
                          toreturn[0] = REGISTER + "";
                          if(check == 1)
                          {
                              toreturn[1] = "OK";
                          }
                          else
                          {
                              toreturn[1] = "ERROR";
                          }
                          break;
                          
           case LOGIN:
                          check = login(Input[1],Input[2]);
                          toreturn[0] = LOGIN + "";
                          if(check == 1)
                          {
                              toreturn[1] = "OK";
                          }
                          else
                          {
                              toreturn[1] ="ERROR";
                          }
                          break;
       }
      return toreturn;
    }
    
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
         * @return 1 on success, 0 on failure
         */
    
    public int register(String name, String user, String pass, String email, String question, String answer)
    {

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
        
        
       try 
       {
           //accessing the database
           handler_check = DBhandler.execQuery(REGISTER,query,null);
       } 
       catch (SQLException ex) 
       {
           System.out.println("ex");
       }
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
    
       /** This method checks if there is an user in the database with name user
        * and password pass.
        *
        * @param user Username of the user trying to login
        * @param pass Password of the user trying to login ( already encrypted ! )
        * 
        * @return 1 on success and 0 on failure
        */
    
    public int login(String user, String pass)  
    {

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
        
       try 
       {
           // accessing the database
           handler_check = DBhandler.execQuery(LOGIN,query,args);
       } 
       catch (SQLException ex) 
       {
           System.out.println("ex");
       }
       
       System.out.println("Query executed!");
       if( handler_check != OK)
       {
           return handler_check;
       }
        
        //closing connection to the database
        handler_check = DBhandler.close();
         
        
        return handler_check;     
    }   
    
    
}

