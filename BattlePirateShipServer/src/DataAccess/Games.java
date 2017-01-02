/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;


import static DataAccess.JDBCHandler.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** This class is a class with the purpose of dealing with the games table in the DB.
 * It has all the methods necessary to extract/update required information from this table.
 */
public class Games 
{
    /**
     * The constructor does nothing
     */
    public Games()
    {
               
    }
    
    /** This method searches for a game for the userid.
     *  It does so by selecting the player waiting who has the closest ranking(maximum difference of 20) to the ranking of the user on userid.
     * 
     * @param query string to be executed on the database
     * @param con connection to the database
     * @param userid userid of the person trying to search for a game
     * 
     * @return gameid, and id of the user waiting on return, or -1 on error
     */
    public String[] search(String query, Connection con,int userid)
    {
         String[] toreturn = new String[3];
        try 
        {
            Statement stmt;
            
            // variable that will be returned
            int gameid = -1;
            
            // auxiliary variables to check the entire table
            int tempgameid;
            int tempuid;
            int temprank;
            int bestrank = 20;     // at the start, this variable should contain the maximum difference of rank between players acceptable.
            
            // variable to store the rank of the player.
            int rank;
            
            // auxiliary result set
            ResultSet aux;
            
            // creating a statement so we can execute queries on the DB
           
            stmt = conn.createStatement();
            System.out.println("Query statement created!");
            
            // extracting the rating from the userstats table
            ResultSet rs = stmt.executeQuery("SELECT rank FROM userstats WHERE id = " + userid);
            rank = rs.getInt("rank");
            
             // executing the query
            rs = stmt.executeQuery(query);
                                   
             //going through the whole games table in database
            while (rs.next()) 
            {   
                //getting the gameid and playerid of the player waiting.
                tempgameid = rs.getInt("id");
                tempuid = rs.getInt("player1id");
                
                // if the game does not have a second player
                if(rs.getInt("player2id") == 0)
                {   
                    // getting the rank of the player waiting
                    aux = stmt.executeQuery("SELECT rank FROM userstats WHERE id = " + tempuid);
                    temprank = aux.getInt("rank");
                
                    // if the rank of the next player is closer to the rank of the player searching for a game
                    if( Math.abs(rank - temprank) < bestrank)
                    {   
                        bestrank = Math.abs(rank - temprank);
                        toreturn[0] = Integer.toString(tempgameid);
                        toreturn[1] = Integer.toString(tempuid);
                        
                    }
                }
            }
            //closing the statement
            stmt.close();
          
            // if there were no games waiting for players, or if we got an SQLexception, we return -1, otherwise, we return the assigned gameid
            return toreturn;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            toreturn[0] = Integer.toString(-1);
            return toreturn;
        }
    }
    
    /** This method creates a new game on the games table in the database.
     * The first player is passed with his userid and the second player is passed with zero.
     * The second player is altered when a match is found on the "search" method
     * 
     * 
     * @param query query to be executed on the DB
     * @param con connection to the DB
     * 
     * @return gameid on success, -1 on error 
     */
    public int create(String query, Connection con)
    {
        int gameid = -1;
        try
        {
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                   
            
            // asking rs to get the generated key
            ResultSet rs = stmt.getGeneratedKeys();
            
            // fetching the gameid if it worked!
            if(rs.next())
                gameid = rs.getInt("id");
            
            // closing the statement since we don't need it anymore
            stmt.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Error SQL Exception!");
        }
        
        return gameid;
    }
    
    public int remove(String query, Connection con)
    {
        try
        {
            Statement stmt;
            
            // creating a statement so we can execute a query on the DB
            stmt = con.createStatement();
            System.out.println("Query statement created!");
            
            // executing the Query
            stmt.execute(query);                  

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
}
