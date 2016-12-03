package Communications;

import BusinessLogic.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;


  /** This class represents the communication protocol between the client and the server.
    * It has a method to decode a string and return it, and a method to encode a string and send to the Socket Class
    * based on a protocol we chose.
    */

public class PirateProtocol {

    private final Authentication auth;
    private final Game game;
    private final User user;
    private final HandleClient handler;
    
    
    
    public PirateProtocol(Socket PSocket)
    {
        auth = new Authentication();
        game = new Game();
        user = new User();
        handler = new HandleClient();
        
    }
    
     /** This method encodes various strings into a single string, separated by the '#' character and sends the string to the Socket Class
        *
        * @param type this is a variable that contains the type of what we are encoding ( 1 for register, 2 for login etc... )
        * @param Input this contains all the strings which will be concatenated into a single one
        * @param argnum this contains the amount of strings on Input
        *
        * @return 1 on success, 0 on error
        */
    
    public boolean encode(int type, String Input[], int argnum) 
    { 
       
        // variable for cycle control
        int i=0;
        
        //variable that contains the encoded string which we will return
        String encoded;
        
        //Placing type in 1st position
        encoded = type + "";
        
        //placing the rest of the information on the string
        while (i < argnum) 
        {
            // now we insert a '#' and the next string on Input
            encoded = encoded + "#" + Input[i]; 
            i++;  
        }
        
         try 
       {
           // sending the string
           client.send(encoded);
           System.out.println("Client Sent encoded!");
           
       } 
       catch (IOException ex) 
       {
           return false; //Whenever an exception occurs, it must return an error.
       }
        
        return true; //returns true to signal everything is ok. 
    }
    
    /** This method decodes a string previously encoded into all the strings that composed it.
         *
         *  Waits for Client Socket to reply with a message. Then it splits all the information and returns it.
         *
         * @return an array of strings with the information
         *
         * @see Communications.PirateProtocol       
         */
    
    public String[] decode(String received) 
    { 
        
        // variable for cycle control
        int i=0;
        
        String decoded[] = new String[20]; // 20 as the max size because we will never pass more than 20 strings
        String encoded;
        
        try 
        {
            encoded = handler.receive();
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
        
        // splitting the string
        for (String retval: received.split("#")) 
        {
                      System.out.println(retval);
                      decoded[i] = retval;
                      i++;
        }
        
        //placing null on the last string
        decoded[i]=null;
        
        // received register request
            if(decoded[0].equals("1"))
            {
                register(decoded);
            }
            // received a login request
            else if(decoded[0].equals("2"))
            {
                login(decoded);
            }
            
          
        
        return decoded; 
    }
       
    private int register(String[] decoded)
    {
        int auth_return=0; // this variable is used to keep the return of the handler method when we execute the query on the database
        String[] arg = {"OK"};  // variable created so we can send the OK message if everything went okay or Error if it didn't
        
        
        try {
                // executing the query on the DB
                auth_return = auth.register(decoded[1],decoded[2],decoded[3],decoded[4],decoded[5],decoded[6]);
            } 
        catch (SQLException ex) 
            {
                System.out.println(ex);
            }
                if(auth_return == 1)
                {
                    // it worked!
                    arg[0]="OK";
                    toSend = encode(1,arg,1);
                }   
                else
                {
                    // something went wrong
                    arg[0]="ERROR";
                    toSend = encode(1,arg,1);
                }
        return 1;
    }
    
    private int login(String[] decoded)
    {
        int auth_return=0;
        String[] arg = {"OK"};  // variable created so we can send the OK message if everything went okay or Error if it didn't
        
        try {
            auth_return = auth.login(decoded[1],decoded[2]);
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
                 if(auth_return == 1)
                {
                    // it worked!
                    arg[0]="OK";
                    toSend = encode(2,arg,1);
                }  
                else
                 {
                     // something went wrong
                     arg[0]="ERROR";
                    toSend = encode(2,arg,1);
                 }
        return 1;
    }
    
    private int quickmatch(String decoded)
    {
        return 1;
    }
    
    private int playwithfriend(String decoded)
    {
        return 1;
    }
    
    private int sendShot(String received)
    {
        return 1;
    }
    
    

}

