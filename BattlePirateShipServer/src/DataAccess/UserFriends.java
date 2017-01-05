/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** This class is a class with the purpose of dealing with the userfriends table in the DB.
 * It has all the methods necessary to extract/update required information from this table.
 */
public class UserFriends 
{

    /**
     * The constructor does nothing
     */
    public UserFriends()
    {
                
    }
    
    /** This method adds a friend to a user's friends list( the query is already set to do this)
     * 
     * @param query string to be executed on the db
     * @param con connection to the db
     * 
     * @return 1 on success, 0 on failure
     */
    
    public int addfriend(String query, Connection con)
    {   
        try
        {
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid INSERT userfriends Query!");
            
            // closing the statement since we don't need it anymore
            stmt.close();
            
            return 1;
        }
        catch(SQLException ex)
        {
            System.out.println("Error SQL Exception!");
            return -1;
        }
        
    }   
     
     /** This method fetches all the user friends for a given userid ( the userid is already placed on the query string).
      * 
      * @param query query we will be executing on the database
      * @param con connection to the database
      * 
      * @return all the user friends or -1 on error
      */
    
    public String[] getfriends(String query, Connection con)
    {
        String[] toreturn = null;
        try
        {
            Statement stmt, stmt2;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            stmt2 = stmt = con.createStatement();
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid Userstats GETfriends Query!");
            
            // executing the query 
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                // fetching the results
                toreturn[0] =  Integer.toString( rs.getInt("id") );
                toreturn[1] =  Integer.toString( rs.getInt("state") );
                toreturn[2] =  Integer.toString( rs.getInt("friendname") );
            }
            // closing the statement since we don't need it anymore
            stmt.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Error SQL Exception!");
            toreturn[0] = Integer.toString(-1);
        }  
        return toreturn;
    }

    
}

