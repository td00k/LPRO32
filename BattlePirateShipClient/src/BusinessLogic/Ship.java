
package BusinessLogic;


public class Ship {
    
  private static int GamesCount;  
   
   public Ship() 
     {
         
     }
     
     public int create(int userid, int rank)
     {
         if( userid == 0 )
         return 0;
         
         else 
         return 1;        
     }
    
     public int search(String user, String pass)
     {
         return 1;
     }
     
     public int remove(int player1, int player2 )
     {
         return 1;
     }
}
