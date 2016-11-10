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
   public static void main(String[] args) throws IOException {
 
      Socket kkSocket = null;
      PrintWriter out = null;
      BufferedReader in = null;
 
      try {
         kkSocket = new Socket("localhost", 3216);
         System.out.println("Connected to server.");
         out = new PrintWriter(kkSocket.getOutputStream(), true);
         in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
      } catch (UnknownHostException e) {
         System.err.println("Don't know about host: localhost.");
         System.exit(1);
      } catch (IOException e) {
         System.err.println("Couldn't get I/O for the connection to: localhost.");
         System.exit(1);
      }
 
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String fromUser;
 
      while ((fromServer = in.readLine()) != null) {
         System.out.println("Received: " + fromServer);
         if (fromServer.equals("Bye."))
            break;
 
         fromUser = stdIn.readLine();
         if (fromUser != null) {
            out.println(fromUser);
         }
      }
 
      out.close();
      in.close();
      stdIn.close();
      kkSocket.close();
   }
}