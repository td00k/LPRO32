
package BusinessLogic;

import Communications.PirateProtocol;
import Communications.SocketClient;

     /** 
     * This class serves the purpose of creating games and assigning boards, players and ships into them.
     */  
  
public class Game
{
   private static int player1;
   private static int player2;
   private static int time;
   private static int[] viewers;
   
     //class variables
    
     // variable to use the protocol methods
     PirateProtocol pirate;
 
     /** 
     * This method creates and initializes the game's class components.
     */
     public Game()
     {
        pirate = new PirateProtocol();
     }
   
   /**
     * This method sends a request to the server to join/create a game.
     * @param userid id of user 
     * 
     * @return returns the assigned game id
     */
   
     public int quickgame(int userid)
     {
         // variable that is going to be sent to the protocol
         String[] tosend = new String[12];
         
         // variable to receive from the protocol
         String[] received;
         
         // placing info on the string that is going to be sent
         tosend[0] = Integer.toString(userid);
         
         // calling the protocol and receiving an answer
         received = pirate.run(3,tosend,1);
         
         // checking what was received
         if(!received[1].equals("ERROR"))
         {
             // returning the gameid
             return Integer.parseInt(received[1]);
         }
         else
         {
             // error
             return -1;
         }
     }
    
    /** 
     * This method allows a player to play with his friend.
     * @param userid1 id of user creating the game
     * @param userid2 id of user's friend
     * @return returns the  game id
     */
     
     public int playwithfriend(int userid1, int userid2)
     {
         // variable that is going to be sent to the protocol
         String[] tosend = new String[12];
         
         // variable to receive from the protocol
         String[] received;
         
         // placing info on the string that is going to be sent
         tosend[0] = Integer.toString(userid1);
         tosend[1] = Integer.toString(userid2);
         
         // calling the protocol and receiving an answer
         received = pirate.run(4,tosend,2);
         
         // checking what was received
         if(received[1] == "OK")
         {
             // returning the gameid
             return Integer.parseInt(received[2]);
         }
         else
         {
             // error
             return -1;
         }
     }
    
     /** This method attempts to remove a game from the games table on the DB.
      * It sends the gameid to the server and waits for an answer.
      * 
      * @param gameid id of the game that is going to be removed
      * @return 1 on success, -1 on failure 
      */
    public int removegame(int gameid)
    {
        // variable that is going to be sent to the protocol
        String[] tosend = new String[12];
        
        // variable to receive from the protocol
        String[] received;
        
        // placing info on the string that is going to be sent
        tosend[0] = Integer.toString(gameid);
        
        // calling the protocol and receiving an answer
        received = pirate.run(5,tosend,1);
        
        // checking what was received
        if(received[1] == "OK")
        {
            // returning 1 on success
            return 1;
        }
        else
        {
            // error
            return -1;
        }
        
    }
    
}
