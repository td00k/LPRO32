
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
      int i,j;
      for(i=0;i<10;i++)
      {
    		for(j=0;j<10;j++)
    		{
    			positions[i][j] = 0;
    		}
      }
  }
    
  
  public String[] shot(int xpos, int ypos)
  {
  		String[] tosend = new String[2];
  		switch(positions[xpos][ypos])
  		{
  			case -1:
  						// position already hit before
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(-1);
  						break;
  			case 0:
  						// water hit
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(0);
  						misses++;
  						break;
  			case 1:
  						// hit one of the 3 health ships
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(1);
  						hits++;
  						if(hits == 17)
  						{
                                                        tosend[1] = Integer.toString(6);
  							// game over
  						}
  						break;
  			case 2:
  						// hit the 2 health ship
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(2);
  						hits++;
  						if(hits == 17)
  						{
  							// game over
  						}
  						break;
  			case 3:
  						// hit the other 3 health ship
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(3);
  						hits++;
  						if(hits == 17)
  						{
  							// game over
  						}
  						break;
  			case 4:
  						// hit the 4 health ship
  						tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(4);
  						hits++;
  						if(hits == 17)
  						{
  							// game over
  						}
  						break;
                        case 5:
  		    			// hit the 5 health ship
  		    			tosend[0] = Integer.toString(7);
  						tosend[1] = Integer.toString(5);
  						hits++;
  						if(hits == 17)
  						{
  							// game over
  						}
  		    			break;
  		}
  		return tosend;
  }

  public int update(String[] args)
  {
      String aux1 = args[1];
      int i,j;
      char[] aux;
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