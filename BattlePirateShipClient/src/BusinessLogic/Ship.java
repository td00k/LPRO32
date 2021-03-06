
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
  private  int poscounter;
   
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
         poscounter = size;
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
       if(poscounter > 0)
       {
            this.xpos[size-poscounter] = xpos;
            this.ypos[size-poscounter] = ypos;
            poscounter--;
       }
   }
   
   /** This method returns all the positions where a ship was hit.
    * It is only called when a ship died
    * 
    * @return an int array containing the positions
    */
      public int[] getPositions()
      {
          int[] toreturn = new int[size*2];
          int j = 0;
          for (int i = 0;i < size*2;i=i+2) 
          {
              toreturn[i]= xpos[j];
              toreturn[i+1]= ypos[j];
              j++;
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
