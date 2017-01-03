package Communications;

import BusinessLogic.Authentication;
import BusinessLogic.Game;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

 /** This is the class that deals with the clients when they attempt to communicate. 
      * It has the run method that executes what we need to do in order to handle the client request.
      * It also has several other methods like send, receive and close all called in the run method
      */

public class HandleClient implements Runnable
{
     // Socket variables to create the write and read variables to it
     private Socket PirateSocket;
     ServerSocket serverSocket = null;
     Game[] games;

    
     // writer, read and protocol variables
     private  BufferedWriter writer;
     private  BufferedReader reader;
     private  PirateProtocol pirate;
    
     // query operation defines
     private static final int REGISTER = 1;
     private static final int LOGIN = 2;
  
     /** The constructor method initializes all the class specific variables
         * @param PSocket Socket connection to use on communications
         */
     
    public HandleClient(Socket PSocket, Game[] games) 
    {
      this.PirateSocket = PSocket;
      try 
         {
             // initializing variables
             writer = new BufferedWriter(new OutputStreamWriter(PirateSocket.getOutputStream()));
             reader = new BufferedReader(new InputStreamReader(PirateSocket.getInputStream()));
             pirate = new PirateProtocol(games);
             this.games = games;

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
    
    /** This method is called when we receive a client. 
     * <p>
     * It reads a message from the socket, and calls the protocol to decode it.
     * When we get an answer from the protocol we send it through the socket
     */
    
    public void run() 
    {

        // String variables
        String[] decoded;       // variable to keep the decoded string
        String received;        // variable to keep the received string
        String toSend="";       // variable created so we can send the OK message if everything went okay or Error if it didn't

        try 
        {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            while(true)
            {
                // reading the string from the socket
                received = receive();

                // calling the protocol
                System.out.println("Server Received encoded string!");
                toSend = pirate.run(received);

                // sending the reply to the client
                send(toSend);
                System.out.println("Sent Reply to Client!");
            }
            
        } 
	catch (IOException e) 
        {
           System.out.println("I/O exception: " + e);
        } 
		
	catch (Exception ex) 
        {
	   System.out.println("Exception in Thread Run. Exception : " + ex);
	}

    }

      /** This method writes a string to the socket
       *
       * @param message string that is sent
       * @throws IOException When there is an error writing to socket
       * @return 1
       */
    
    public int send(String message) throws IOException 
   {
	  writer.write(message);
	  writer.newLine();
	  writer.flush();
          
      return 1;
   }
   
    /** This method receives a string from the socket
       *  @throws IOException When there is an error reading to socket
       *  @return the received string
       *
       */
    
   public String receive() throws IOException 
   {
      String toReturn;
      toReturn = "1";
      toReturn = reader.readLine();
      System.out.println("Received: " + toReturn);
      return toReturn;
   }
  
   /* This method closes the class variables
        */
   
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
