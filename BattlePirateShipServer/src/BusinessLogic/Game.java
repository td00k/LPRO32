package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import java.net.Socket;

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
     HandleClient client;  
 
     
   public Game(Socket Psocket) 
     {
        pirate = new PirateProtocol();
        client = new HandleClient(Psocket);
     }
   
/** 
     * This method creates a new game and assigns it to the game's list.
     * @param userid id of user creating the game
     * 
     * @return returns the created game id
     */
   
   public int create(int userid)
     {
         int gameid = 0;
         
         if( 1 == 0 )
         return 0;
         
         else 
         return gameid;        
     }
     
     /** 
     * This method try's to find a game waiting for player. If none is found, it will create a new one.
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
    
     public int playwithfriend(int userid1, int userid2)
     {
         return 1;
     }
     
    /** 
     * This method searches the games list to look for a game waiting for players.
     * 
     * @return returns the created game id
     */
     
    public int search()
     {
         return 1;
     }
     
       /** 
     * This method removes a game from the list after it is finished.
     * @param gameid id of game to remove
     * 
     * @return returns true if everything went okay, false otherwise
     */
     
     public boolean remove(int gameid)
     {
         return true;
     }
}
