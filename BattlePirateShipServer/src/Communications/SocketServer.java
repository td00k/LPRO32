
package Communications;

import BusinessLogic.Game;
import DataAccess.JDBCHandler;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
 

/** This class is the socket class for the server side.
      * It creates a connection to the socket and listens for clients.
      * After receiving a client, it calls the HandleClient class
      *
      * @see Communications.HandleClient#run() run()
      */

public class SocketServer {
    
      // creates an executor variable so we can handle clients
      private ExecutorService executor = null;
      
      // socket variable so we can listen to the client
      private ServerSocket serverSocket;
      
      private ServerSocket chatSocket;
      
      // variable to accept a new client
      private Socket clientSocket;
      
      //variable to deal with a new client
      private Runnable worker;
      
      private Game[] games;
      private ChatServer[] chatserver;
      
      private boolean online = false;
      
      private JDBCHandler DBhandler;
      
      private Thread inputThread;
      
    public SocketServer()
    {
      games = new Game[500];
      chatserver = new ChatServer[500];
      for (int i = 0; i < 500; i++) 
      {
        chatserver[i] = new ChatServer(i);
        games[i] = new Game();
      }
      
      

     DBhandler = new JDBCHandler();
 
    inputThread = new Thread(new Runnable() 
    {
        @Override
        public void run() 
        {
            System.out.println("Waiting for input commands..");
            while(!Thread.currentThread().isInterrupted()) 
            {
                try 
                {
                    Scanner scanner = new Scanner(System.in);
                    int reply = runCommand(scanner.nextLine());
                    switch(reply)
                    {
                        case 0:
                                startServer();
                                online = true;
                                break;
                        case 1:
                                online = false;
                                stopServer();
                                break;
                        case 2:
                                online = false;
                                stopServer();
                                System.exit(1);
                                break;
                    }
                } 
                catch(Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }
    });
    inputThread.start();  
        
        while (true) 
        {
            while(online)
            {
                System.out.println("Port 3216 Waiting for clients!");
                //accepting a client
                System.out.println("Waiting for accept connection!");
                try 
                { 
                    clientSocket = serverSocket.accept();
                    if(!online)
                    {
                        break;
                    }
                } 
                catch (IOException e) 
                {
                   System.err.println("Could not listen on port: 3216.");
                }
                System.out.println("Accepting connection! Creating new thread!");

                //calling Handle client to deal with the new client
                worker = new HandleClient(clientSocket,games,DBhandler);

                //dealing with the new client
                executor.execute(worker);
            }
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException ignore) 
            {
            }  
        }

    }
    
    
   public static void main(String[] args) 
   {
       
       SocketServer sockserv = new SocketServer();
   }
   
   private void startServer()
   {
          try {
              //listening to port number 3217
              
              DBhandler.open();
              serverSocket = new ServerSocket(3216);
              chatSocket = new ServerSocket(3217);
              
              // maximum number of clients that can be handled at the same time
              executor = Executors.newFixedThreadPool(20);
          } 
          catch (IOException ex) 
          {
              Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
          }
   }
   
   private void stopServer()
   {
          try 
          {
              DBhandler.close();
              executor.shutdownNow();
              serverSocket.close();
          } 
          catch (IOException ex) 
          {
              Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
          }
   }
   
   private int runCommand(String input)
      {
       if(input.equals("start server"))
       {
           return 0;
       }
       if(input.equals("stop server"))
       {   
           return 1;
       }
       if(input.equals("exit"))
       {   
           return 2;
       }
       if(input.equals("ban"))
       {   
           return 2;
       }
       return -1;
      }

}
