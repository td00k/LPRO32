package Communications;

import BusinessLogic.*;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.lang.String;


  /** This class represents the communication protocol between the client and the server.
    * It has a method to decode a string and return it, and a method to encode a string and send to the Socket Class
    * based on a protocol we chose.
    */

public class PirateProtocol {

    private final Authentication auth;
    
    public PirateProtocol()
    {
        auth = new Authentication();
    }
    
     /** This method encodes various strings into a single string, separated by the '#' character and sends the string to the Socket Class
        *
        * @param type this is a variable that contains the type of what we are encoding ( 1 for register, 2 for login etc... )
        * @param Input this contains all the strings which will be concatenated into a single one
        * @param argnum this contains the amount of strings on Input
        *
        * @return 1 on success, 0 on error
        */
    
    public String run(String Input)
    {
        String encoded;
        String[] decoded;
        String[] received;
        String[] args = new String[1];
        int type;
        
        
        decoded = decode(Input);
        
        received = auth.run(decoded);
        args[0] = received[1];
        
        encoded = encode(Integer.parseInt( received[0]),args,1 );
        
        return encoded;
    }
    
    
    public String encode(int type, String Input[], int argnum) 
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
    
        return encoded;
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
     
        // splitting the string
        for (String retval: received.split("#")) 
        {
                      System.out.println(retval);
                      decoded[i] = retval;
                      i++;
        }
        
        //placing null on the last string
        decoded[i]=null;
       
        return decoded; 
    }
       
}

