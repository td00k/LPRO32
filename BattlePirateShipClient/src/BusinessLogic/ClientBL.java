/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import Communications.PirateProtocol; 
import Communications.SocketClient;
import java.io.IOException;




public class ClientBL  {
    
     // This class represents the ClientBL of the server.
     // It has methods to validate parameters passed from the interface package,
     // a method to encrypt the password using MD5 and login/register methods.
  
    
     //codes to signal what we will be passing to other layers
     private static final int REGISTER = 1;  
     private static final int LOGIN = 2;
   
     //class variables
     PirateProtocol pirate;       // variable to use the protocol methods
     SocketClient client;         // variable for client communications
   
   
     //constructor
     public ClientBL() 
     {
        // the constructor simply initializes the class variables
        pirate = new PirateProtocol();
        client = new SocketClient();
     }
     
     
     public boolean validate(String user, String pass)
     {
         // this method checks if the user and pass strings have more than 3 chars.
         
         // user -> string to be checked
         // pass -> string to be checked
         
         // return value : 1 if user and pass are valid, 0 if either of them fail the test.
         
         if( user.equals("") || pass.equals("") || (user.length() < 3) || (pass.length() < 3) )
         return false;
         
         else 
         return true;        
     }
     
     public boolean emailcheck(String email)
     {
        // This method checks if the string email is a valid email address
         
        // email -> string to be checked
         
        // return value : 1 if the email is valid and 0 if the the email isn't valid
         
        // This string has the parameters we want to check the email with.
        String regex;
        regex = "^([a-zA-Z0-9]+[a-zA-Z0-9._%\\-\\+]*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4})$";
        
        return email.matches(regex);        
     }
     
     
     
    public String encrypt(String str)
    {
        // This method encrypts the str using the MD5 algorithm
        
        // str -> string to be encrypted
        
        // return value: encrypted string if it worked, null if it didn't
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
       // This method logs the user in, by sending the user and password through
       // a socket so the server can validate the login
        
       // user -> username of the person trying to login
       // pass -> password of the person trying to login
        
       // return value : 1 if it worked, zero in case of an exception and -1 if
       // an unkown error occured
        
        
       // String variables
       String encoded;                  // variable that stores the encoded string we will send through the socket
       String received;                 // variable that stores the string we receive from the socket
       String[] decoded;                // variable that stores the decoded version of the received message
       String[] aux = new String[2];    // variable that contains the information to be passed to the encode
       
       // encrypting the password
       pass = encrypt(pass);
       
       // placing user and pass on a String array so we can pass it to the encode function
       aux[0] = user;
       aux[1] = pass;
       
       // encoding the string so it's ready to be sent
       encoded = pirate.encode(LOGIN,aux,2);
     
       try 
       {
           // sending the string
           client.send(encoded);
           System.out.println("Client Sent encoded!");
           
           // waiting for a response
           received = client.receive();
           System.out.println("Client received reply!");
       } 
       catch (IOException ex) 
       {
           return 0;
       }
       
       // decoding the received string
       decoded = pirate.decode(received);
       
       // checking what we received and returning
       if (decoded[0].equals("2") && decoded[1].equals("OK"))
       {
          // User logged in successfully
          return 1; 
       }
       else if (decoded[0].equals("2") && decoded[1].equals("ERROR"))
       {
           // Error while trying to login the user
           return 0;
       }
       else 
       {
           // Unkown error occurred
           return -1; 
       }
      
    }
    
    public int register(String name, String user, String pass,String email, String question, String answer ) 
    {
       // This method registers the user in the database, by sending all strings
       // on the arguments to the socket
       // name -> string containing the name
       // user -> string containing the username
       // pass -> string containing the password
       // email -> string containing the email
       // question -> string containing the security question
       // answer -> string containing the answer to the security question
        
       // return value : 1 if it worked, zero in case of an exception and -1 if
       // an unkown error occured
        
       // string variables
       String lockpass;                 // variable that stores the encrypted pass
       String[] aux = new String[6];    // variable that stores the information we are encoding
       String encoded;                  // variable that stores the encoded string
       String received;                 // variable that stores the received string
       String[] decoded;                // variable that stores the decoded information from the received message
       
       // encrypting the password
       lockpass = encrypt(pass);
       
       // placing the information on a String array so we can pass it to the encode function
       aux[0] = name;
       aux[1] = user;
       aux[2] = lockpass;
       aux[3] = email;
       aux[4] = question;
       aux[5] = answer;
       
       // encoding the string so it's ready to be sent
       encoded = pirate.encode(REGISTER,aux,6);
       
       try 
       {
           // sending the string
           client.send(encoded);
           
           // waiting for a response
           received = client.receive();
       } 
       catch (IOException ex) 
       {
           return 0;
       }
       
       // decoding the received string
       decoded = pirate.decode(received);
       
       //checking what we received and returning
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
