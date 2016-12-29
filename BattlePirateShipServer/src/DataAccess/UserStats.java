package DataAccess;

import static DataAccess.JDBCHandler.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserStats
{
    /**
     * 
     */
    public UserStats()
    {
                
    }
    
    /** This method updates the user stats on the database
     * 
     * @return true on success, false on failure
     */
    
    public boolean update(String query, Connection con)
    {
        
        try
        {
          Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid Userstats UPDATE Query!");
            
            // closing the statement
            stmt.close();
            
            return true;
           
        }
        catch(SQLException ex)
        {
            return false;
        }
        
    }
    
    /** This method fetches all the user stats from the database
     * 
     * @return true on success, false on failure
     */
     public String[] get(String query, Connection con)
    {
        
       try
        {
          Statement stmt;
          String[] toreturn = null;
         
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid Userstats GETSTATS Query!");
            
             ResultSet rs = stmt.executeQuery(query);
                
                     toreturn[0] =  Integer.toString( rs.getInt("id") );
                     toreturn[1] =  Integer.toString( rs.getInt("state") );
                     toreturn[2] =  Integer.toString( rs.getInt("gamesplayed") );
                     toreturn[3] =  Integer.toString( rs.getInt("id") );
                     toreturn[4] =  Integer.toString( rs.getInt("id") );
                     toreturn[5] =  Integer.toString( rs.getInt("id") );
                     toreturn[6] =  Integer.toString( rs.getInt("id") );
                     toreturn[7] =  Integer.toString( rs.getInt("id") ); 
                     
            // closing the statement
            stmt.close();
            
            
       
           return toreturn ;
        }
        catch(SQLException ex)
        {
            return null;
        }
        
        
    }
}
