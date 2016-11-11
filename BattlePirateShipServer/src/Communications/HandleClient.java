/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

import Communications.PirateProtocol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Vitor
 */
public class HandleClient implements Runnable{
     private Socket client;
  ServerSocket serverSocket = null;

    public HandleClient(Socket client) {
      this.client = client;
    }
    
    
    @Override
    public void run() 
    {
        BufferedReader reader;
        BufferedWriter writer;
        String UserInput;
      try
      {
        //reader and write variables to the socket
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        PirateProtocol pirp = new PirateProtocol();
        
        //reading from the socket
	UserInput = reader.readLine();
        pirp.decode(UserInput);
      } 
      catch (IOException e) 
      {
           
      } 
      catch (Exception ex) 
      {
          
      }
      
    }

}
