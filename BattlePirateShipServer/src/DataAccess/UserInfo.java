package DataAccess;

import static DataAccess.JDBCHandler.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfo 
{
    /**
     *  The constructor does nothing!
     */
    public UserInfo()
    {
                
    }
    
    /** This method attempts to register a user on the database, by executing the query passed on string
     * 
     * 
     * @param query query to be executed
     * @param con connection to the database
     * 
     * @return true on success, false on failure
     */
    
    public boolean register(String query, Connection con)
    {
        try 
        {
            Statement stmt;
            
            
            // creating a statement so we can execute a query on the DB
            stmt = conn.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid Register Query!");
            
            // closing the statement
            stmt.close();
            
            return true;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return false;
        }
    }
    
    /** This method attempts to execute a Login on the database, by executing the query on the Database 
     * 
     * @param query query to be executed 
     * @param con connection to the database
     * @param args string that contains the username and password to be compared with what is on the database
     * 
     * @return true on success, false on failure
     */
    public boolean login(String query, Connection con, String args[])
    {
        try 
        {
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
           
            stmt = conn.createStatement();
            System.out.println("Query statement created!");
            
             // executing the query
             ResultSet rs = stmt.executeQuery(query);
             String username;
             String password;
             int uid;
                                   
             //going through the whole table in database
             while (rs.next()) 
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
                 return true;
                }
                
               }
            
            //closing the statement
            stmt.close();
            
            return false;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return false;
        }
        
    }
}
