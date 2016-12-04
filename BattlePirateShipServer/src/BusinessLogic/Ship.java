package BusinessLogic;


public class Ship {
    
  private static int size; 
  private static int health; 
  private static boolean vertical; 
  private static int xpos; 
  private static int ypos; 
   
   public Ship(int xpos, int ypos, int size, boolean vertical) 
     {
         this.size = size;
         this.xpos = xpos;
         this.ypos = ypos;
         this.vertical = vertical;
     }
     
     public int place(int xpos, int ypos, int health, boolean vertical)
     {
         if( xpos == 0 )
         return 0;
         
         else 
         return 1;        
     }
    
     public int hit()
     {
         int health=0;
         return health;
     }

}
