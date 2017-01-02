
package BusinessLogic;

/** 
     * This class handles the creation and placement of ships.
     */

public class Ship
{
  private  int size; 
  private  int health;  
  private  int[] xpos; 
  private  int[] ypos; 
   
   public Ship (int size) 
     {
         this.size = size;
         this.health = size;
         xpos = new int[size];
         ypos = new int[size];
     }
     
   public int getHealth()
   {
       return this.health;
   }
   
   public void insertPos(int xpos,int ypos)
   {
       this.xpos[size-health] = xpos;
       this.ypos[size-health] = ypos;
       this.health--;
   }
   
      public int[] getPositions()
      {
          int[] toreturn = new int[size*2];
          for (int i = 0;i < size*2;i=i+2) 
          {
              toreturn[i]=xpos[i];
              toreturn[i+1]=ypos[i];
          }
          return toreturn;
      }
}
