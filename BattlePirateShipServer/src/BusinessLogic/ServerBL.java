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
import DataBase.JDBCHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor
 */


public class ServerBL 
{
    //  Query operatoin defines
    private static final int REGISTER = 1;
    private static final int LOGIN = 2;
    
        public ServerBL() 
        {
            //constructor method
        }

    public int login(String user, String pass) throws SQLException 
    {
        // This method checks if there is an user in the database with name user
        // and password pass.
        
        // User: Username of the user trying to login
        // Pass: Password of the user trying to login
  
        //query we are executing on the database. This selects all the users
        //registered on the login table.
        String query = "select * " + "from login";
        
        //handler variable for database access
        JDBCHandler handler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
        
        
        //accessing the database
        if(handler.executeQuery(query,LOGIN));
        
            
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

