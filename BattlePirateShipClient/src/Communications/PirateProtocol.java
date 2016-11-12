/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

/**
 *
 * @author Vitor
 */
 
import java.net.*;
import java.io.*;
 
public class PirateProtocol {
    
    
    
    public String encode(int type, String Input[], int argnum) { //enter type of connection (register/login/etc),array of strings, and argument number.
        int i=0;
        String tosend = type + ""; //Placing type in 1st position
        while (i < argnum) {
        
        tosend = tosend + "#" + Input[i]; //Rest of arguments separeted by #
        i++;
        
    }
        
        
        return tosend;//returns string separated by  #. 
    }
    
      public String[] decode(String Input) { //enter string separated by  #.  
        String toreceive = Input;
        String decoded[] = toreceive.split("#"); 
        
        return decoded; //returns array os strings 
   }
       

}

