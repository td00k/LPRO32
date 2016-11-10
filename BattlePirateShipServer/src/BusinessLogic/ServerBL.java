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
public class ServerBL {
    private String cmd;
    
     public ServerBL(String cmd) {
      this.cmd = cmd;
    }

    public int login(String user, String pass, Connection connection) 
    {
        Statement stmt = null;
        int uid=0;
        String query = "select * " + "from login";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                uid = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                if( username.equals(user) && password.equals(pass) )
                {
                    //JOptionPane.showMessageDialog(null,"User found! UID:"+uid+" \n Username:"+username+"");
                    stmt.close();
                    return 1;
                }
            }
         //JOptionPane.showMessageDialog(null,"Username/password wrong! Not found on DB!");
        } 
        catch (SQLException e ) 
        {
        //JOptionPane.showMessageDialog(null,"SQL Exception");
        } 
        finally 
        {
            if (stmt != null) 
            { 
                try 
                {
                    stmt.close();
                } 
                catch (SQLException ex) 
                {
               
                }
            }
        }
        return 1;
    }   
    
    public int register(String user, String pass, Connection connection) 
{
    Statement stmt = null;
    String query = "INSERT INTO login " +
                   "VALUES (DEFAULT,'"+ user +"','"+ pass +"')";
    try {
       stmt = connection.createStatement();
       stmt.executeUpdate(query);
    } catch (SQLException e ) {
        JOptionPane.showMessageDialog(null,"SQL Exception");
        return 0;
    } 
    finally 
    {
        if (stmt != null) 
        { 
            try {
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


