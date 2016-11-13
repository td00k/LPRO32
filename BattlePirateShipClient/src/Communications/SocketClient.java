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
/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 */ 
 
import java.io.*;
import java.net.*;

 
public class SocketClient {
    
     private  Socket PirateSocket;
     private  PrintWriter writer;
     private  BufferedReader reader;
   
    public SocketClient()
    {
         try 
         {
             PirateSocket = new Socket("gnomo.fe.up.pt", 3216);
             writer = new PrintWriter(PirateSocket.getOutputStream(), true);
             reader = new BufferedReader(new InputStreamReader(PirateSocket.getInputStream()));
         } 
         catch (UnknownHostException e) 
         {
         System.err.println("Host gnomo not found!");
         System.exit(1);
         } 
         catch (IOException ex) 
         {
             System.out.println("Error creating socket.");
             System.exit(1);
         } 
    }
    
   public int send(String message) throws IOException 
   {

      writer.println(message); //sending message to socket

      return 1;
   }
   
   public String receive() throws IOException 
   {
      String toReturn;
      while ((toReturn = reader.readLine()) != null) 
      {
         System.out.println("Received: " + toReturn);
      }
        return toReturn;
   }
   
      public int close()
   {
         try 
         {
             writer.close();
             reader.close();
             PirateSocket.close();
         }
         catch (IOException ex) 
         {
             return 0;
         }
     
      return 1;
   }
}