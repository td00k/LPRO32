
package BusinessLogic;

 /** 
     * This class represents the Board where the game will be played.
     * It has methods to make a shot and to place Ships on the board.
     * Last but not least, it has a method to send the board with ships placed to the server
     */

public class Board 
{
  private static int[][] positions; 
  private static int hits;
  private static int misses; 
  private static int gameid; 
  
   /** 
     * This method creates and initializes the board where the player's ships will be placed.
     */
  public Board()
  {
      
  }
  
   /** 
     * This method's aim is to register a shot on the board and call the ship class to verify if a ship is hit.
     * @param xpos x-axis position of shot
     * @param ypos y-axis position of shot
     * 
     * @return ship id if a ship was hit or false otherwise
     */  
  
  public int shot(int xpos, int ypos)
  {
      return 1;
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
  
  /** 
     * This method's aim is to send a player's board with ships placed to the server.
     * @param userid id of the user playing
     * @param gameid if of the game assigned to user
     * 
     * @return ship id if a ship was hit or false otherwise
     */  
  
  
   public boolean sendBoard(int userid, int gameid)
  {
      return true;
  }
  
  
}
