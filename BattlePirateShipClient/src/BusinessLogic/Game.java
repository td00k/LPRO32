
package BusinessLogic;

import Communications.PirateProtocol;
import Communications.SocketClient;


public class Game {
    
   private static int GamesCount;
   private static int player1;
   private static int player2;
   private static int time;
   private static int[] viewers;
   
     //class variables
     PirateProtocol pirate;       // variable to use the protocol methods
     SocketClient client;  
 
     
   public Game() 
     {
        pirate = new PirateProtocol();
        client = new SocketClient();
     }
   
   public int create(int userid)
     {
         int gameid = 0;
         
         if( 1 == 0 )
         return 0;
         
         else 
         return gameid;        
     }
     
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
     
     public int search()
     {
         return 1;
     }
     
     public int remove()
     {
         return 1;
     }
}
