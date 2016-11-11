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
import Communications.HandleClient;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class SocketServer {
    
   public static void main(String[] args) throws IOException 
   {
       
      // creates an executor variable so we can handle clients
      ExecutorService executor = null;
      
      // socket variable so we can listen to the client
      ServerSocket serverSocket;
      
      // variable to accept a new client
      Socket clientSocket;
      
      //variable to deal with a new client
      Runnable worker;
      
      try 
      {
        //listening to port number 3216
        serverSocket = new ServerSocket(3216);
        
        // maximum number of clients that can be handled at the same time 
        executor = Executors.newFixedThreadPool(20);
        
        while (true) 
        {
            //accepting a client
            clientSocket = serverSocket.accept();
            
            //calling Handle client to deal with the new client
            worker = new HandleClient(clientSocket);
            
            //dealing with the new client
            executor.execute(worker);
        }
      } 
      catch (IOException e) 
      {
         System.err.println("Could not listen on port: 3216.");
         System.exit(1);
      }

   }
}
