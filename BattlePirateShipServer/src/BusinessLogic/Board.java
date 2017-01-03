
package BusinessLogic;


 /** 
     * This class represents the Board where the game will be played.
     * It has methods to make a shot, and to update the board
     */

public class Board 
{
  private static int[][] positions; 
  private static int hits;
  private static int misses; 
  private static int gameid; 
  
  /**
   * The constructor initializes the class variables
   */
  public Board()
  {
      positions = new int[10][10];
      int i,j;
      for(i=0;i<10;i++)
      {
    		for(j=0;j<10;j++)
    		{
    			positions[i][j] = 0;
    		}
      }
  }
    
  /** This method registers a shot on the board
   * 
   * @param xpos position of the shot
   * @param ypos position of the shot
   * @return the code for a shot defined on the protocol and the number of the position hit.
   */
  public String[] shot(int xpos, int ypos)
  {
    String[] toreturn = new String[3];
    switch(positions[xpos][ypos])
    {
            case -1:
                    // position already hit before
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(-1);
                    break;
            case 0:
                    // water hit
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(0);
                    misses++;
                    break;
            case 1:
                    // hit one of the 3 health ships
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(1);
                    hits++;
                    if(hits == 17)
                    {
                        // game over
                        toreturn[2] = "END";
                    }
                    break;
            case 2:
                    // hit the 2 health ship
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(2);
                    hits++;
                    if(hits == 17)
                    {
                        // game over
                        toreturn[2] = "END";
                    }
                    break;
            case 3:
                    // hit the other 3 health ship
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(3);
                    hits++;
                    if(hits == 17)
                    {   
                       //game over
                       toreturn[2] = "END";
                    }
                    break;
            case 4:
                    // hit the 4 health ship
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(4);
                    hits++;
                    if(hits == 17)
                    {
                        // game over
                        toreturn[2] = "END";
                    }
                    break;
            case 5:
                    // hit the 5 health ship
                    toreturn[0] = Integer.toString(7);
                    toreturn[1] = Integer.toString(5);
                    hits++;
                    if(hits == 17)
                    {
                        // game over
                        toreturn[2] = "END";
                    }
                    break;
    }
    return toreturn;
  }

  /** This method updates the board to the board on the String[] args
   * 
   * @param args string[] containing the board
   * @return 1
   */
  public int update(String[] args)
  {
      int i,j;
      char[] aux = new char[10];


      for(i=0;i<10;i++)
      {
          aux = args[i].toCharArray();
          for(j=0;j<10;j++)
          {
              positions[i][j] = aux[j];
          }
      } 
    
      return 1;
  }

}