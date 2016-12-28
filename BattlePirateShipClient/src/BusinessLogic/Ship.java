
package BusinessLogic;

/** 
     * This class handles the creation and placement of ships.
     */

public class Ship
{
    
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
     
     public int[] place()
     {
         // listener aqui
         
         int[] returnval = null;

         //returnval[0] = xpos;
         //returnval[1] = ypos;
         //returnval[2] = health;
         //returnval[3] = orientation ( 1 for vertical, 0 for horizontal)
         
         // returns both positions, health and orientation
         return returnval;
     }
}
