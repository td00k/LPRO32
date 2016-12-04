package Communications;

import BusinessLogic.*;
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
    
     /**
      * This method is the main method of the class. 
      * <p>
      * It decodes a string and sends it to the authentication
      * When we get a response from the authentication, we encode it and send it to the handle client class
      * 
      * @param Input string received from the socket
      * 
      * 
      * @return the message to be sent to the client
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
    
     /** This method encodes various strings into a single string.
       * <p>
       * It does this by placing the type in the first position, and then a '#'.
       * Afterwards, it places one string of Input at a time, and then a '#'.
       * The result is one single string with all the information required separated by '#'
       * 
       * @param type this is a variable that contains the type of what we are encoding ( 1 for register, 2 for login etc... )
       * @param Input this contains all the strings which will be concatenated into a single one
       * @param argnum this contains the amount of strings on Input
       *
       * @return encoded string
       */
    
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
      * It does this by using the string split method
      * 
      * @param Input String that is going to be split
      * 
      * @return an array of strings with the information
      */    
    
    public String[] decode(String Input) 
    { 
        
        // variable for cycle control
        int i=0;
        
        String decoded[] = new String[20]; // 20 as the max size because we will never pass more than 20 strings
     
        // splitting the string
        for (String retval: Input.split("#")) 
        {
                      System.out.println(retval);
                      decoded[i] = retval;
                      i++;
        }
        
        //placing null on the last string
        decoded[i]=null;
        
        // we're done!
        System.out.println("Pirate decode finished!");
        return decoded; 
    }
       
}

