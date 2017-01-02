
package BusinessLogic;

import Communications.PirateProtocol;

 /** 
     * This class represents the Board where the game will be played.
     * It has methods to make a shot and to place Ships on the board.
     * Last but not least, it has a method to send the board with ships placed to the server
     */

public class Board 
{
  public  int[][] positions;
  private  int hits;
  private  int misses; 
  private  int gameid;
  private  Ship ship;
  private  PirateProtocol pirate;
  
   /** 
     * This method creates and initializes the board where the player's ships will be placed.
     */
  public Board(/*int gameid*/)
  {
      // variables for cicle control
      int i=0,j=0;
      pirate = new PirateProtocol();
      positions = new int[10][10];
      for(i=0;i<10;i++)
      {
          for(j=0;j<10;j++)
          {
              positions[i][j] = 0;
          }
      }
  }
  
   /** 
     * This method's aim is to register a shot on the board
     * 
     * @param userid id of the user who did the shot
     * @param xpos position of the shot
     * @param ypos position of the shot
     * @return a string containing a number that identifies what happened
     * 
     */  

  public String shot(int userid, int xpos, int ypos)
  {
      //variable to check the return of ship.hit
      int health;
      
      // variable that is going to be sent to the protocol
      String[] tosend = new String[12];
      
      // variable to receive from the protocol
      String[] received;
      
      // placing info on the string that is going to be sent
      tosend[0] = Integer.toString(gameid);
      tosend[1] = Integer.toString(userid);
      tosend[2] = Integer.toString(xpos);
      tosend[3] = Integer.toString(ypos);
      
      // calling the protocol and receiving an answer
      received = pirate.run(7,tosend,4);
      
      // checking what was received
      if(received[1] == "OK")
      {
          // received[2] contains a number identifying what happened
          return received[2];
      }
      else
      {
          // error
          return "fail";
      }
  }

  /** This methods sends a board a player to the server.
   * It is usually called after the ships have been placed
   * 
   * @param gameid id of the game
   * @param userid userid of the player
   * @return 1 on success, -1 on failure
   */
  public int Sendboard(int gameid,int userid)
  {
      // variables for cicle control
      int i=0,j=0;
      
      // variable that is going to be sent to the protocol
      String[] tosend = new String[12];
      
      // variable to receive from the protocol
      String[] received = {};
      
      // placing info on the string that is going to be sent
      tosend[0] = Integer.toString(gameid);
      tosend[1] = Integer.toString(userid);
      System.out.printf("\n");
      for(i=2;i<12;i++)
      {
          tosend[i] = "";
          System.out.printf("[");
          for(j=0;j<10;j++)
          {
              // each position of tosend is going to have a line of the board.
              tosend[i] = tosend[i] + positions[i-2][j];
              System.out.printf("%d", positions[i-2][j]);
          }
          System.out.printf("]\n");
      }
      
      // calling the protocol and receiving an answer
      received = pirate.run(6,tosend,12);
      
      // checking what was received
      if(received[1].equals("OK"))
      {
          return 1;
      }
      else
      {
          // error
          return -1;
      }
      
  }
}
