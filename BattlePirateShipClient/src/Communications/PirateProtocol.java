package Communications;



  /** This class represents the communication protocol between the client and the server.
    * It has a run method that has calls to both the encode methods and decode methods also present on this class.
    */

public class PirateProtocol {

    private final SocketClient client;
    
    /**
     *  The constructor method initializes the class variables
     */
    public PirateProtocol()
    {
        client = new SocketClient();
    }
    
   /**
    * This method is the main method of the class. 
    * <p>
    * It encodes a string, sends it to the socket and waits for a response. 
    * It then decodes to the string received and returns.
    * 
    * @param type type of message we are encoding
    * @param Input information to be encoded
    * @param argnum number of arguments on the Input parameter
    * 
    * @return an array of strings containing the decoded message from the server
    */
    public String[] run(int type, String[] Input, int argnum) 
    {
        String encoded;
        String received;
        String[] decoded;
        
        encoded = encode(type,Input,argnum);
        received = client.run(encoded);
        
        decoded = decode(received);
        
        return decoded;
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
    
    public String encode(int type, String[] Input, int argnum) 
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
        
        return encoded; //returns true to signal everything is ok. 
    }
    
    /** This method decodes a string previously encoded into all the strings that composed it.
     * 
     * It does this by using the string split method
     * 
     * @param Input String that is going to be split
     * 
     * @return an array of strings with the information      *    
     */
    
    public String[] decode(String Input) 
    { 
        
        // variable for cycle control
        int i=0;
        
        String decoded[] = new String[20];  // 20 as the max size because we will never pass more than 20 strings
          
        // splitting the string
        for (String retval: Input.split("#")) 
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

