package Communications;

  /** This class represents the communication protocol between the client and the server.
    * It has a method to decode a string, and a method to encode a string
    * based on a protocol we chose.
    */

public class PirateProtocol {
    
     /** This method encodes various strings into a single string, separated by the '#' character
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
        String tosend;
        
        //Placing type in 1st position
        tosend = type + "";
        
        //placing the rest of the information on the string
        while (i < argnum) 
        {
            // now we insert a '#' and the next string on Input
            tosend = tosend + "#" + Input[i]; 
            i++;  
        }
        
        // string encoded!
        System.out.println("Pirate encode finished!");
        return tosend;//returns string separated by  #. 
    }
    
    /** This method decodes a string previously encoded into all the strings that composed it.
         *
         * @param Input string that will be separated
         *
         * @return an array of strings with the information
         *
         * @see Communications.PirateProtocol       
         */
    
    public String[] decode(String Input) 
    { 
        
        // variable for cycle control
        int i=0;
        
        // 20 as the max size because we will never pass more than 20 strings
        String decoded[] = new String[20]; 
        
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

