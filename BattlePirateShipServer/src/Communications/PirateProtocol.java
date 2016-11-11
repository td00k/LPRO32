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
    
    public String encode(String Input[], int argnum) {
        int i=0;
        String tosend = "";
        while (i < argnum) {
        
        tosend = tosend + "#" + Input[i];
        i++;
        
    }
        
        
        return tosend;
    }
    
      public String decode(String Input) {
        int i=0;
        String tosend = "";
        while (i<5) 
        {
        i++;  
        }
        
        
        return tosend;
    }
}
