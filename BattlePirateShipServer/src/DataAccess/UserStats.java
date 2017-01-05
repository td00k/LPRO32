package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/** This class is a class with the purpose of dealing with the userstats table in the DB.
 * It has all the methods necessary to extract/update required information from this table.
 */
public class UserStats
{
    /**
     * The constructor does nothing
     */
    public UserStats()
    {
                
    }
    
    
    
    /** This method creates the user stats on the database
     * 
     * @param query query that's going to be executed on the DB
     * @param con connection to the DB
     * 
     * @return 1 on success, -1 on failure
     */
    
    public int create(String query, Connection con)
    {
        
        try
        {
          Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid Userstats CREATE Query!");
            
            // closing the statement, since we don't need it anymore
            stmt.close();
            
            return 1;
           
        }
        catch(SQLException ex)
        {
            return -1;
        }
        
    }
    
    /** This method updates the user stats on the database
     * 
     * @param query query that's going to be executed on the DB
     * @param con connection to the DB
     * 
     * @return 1 on success, -1 on failure
     */
    
    public int update(String query, Connection con)
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
            
            // closing the statement, since we don't need it anymore
            stmt.close();
            
            return 1;
           
        }
        catch(SQLException ex)
        {
            return -1;
        }
        
    }
    
   /** This method fetches all the userstats from the database
    * 
    * @param query query to be executed on the db
    * @param con connection to the DB
    * 
    * @return a string[] containing the userstats or -1 in case of error
    */
    public String[] get(String query, Connection con)
    {
        String[] toreturn = new String[7];
        try
        {
            Statement stmt;
         
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query  USERSTATS statement created!");
            
            // executing the Query
            System.out.println("Userstats " + query);
            stmt.execute(query);                   
            System.out.println("Valid Userstats GETSTATS Query!");
            
            // executing the query
            ResultSet rs = stmt.executeQuery(query);

            // extracting results
            if(rs.next())
            {
            toreturn[0] =  Integer.toString(rs.getInt("id"));
            toreturn[1] =  Integer.toString(rs.getInt("gamesplayed"));
            toreturn[2] =  Integer.toString(rs.getInt("wins"));
            toreturn[3] =  Integer.toString(rs.getInt("defeats"));         
            toreturn[4] =  Integer.toString(rs.getInt("surrenders"));
            toreturn[5] =  Integer.toString(rs.getInt("rank"));
            } 

            // closing the statement since we don't need to use it anymore
            stmt.close();
            
            // returning
            return toreturn ;
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
            toreturn[0] = Integer.toString(-1);
            return toreturn;
        }
    }
}
        