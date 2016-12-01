package Communications;


import java.io.*;
import java.net.*;

 
public class SocketClient
{
    
     /** This class has all methods that deal with the connection to the server and that deal with the socket.
      *It has methods to send a string, receive a string, and close connection.
      */
    
     // variable so we can connect to the server
     private  Socket PirateSocket;
    
     // variable to write to the socket
     private  PrintWriter writer;
    
     // variable to read from the socket
     private  BufferedReader reader;
   
    
    public SocketClient()
    {
        /** Opens the socket to gnomo.fe.up.pt and initializes variables to write to the socket and read from the socket
         */
         try
         {
             // connecting to the server
             PirateSocket = new Socket("gnomo.fe.up.pt", 3217);
             
             // connecting the write variable with the socket
             writer = new PrintWriter(PirateSocket.getOutputStream(), true);
             
             // connecting the read variable with the socket
             reader = new BufferedReader(new InputStreamReader(PirateSocket.getInputStream()));
         } 
         catch (UnknownHostException e) 
         {
             
         // Can't find host
         System.err.println("Host gnomo not found!");
         System.exit(1);
         } 
         catch (IOException ex) 
         {
             // Can't create socket
             System.out.println("Error creating socket.");
             System.exit(1);
         } 
      
    }
    
   public int send(String message) throws IOException 
   {
      /** This function sends a string through the socket
       *
       * @param message string which is sent
       *
       * @return 1
       */
      //sending message to socket
      writer.println(message);

      return 1;
   }
   
    
   public String receive() throws IOException 
   {
      /** This method receives a message from the socket
       *
       * @return received String
       *
       */
      // String where we store the received value
      String toReturn;
       
      // Reading the string
      toReturn = reader.readLine();
      System.out.println("Received: " + toReturn);
       
      // We're done
      return toReturn;
   }
   
    
      public int close()
   {
         /** This method closes the connection to the server and closes the write and read variables
         *
         * @return 1 if it worked and 0 if there was an error
         *
         */
       
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