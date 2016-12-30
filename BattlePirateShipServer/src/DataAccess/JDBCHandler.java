package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;


/** This class deals with the the Database itself.
    * It has methods to open a connection, execute a given query on the DB, and close a connection.
    */

public class JDBCHandler 
{

   //  Database credentials
   private static  String JDBC_DRIVER;      // This is the driver used to connect with the DB
   private static  String DB_URL;           // This is the database URL
   private static  String USER;             // This is the username
   private static  String PASS;             // This is the pass
    
   //  Query Operation defines
   private static final int REGISTER = 1;       // code for signaling it's a Register query
   private static final int LOGIN = 2;          // code for signaling it's a login query
   private static final int SEARCH = 3;         // code for signaling it's a search query
   private static final int CREATE = 4;         // code for signaling it's a create query
   private static final int UPDATE=8;           // code for signaling it's a update query
   private static final int GET=9;              // code for signaling it's a getstats query
   private static final int GETFRIENDS = 10;    // code for signaling it's a getfriends query
    
   
   // Error code definitions
   private static final int ERROR = -1;     // code for signaling an error
   private static final int OK = 1;         // code for signaling there were no problems
   private static final int EX_ERROR = -2;  // code for signaling there was an error on an exception
   
   // Connection to DB
   static Connection conn;
    
    // Creates a connection 
    public JDBCHandler(String JDBC_DRIVER, String DB_URL, String USER, String PASS) 
    {
        /** The constructor method saves the variables as class specific ones
         */
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }
    
    /** This method is the main method of this class. 
     * It executes a query on the DB depending on the type of query wanted by passing the information to a class that deals with the tables in the DB.
     * 
     * @param type type of query that is going to be executed
     * @param query query that is going to be executed
     * @param args this variable contains the user and password strings to be compared when a user is trying to login
     * 
     * @return 1 if everything worked, 0 if there was an error
     */
    
    
    public int run(int type, String query, String args[])
    {
        UserInfo Uinfo = new UserInfo();
        UserStats Ustats= new UserStats();
        
        if(open() != OK)
        {
            return ERROR;
        }
        
        switch (type)
        {
            case REGISTER:
                            if ( Uinfo.register(query,conn) == false)
                                return ERROR;
                            break;
            case LOGIN:
                            if ( Uinfo.login(query,conn,args) == false)
                                return ERROR;
                            break;
            case CREATE:
                            games.create(); // counter
            case UPDATE: 
                            Ustats.update(query,conn);
            case GET:
                
                            Ustats.get(query,conn);
            case GETFRIENDS:
                            Ufriends.get(query,conn);
                            
        }
        
        if(close() != OK)
        {
            return ERROR;
        }
        return OK;
    }
    
    /** 
     * This method attempts to open a connection to the DB
     *  
     * @return 1 if a connection was opened or 0 if there was an error
     */
    public int open()
    {

        try
        {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            System.out.println("DB Driver Exists!");
            //Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection to DB established!");
        }
       catch(SQLException se)
       {
          //Handle errors for JDBC
          System.out.println("Error SQL Exception!");
          se.printStackTrace();
          return EX_ERROR;
       }
       catch(Exception e)
       {
          //Handle errors for Class.forName
          System.out.println("Error Exception on DB!");
          e.printStackTrace();
          return EX_ERROR;
       } 
       return OK;
    }

    
    /** This method closes the connection to the DB
          *
          * @return 1 on success and 0 on exception error
          *
          */
    
    public int close()
    {
          try
          {
              // closing the connection
             if(conn!=null)
                conn.close();
          }
          
          catch(SQLException se)
          {
             se.printStackTrace();
             System.out.println("Closing DB and returning!");
             return EX_ERROR;
          }
          System.out.println("Closing DB and returning!");
          return OK;
    }
    
}
