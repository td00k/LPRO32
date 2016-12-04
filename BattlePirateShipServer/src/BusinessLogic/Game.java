package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import java.net.Socket;


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
     
     private int search()
     {
         return 1;
     }
     
     public int remove()
     {
         return 1;
     }
}
