/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.net.Socket;
import java.sql.Connection;

/**
 *
 * @author Vitor
 */
public class ClientBL  {
    private String cmd;
    
     public ClientBL(String cmd) {
      this.cmd = cmd;
    }

    public String encrypt(String str)
    {
        // Encrypting string with MD5 Hash algorithm
        try 
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) 
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } 
        catch (java.security.NoSuchAlgorithmException e) 
        {
            return null;
        }
    }
    
    public void login(String user, String pass) 
    {
        
    }   
    
    public void register() 
    {
        
    }   

}
