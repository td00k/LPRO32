package Communications;

import BusinessLogic.*;
import DataAccess.*;
import java.lang.String;


  /** This class represents the communication protocol between the client and the server.
    * It has a method to decode a string and return it, and a method to encode a string and send to the Socket Class
    * based on a protocol we chose.
    */

public class PirateProtocol 
{

    private  Authentication auth;
    private  Game[] game;
    private  Matchmaking matchmaking;
    private  User userinfo;
    
    public PirateProtocol()
    {
        auth = new Authentication();
        game = new Game[500];
        matchmaking = new Matchmaking();
        userinfo = new User();
        
    }
    
     /**
      * This method is the main method of the class. 
      * <p>
      * It decodes a string and sends it to the Business Logic according to what was received.
      * When we get a response from the Business Logic, we encode it and send it to the handle client class
      * 
      * @param Input string received from the socket
      * 
      * @return the message to be sent to the client
      */
    
    public String run(String Input)
    {
        String encoded = "";
        String[] decoded;
        String[] received;
        String[] args = new String[50];
        int argnum = 1;
        
        decoded = decode(Input);
        switch( decoded[0] )
        {
            case "1":
                        //register
                        received = auth.run(decoded);
                        args[0] = received[0];
        
                        encoded = encode(Integer.parseInt(received[0]),args,1);
                        break;
            case "2":
                        // login
                        received = auth.run(decoded);
                        args[0] = received[1];
                        argnum = 1;
                        
                        // if login worked, we need to place the userid on the args string so it's sent
                        if(received[1].equals("OK"))
                        {
                            args[1] = received[2];
                            argnum = 2;
                        }
                        System.out.println(received[1]);
                        encoded = encode(Integer.parseInt(received[0]),args,argnum);
                        break;
                     
            case "3":
                        
                        // quickgame
                        received = matchmaking.quickgame(Integer.parseInt(decoded[1]));
                        
                        if(received[1].equals("ERROR"))
                        {
                            args[0] = "ERROR";
                            
                        }
                        else
                        {
                            args[0] = received[2];
                            game[Integer.parseInt(received[1])].updatePlayers(Integer.parseInt(received[1]),Integer.parseInt(received[2]));
                        }
                        encoded = encode(Integer.parseInt(received[0]),args,1);
                        break;
            case "4":
                        // play with a friend
                        received = matchmaking.playwithfriend(Integer.parseInt(decoded[1]),Integer.parseInt(decoded[2]));
                        
                        if(received[1].equals("ERROR"))
                        {
                            args[0] = "ERROR";
                        }
                        else
                        {
                            args[0] = received[2];
                        }
                        
                        encoded = encode(Integer.parseInt(received[0]),args,1);
                        break;
            case "5":
                        // removegame
                        received = matchmaking.remove(Integer.parseInt(decoded[0]));
                        args[0] = received[1];
                        encoded = encode(Integer.parseInt(received[0]),args,1);
                        break;
            case "6":
                        // sendboard
                        System.arraycopy(decoded,3,args,0,10);
                        game[Integer.parseInt(decoded[1])].updateBoard(Integer.parseInt(decoded[2]),args); 
                        break;
            case "7":
                        // send shot
                        received = game[Integer.parseInt(decoded[1])].Shot(Integer.parseInt(decoded[2]),Integer.parseInt(decoded[3]),Integer.parseInt(decoded[4]));
                        break;
            case "8":
                        // update user stats
                        String[] aux = new String[7];
                        System.arraycopy(decoded, 1, aux, 0, 7);
                        received = userinfo.updateinfo(aux);
                        args[0] = received[1];
                        encoded = encode(Integer.parseInt(received[0]),args,1);
                        break;
            case "9":
                        String[] rcv1 = new String[3]; 
                        String[] rcv2 = new String[10]; 
                        // get user stats
                        
                        rcv1 = auth.get(Integer.parseInt(decoded[1]));
                        rcv2 = userinfo.get(Integer.parseInt(decoded[1]));
                        if(rcv1[1].equals("ERROR") || rcv2[1].equals("ERROR"))
                        {
                            args[0] = "ERROR";
                        }
                        else
                        {
                            args[0] = rcv1[1];
                            args[1] = rcv1[2];
                            args[2] = rcv1[3];
                            args[3] = rcv2[2];
                            args[4] = rcv2[3];
                            args[5] = rcv2[4];
                            args[6] = rcv2[5];
                            args[7] = rcv2[6];
                            args[8] = rcv2[7];
                            argnum = 9;
                        }
                        encoded = encode(Integer.parseInt(rcv1[0]),args,argnum);

                        //5#userid#name#username#state#gamesplayed#wins#defeats#surrenders#rank 
                        break;
            case "10":
                        // get user friends
                        received = userinfo.getfriends(Integer.parseInt(decoded[1]));
                        break;
            case "11":
                        // add friend
                        break;
            
        }
    
        return encoded;
       
    }
    
     /** This method encodes various strings into a single string.
       * <p>
       * It does this by placing the type in the first position, and then a '#'.
       * Afterwards, it places one string of Input at a time and then a '#'.
       * The result is one single string with all the information required separated by '#'
       * 
       * @param type this is a variable that contains the type of what we are encoding ( 1 for register, 2 for login etc... )
       * @param Input this contains all the strings which will be concatenated into a single one
       * @param argnum this contains the amount of strings on Input
       *
       * @return encoded string
       */
    
    public String encode(int type, String Input[], int argnum) 
    { 
       
        // variable for cycle control
        int i=0;
        
        //variable that contains the encoded string which we will return
        String encoded;
        
        //Placing type in 1st position
        encoded = type + "";
        
        //placing the rest of the information on the string
        while (i < argnum) 
        {
            // now we insert a '#' and the next string on Input
            encoded = encoded + "#" + Input[i]; 
            i++;  
        }
    
        return encoded;
    }
    
     /** This method decodes a string previously encoded into all the strings that composed it.
      * 
      * It does this by using the string split method
      * 
      * @param Input String that is going to be split
      * 
      * @return an array of strings with the information
      */    
    
    public String[] decode(String Input) 
    { 
        
        // variable for cycle control
        int i=0;
        
        String decoded[] = new String[20]; // 20 as the max size because we will never pass more than 20 strings
     
        // splitting the string
        for (String retval: Input.split("#")) 
        {
                      System.out.println(retval);
                      decoded[i] = retval;
                      i++;
        }
        
        //placing null on the last string
        decoded[i]=null;
        
        // we're done!
        System.out.println("Pirate decode finished!");
        return decoded; 
    }
       
}

