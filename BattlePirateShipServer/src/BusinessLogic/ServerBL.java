/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */


public class ServerBL 
{
        public ServerBL() 
        {
            //constructor method
        }

    public int login(String user, String pass, Connection connection) throws SQLException 
    {
        // This method checks if there is an user in the database with name user
        // and password pass. it returns 1 on sucess, -1 if there is no user with
        // that name in the database and -2 if we have an sql error while checking
        // the database
        
        // User: Username of the user trying to login
        // Pass: Password of the user trying to login
        // Connection: Variable to access the database
        
        //Variables for database acess
        Statement stmt = null;  
       
        //varibles to save the data we extract from the database
        String username;
        String password;
        int uid;
        
        //query we are executing on the database
        String query = "select * " + "from login";
        
        //accessing the database
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            //going through the whole table in database
            while ( rs.next() ) 
            {
                //getting the id, username and password
                uid = rs.getInt("user_id");
                username = rs.getString("username");
                password = rs.getString("password");
                
                //comparing the results to see if the user is in the database
                if( username.equals(user) && password.equals(pass) )
                {
                    // We found the user in the database
                    stmt.close();
                    return 1;
                }
            }
            // Didn't find user in database, now we continue 
        } 
        catch (SQLException e ) 
        {
            //we had an sql problem, and return and error code
            if (stmt != null)
            {
                stmt.close(); 
            }
            return -2;
        } 
        finally 
        {
            //no sql problem, so we return -11 to signal the user wasn't found in the db
            if (stmt != null) 
            { 
              stmt.close();
            }
        }
        return -1;
    }   
    
    public int register(String user, String pass, Connection connection) 
    {
        Statement stmt = null;
        String query = "INSERT INTO login " + "VALUES (DEFAULT,'"+ user +"','"+ pass +"')";
        try 
        {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } 
        catch (SQLException e ) 
        {
            JOptionPane.showMessageDialog(null,"SQL Exception");
            return 0;
        } 
        finally 
        {
            if (stmt != null) 
            { 
                try 
                {
                    JOptionPane.showMessageDialog(null," User:"+user+"\n Password:"+pass+" \n Success!");
                    stmt.close();
                } 
                catch (SQLException ex) 
                {
                return 0;
                }
            } 
        return 1;
        }
    }   
}

