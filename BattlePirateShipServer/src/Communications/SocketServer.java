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
   public static void main(String[] args) throws IOException {
 
     
      ExecutorService executor = null;
      try ( ServerSocket serverSocket = new ServerSocket(3216); ){
      
        executor = Executors.newFixedThreadPool(5);
        System.out.println("Waiting on 3216.");
       while (true) {
        Socket clientSocket = serverSocket.accept();
        Runnable worker = new HandleClient(clientSocket);
        executor.execute(worker);
         }
      } catch (IOException e) {
         System.err.println("Could not listen on port: 3216.");
         System.exit(1);
      }

   }
}
