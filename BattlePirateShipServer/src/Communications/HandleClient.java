/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import BusinessLogic.ServerBL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Vitor
 */
public class HandleClient implements Runnable{
     private Socket PirateSocket;
     ServerSocket serverSocket = null;
     ServerBL handler;
     private  BufferedWriter writer;
     private  BufferedReader reader;
     private  PirateProtocol pirate;
     private static final int REGISTER = 1;
     private static final int LOGIN = 2;
     /// more to add...

    public HandleClient(Socket PSocket) 
    {
      this.PirateSocket = PSocket;
      try 
         {
             writer = new BufferedWriter(new OutputStreamWriter(PirateSocket.getOutputStream()));
             reader = new BufferedReader(new InputStreamReader(PirateSocket.getInputStream()));
             pirate = new PirateProtocol();
             handler = new ServerBL();
             System.out.println("Created HandleClient!");
         } 
         catch (UnknownHostException e) 
         {
         System.out.println("Host gnomo not found!");
         System.exit(1);
         } 
         catch (IOException ex) 
         {
             System.out.println("Error creating socket.");
             System.exit(1);
         } 
      
    }
    
    public void run() 
    {
        String encoded;
        String[] decoded;
        String toSend="";
        String[] arg = {"OK"};
        int handler_return;
        
        try 
        {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            encoded = receive();
            System.out.println("Server Received encoded string!");
            decoded = pirate.decode(encoded);
            if(decoded[0].equals("1"))
            {
                handler_return = handler.register(decoded[1],decoded[2],decoded[3],decoded[4],decoded[5],decoded[6]);
                if(handler_return == 1)
                {
                    arg[0]="OK";
                    toSend = pirate.encode(1,arg,1);
                }   
                else
                {
                    arg[0]="ERROR";
                    toSend = pirate.encode(1,arg,1);
                }
            }
            else if(decoded[0].equals("2"))
            {
                handler_return = handler.login(decoded[1],decoded[2]);
                 if(handler_return == 1)
                {
                    arg[0]="OK";
                    toSend = pirate.encode(2,arg,1);
                }  
                else
                 {
                     arg[0]="ERROR";
                    toSend = pirate.encode(2,arg,1);
                 }
            }

            send(toSend);
            System.out.println("Sent Reply to Client!");
            
        } 
	catch (IOException e) 
        {
           System.out.println("I/O exception: " + e);
        } 
		
	catch (Exception ex) 
        {
	   System.out.println("Exception in Thread Run. Exception : " + ex);
	}
    
        ////////////////

    }

    public int send(String message) throws IOException 
   {

	  writer.write(message);
	  writer.newLine();
	  writer.flush();
          
      return 1;
   }
   
   public String receive() throws IOException 
   {
      String toReturn;
      toReturn = "1";
      //while (toReturn != null) 
      //{
         toReturn = reader.readLine();
         System.out.println("Received: " + toReturn);
      //}
      System.out.println("Out of cycle");
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
