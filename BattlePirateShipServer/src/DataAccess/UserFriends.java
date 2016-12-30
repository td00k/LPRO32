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

/**
 *
 * @author tiagodias
 */
public class UserFriends 
{

     public boolean addfriend(String query, Connection con)
       {
           
        try
        {
          Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            System.out.println("Valid UPDATE userfriends Query!");
            
            // closing the statement
            stmt.close();
            
            return true;
           
        }
        catch(SQLException ex)
        {
            return false;
        }
        
       }   
     
     
     public String[] getfriends(String query, Connection con)
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
            System.out.println("Valid Userstats GETfriends Query!");
            
             ResultSet rs = stmt.executeQuery(query);
                
                     toreturn[0] =  Integer.toString( rs.getInt("id") );
                     toreturn[1] =  Integer.toString( rs.getInt("state") );
                     toreturn[2] =  Integer.toString( rs.getInt("friendname") );
 
                     
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
