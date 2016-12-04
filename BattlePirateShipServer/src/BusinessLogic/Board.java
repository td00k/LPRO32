
package BusinessLogic;

 /** 
     * This class represents the Board where the game will be played.
     * It has methods to make a shot and to place Ships on the board.
     * Last but not least, it has a method to update the board when a shot is received.
     */

public class Board 
{
  private static int[][] positions; 
  private static int hits;
  private static int misses; 
  private static int gameid; 
  
  public Board()
  {
      
  }
    
  
  public boolean shot(int xpos, int ypos)
  {
      return true;
  }
  
    /** 
     * This method's aim is to place the ships on board.
     * 
     * @return true if everything went ok, false if there was an error
     */  
  
  
  public boolean PlaceShips()
  {
      return true;
  }
  
  public boolean update()
  {
      return true;
  }
}