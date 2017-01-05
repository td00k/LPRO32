package DataAccess;

import static DataAccess.JDBCHandler.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/** This class is a class with the purpose of dealing with the userinfo table in the DB.
 * It has all the methods necessary to extract/update required information from this table.
 */
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
     * @return 1 on success, -1 on failure
     */
    
    public int register(String query, Connection con)
    {
        int id;
        try 
        {
            Statement stmt;
            
            
            // creating a statement so we can execute a query on the DB
            stmt = conn.createStatement();
            System.out.println("Query statement created!");
            
           
            // executing the Query
            stmt.execute(query,Statement.RETURN_GENERATED_KEYS);                   
            System.out.println("Valid Register Query!");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next())
            {
               id = rs.getInt(1);
            }
            else
            {
                id = -1;
            }
            
            // closing the statement
            stmt.close();
            
            return id;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return -1;
        }
    }
    
    /** This method attempts to execute a Login on the database, by executing the query on the Database 
     * 
     * @param query query to be executed 
     * @param con connection to the database
     * @param args string that contains the username and password to be compared with what is on the database
     * 
     * @return userid on success, -1 on failure
     */
    public int login(String query, Connection con, String args[])
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
                if(rs.getInt("banned") == 1) // check if user is banned
                {
                    stmt.close();
                    return -2;
                }
                // We found the user in the database
                stmt.close();
                return uid;
               }
                
            }
            
            //closing the statement
            stmt.close();
            
            return -1;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            return -1;
        }
        
    }
    
    /** This method attemps to fetch the name and username, for a given id. It also returns said id.
     * 
     * @param query query to be executed on the DB
     * @param con connectino to the DB
     * 
     * @return a string[] containing the required information on success, or  a string containing s-1 on error
     */
     public String[] get(String query, Connection con)
    {
        String[] toreturn = new String[3];
        try
        {
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            System.out.println("Userinfo" + query);
            stmt.execute(query);                   
            System.out.println("Valid Userinfo GETSTATS Query!");
            
            // executing the query
            ResultSet rs = stmt.executeQuery(query);
            
            // extracting results
            if(rs.next())
            {
            toreturn[0] =  Integer.toString(rs.getInt("id"));
            toreturn[1] =  rs.getString("name");
            toreturn[2] =  rs.getString("username");
            }
                    
            // closing the statement since we don't need to use it anymore
            stmt.close();
            
            // returning
            return toreturn ;
        }
        catch(SQLException ex)
        {
            toreturn[0] = Integer.toString(-1);
            System.out.println(ex);
            return toreturn;
        }
    }
    
    public int getidbyusername(String query, Connection con)
    {
        int userid = -1;
        try
        {
            
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Create Query statement created!");
            
            // executing the Query
            stmt.execute(query,Statement.RETURN_GENERATED_KEYS);                   
            
            // asking rs to get the generated key
            ResultSet rs = stmt.getGeneratedKeys();
            
            // fetching the userid if it worked!
            if(rs.next())
                userid = rs.getInt("id");
            
            // closing the statement since we don't need it anymore
            stmt.close();
            
            return userid;
        }
        catch(SQLException ex)
        {
            System.out.println("Error SQL Exception!");
             
            return userid;
        }
    }
}
