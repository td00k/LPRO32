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


public class HandleClient implements Runnable{
    
     /** This is the class that deals with the clients when they attempt to communicate. 
      * It has the run method that executes what we need to do in order to handle the client request.
      * It also has several other methods like send, receive and close to aid the run method in whatever in needs.
     */
    
     // Socket variables to create the write and read variables to it
     private Socket PirateSocket;
     ServerSocket serverSocket = null;
    
     // handler variable for the DB methods
     ServerBL handler;
    
     // writer, read and protocol variables
     private  BufferedWriter writer;
     private  BufferedReader reader;
     private  PirateProtocol pirate;
    
     // query operation defines
     private static final int REGISTER = 1;
     private static final int LOGIN = 2;
  
    
    public HandleClient(Socket PSocket) 
    {
        /** The constructor method initializes all the class specific variables
         */
      this.PirateSocket = PSocket;
      try 
         {
             // initializing variables
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
        /** This method is called when we receive a client. 
        * It reads a message from the socket and executes the request we receive.
        * Then, it sends an appropriate answer depending on what happened.
        */
        
        // String variables
        String encoded;         // variable to keep the encoded string
        String[] decoded;       // variable to keep the decoded string
        String toSend="";       // variable created so we can send the OK message if everything went okay or Error if it didn't
        String[] arg = {"OK"};  // variable created so we can send the OK message if everything went okay or Error if it didn't
        
        // variables for checking
        int handler_return;     // this variable is used to keep the return of the handler method when we execute the query on the database
        
        
        try 
        {
            // reading the string from the socket
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            
            encoded = receive();
            
            // decoding the string
            System.out.println("Server Received encoded string!");
            decoded = pirate.decode(encoded);
            
            // received register request
            if(decoded[0].equals("1"))
            {
                // executing the query on the DB
                handler_return = handler.register(decoded[1],decoded[2],decoded[3],decoded[4],decoded[5],decoded[6]);
                if(handler_return == 1)
                {
                    // it worked!
                    arg[0]="OK";
                    toSend = pirate.encode(1,arg,1);
                }   
                else
                {
                    // something went wrong
                    arg[0]="ERROR";
                    toSend = pirate.encode(1,arg,1);
                }
            }
            // received a login request
            else if(decoded[0].equals("2"))
            {
                handler_return = handler.login(decoded[1],decoded[2]);
                 if(handler_return == 1)
                {
                    // it worked!
                    arg[0]="OK";
                    toSend = pirate.encode(2,arg,1);
                }  
                else
                 {
                     // something went wrong
                     arg[0]="ERROR";
                    toSend = pirate.encode(2,arg,1);
                 }
            }
            
            // sending the reply to the client
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

    }

    public int send(String message) throws IOException 
   {
      /** This method writes a string to the socket
       *
       * @param message string that is sent
       *
       * @return 1
       */
	  writer.write(message);
	  writer.newLine();
	  writer.flush();
          
      return 1;
   }
   
   public String receive() throws IOException 
   {
      /** This method receives a string from the socket
       *
       *  @return the received string
       *
       */
      String toReturn;
      toReturn = "1";
      toReturn = reader.readLine();
      System.out.println("Received: " + toReturn);
      System.out.println("Out of cycle");
      return toReturn;
   }
  
   
   public int close()
   {
       /* This method closes the class variables
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
