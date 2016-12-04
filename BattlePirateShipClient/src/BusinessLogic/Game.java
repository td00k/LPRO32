
package BusinessLogic;

import Communications.PirateProtocol;
import Communications.SocketClient;

     /** 
     * This class serves the purpose of creating games and assigning boards, players and ships into them.
     */  
  
public class Game {
    
   private static int GamesCount;
   private static int player1;
   private static int player2;
   private static int time;
   private static int[] viewers;
   
     //class variables
     PirateProtocol pirate;       // variable to use the protocol methods
     SocketClient client;  
 
     /** 
     * This method creates and initializes the game's class components.
     */
   public Game() 
     {
        pirate = new PirateProtocol();
        client = new SocketClient();
     }
   
   /** 
     * This method will send a request to the server to join any game.
     * @param userid id of user 
     * 
     * @return returns the assigned game id
     */
   
     public int quickgame(int userid)
     {
         if( 1 == 0 )
         return 0;
         
         else 
         return 1;        
     }
    
    /** 
     * This method allows a player to play with his friend.
     * @param userid1 id of user creating the game
     * @param userid2 id of user's friend
     * @return returns the  game id
     */
     
     public int playwithfriend(int userid1, int userid2)
     {
         return 1;
     }
 
     
}
