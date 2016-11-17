/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.net.Socket;
import java.sql.Connection;
import Communications.PirateProtocol; 
import Communications.SocketClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vitor
 */
public class ClientBL  {
    
   private static final int REGISTER = 1;  
   private static final int LOGIN = 2;
   PirateProtocol pirate;
   SocketClient client;
     public ClientBL() 
     {
        pirate = new PirateProtocol();
        client = new SocketClient();
     }
     
     public boolean validate(String user, String pass)
     {
         if( user.equals("") || pass.equals("") || (user.length() < 3) || (pass.length() < 3) )
         return false;
         
         else 
         return true;        
     }
     
     public boolean emailcheck(String email)
     {
        String regex;
        regex = "^([a-zA-Z0-9]+[a-zA-Z0-9._%\\-\\+]*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4})$";
        return email.matches(regex);        
     }
     
     
     
    public String encrypt(String str)
    {
        // Encrypting string with MD5 Hash algorithm
        try 
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < array.length; ++i) 
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
    
    public int login(String user, String pass) 
    {
       pass = encrypt(pass);
       String[] aux = {user, pass};
       String encoded;
       String received;
       String[] decoded;
       int i=0;
       
       encoded = pirate.encode(LOGIN,aux,2);
     
       try 
       {
           client.send(encoded);
            System.out.println("Client Sent encoded!");
           received = client.receive();
            System.out.println("Client received reply!");
       } 
       catch (IOException ex) 
       {
           return 0;
       }
       
       decoded = pirate.decode(received);
       
       if (decoded[0].equals("2") && decoded[1].equals("OK"))
       {
          return 1; 
       }
       else if (decoded[0].equals("2") && decoded[1].equals("ERROR"))
       {
           return 0;
       }
       else 
       {
           return -1; //Error occurred
       }
      
    }
    
    public int register(String name, String user, String pass,String email, String question, String answer ) 
    {
       
       String lockpass = encrypt(pass);
       String[] aux = {name, user, lockpass, email, question, answer};
       String encoded;
       String received;
       String[] decoded;
       
       encoded = pirate.encode(REGISTER,aux,6);
       
       try 
       {
           client.send(encoded);
           received = client.receive();
       } 
       catch (IOException ex) 
       {
           return 0;
       }
       
       decoded = pirate.decode(received);
       
       if (decoded[0].equals("1") && decoded[1].equals("OK"))
       {
          return 1; 
       }
       else if (decoded[0].equals("1") && decoded[1].equals("ERROR"))
       {
           return 0;
       }
       else 
       {
           return -1; //Error occurred
       }
       
    }   

}
