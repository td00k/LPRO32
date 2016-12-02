package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

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
   private static final int REGISTER = 1;   // code for signaling it's a Register query
   private static final int LOGIN = 2;      // code for signaling it's a login query
    
   
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
    
          /** This methods opens a connection to the DB
         *
         * @return 1 if it worked, or -1 if there was an exception
         *
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
    
    /** This method executes a given query on the Database
             *
             * @param type the type of query we are executing, like login, example etc...
             * @param query the string we will be executing on the DB
             * @param args variable to check if what we have on the DB is what's expected
             * @throws SQLException When there is an error executing the Query (SQL error)
             * @return value 1 on success, 0 on error, and -1 on an exception error
             *
             */
    
    public int execQuery (int type, String query, String args[]) throws SQLException
    {     
        
            // variable to deal with the connection to execute the query
            Statement stmt = null;
            
            // variable we are going to return
            int ret=OK;
            
            // Execute a query
            stmt = conn.createStatement();
            System.out.println("Query statement created!");
            
            switch(type)
            {
                case REGISTER: 
                                try
                                {
                                    // executing the Query
                                    stmt.execute(query);
                                    ret=OK;
                                    System.out.println("Valid Register Query!");
                                }
                                catch(SQLException e)
                                {
                                    ret = EX_ERROR;
                                    System.out.println(e);
                                }
                                
                                if(stmt != null)
                                stmt.close();
                                
                                break;
                case LOGIN:
                             try 
                             {
                                 // executing the query
                                 ResultSet rs = stmt.executeQuery(query);
                                 String username;
                                 String password;
                                 int uid;
                                   
                                 //going through the whole table in database
                                 while ( rs.next() ) 
                                 {
                                     //getting the id, username and password
                                     uid = rs.getInt("id");
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
                                 ret = ERROR;
                             } 
                             catch (SQLException e ) 
                             {
                                //SQL error
                                ret = EX_ERROR;
                                System.out.println(e);
                             } 
                             break;
                           
            }
        
      // returns the appropriate value
      return ret;
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
