package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/** This class deals with the the Database itself.
    * It has methods to open a connection, execute a given query on the DB, and close a connection.
    */

public class JDBCHandler 
{

   //  Database credentials
   private static  String JDBC_DRIVER;            // This is the driver used to connect with the DB
   private static  String DB_URL;                 // This is the database URL
   private static  String USER;                   // This is the username we are using to log on to the DB
   private static  String PASS;                   // This is the password we are using to log on to the DB
    
   //  Query Operation defines
   private static final int REGISTER = 1;         // code for signaling it's a Register query
   private static final int LOGIN = 2;            // code for signaling it's a login query
   private static final int SEARCH = 3;           // code for signaling it's a search query
   private static final int CREATE = 4;           // code for signaling it's a create query
   private static final int REMOVE = 5;           // code for signaling it's a remove game query
   private static final int UPDATE = 8;           // code for signaling it's a update query
   private static final int GETSTATS = 9;              // code for signaling it's a getstats query
   private static final int GETFRIENDS = 10;      // code for signaling it's a getfriends query
   private static final int ADDFRIEND = 11;       // code for signaling it's a addfriend query
   private static final int GETINFO = 12;       // code for signaling it's a addfriend query
   private static final int NEWUSERSTATS = 13;  // code for signaling it's a create userstats query
   
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
     * @param args this variable contains the user and password strings to be compared when a user is trying to login, for example. it contains extra information whenever it's required.
     * 
     * @return an array of strings containing the relevant information, or containing -1 if there was an error.
     */
    
    
    public String[] run(int type, String query, String args[])
    {
        // variables to call the methods to access the database
        UserInfo Uinfo = new UserInfo();
        UserStats Ustats = new UserStats();
        UserFriends Ufriends = new UserFriends();
        Games games = new Games();
        
        // variable we put data on to return
        String[] returnval= new String[20];
        
        // trying opening a connection to the DB
        if(open() != 1)
        {
            returnval[0] = Integer.toString(-1);
            return returnval;
        }
        
        // calling the correct method depending on the type
        switch (type)
        {
            
            case REGISTER:
                            returnval[0] = Integer.toString(Uinfo.register(query,conn));
                            break;
            case LOGIN:
                            returnval[0] = Integer.toString(Uinfo.login(query,conn,args));
                            break;
            case SEARCH:
                            returnval = games.search(query,conn,Integer.parseInt(args[0]));
                            break;
            case CREATE:
                            returnval[0] = Integer.toString(games.create(query,conn));
                            break;
            case REMOVE:
                            returnval[0] = Integer.toString(games.remove(query,conn));
                            break;
            case UPDATE: 
                            returnval[0] = Integer.toString(Ustats.update(query,conn));
                            break;
            case GETSTATS:
                            returnval = Ustats.get(query,conn);
                            break;
            case GETFRIENDS:
                            returnval = Ufriends.getfriends(query,conn);
                            break;
            case ADDFRIEND:
                            returnval[0] = Integer.toString(Ufriends.addfriend(query, conn));
                            break;
            case GETINFO:
                            returnval = Uinfo.get(query,conn);
                            break;
            case NEWUSERSTATS:
                            returnval[0] = Integer.toString(Ustats.create(query,conn));
                            break;
        }
        
        // trying to close the connection to the DB
        if(close() != 1)
        {
            returnval[0] = Integer.toString(-1);
        }
        
        // returning
        return returnval;
    }
    
    /** 
     * This method attempts to open a connection to the DB
     *  
     * @return 1 if a connection was opened or -1 if there was an error
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
            
            // returning success!
            return 1;
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            System.out.println("Error SQL Exception!");
            return -1;
        }
       catch(ClassNotFoundException e)
       {
            //Handle errors for Class.forName
            System.out.println("Error Exception on DB!");
            return -1;
       } 
    }

    
    /** This method closes the connection to the DB
          *
          * @return 1 on success and -1 on error
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
            System.out.println(se);
            return -1;
        }
        System.out.println("Closing DB and returning!");
        return 1;
    }
    
}
