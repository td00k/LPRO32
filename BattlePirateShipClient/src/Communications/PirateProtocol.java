package Communications;

import java.io.IOException;

  /** This class represents the communication protocol between the client and the server.
    * It has a method to decode a string and return it, and a method to encode a string and send to the Socket Class
    * based on a protocol we chose.
    */

public class PirateProtocol {

    private final SocketClient client;
    
    
    public PirateProtocol()
    {
        client = new SocketClient();
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
    
    public String[] decode() 
    { 
        
        // variable for cycle control
        int i=0;
        
        String decoded[] = new String[20]; // 20 as the max size because we will never pass more than 20 strings
        String received; // variable that stores the string we receive from the socket
        
        
          try 
       { 
           // waiting for a response
           received = client.receive();
           System.out.println("Client received reply!");
       } 
       catch (IOException ex) 
       {
           return null; //Whenever an exception occurs, it must return an error.
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
        
        // we are done!
        System.out.println("Pirate decode finished!");
        return decoded; 
    }
       

}

