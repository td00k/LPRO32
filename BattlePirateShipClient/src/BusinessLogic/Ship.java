
package BusinessLogic;

/** 
     * This class handles the creation and management of ships.
     */

public class Ship
{
  private  int size; 
  private  int health;  
  private  int[] xpos; 
  private  int[] ypos; 
   
  /** The constructor initializes the class variables
   * 
   * @param size size of the ship
   */
   public Ship (int size) 
     {
         this.size = size;
         this.health = size;
         xpos = new int[size];
         ypos = new int[size];
     }
     
   /** This method returns the health of a ship
    * 
    * @return health
    */
   public int getHealth()
   {
       return this.health;
   }
   
   /** This method is called whenever a ship is hit. It stores the position where it was hit, and decrements the health
    * 
    * @param xpos position of the hit
    * @param ypos position of the hit
    */
   public void insertPos(int xpos,int ypos)
   {
       this.xpos[size-health] = xpos;
       this.ypos[size-health] = ypos;
   }
   
   /** This method returns all the positions where a ship was hit.
    * It is only called when a ship died
    * 
    * @return an int array containing the positions
    */
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
      
      public void hit()
      {
          health--;           
      }
      
      public int getSize()
      {
          return this.size;
      }
}
