
package BusinessLogic;

import Communications.PirateProtocol;

 /** 
     * This class represents the Board where the game will be played.
     * It has methods to make a shot and to place Ships on the board.
     * Last but not least, it has a method to send the board with ships placed to the server
     */

public class Board 
{
  public static int[][] positions;
  private static int hits;
  private static int misses; 
  private static int gameid;
  private static Ship ship;
  private static PirateProtocol pirate;
  
   /** 
     * This method creates and initializes the board where the player's ships will be placed.
     */
  public Board(/*int gameid*/)
  {
      // variables for cicle control
      int i=0,j=0;
      
      for(i=0;i<10;i++)
      {
          for(j=0;j<10;j++)
          {
              positions[i][j] = 0;
          }
      }
  }
  
   /** 
     * This method's aim is to register a shot on the board and call the ship class to verify if a ship is hit.
     *
     * 
     * @return ship id if a ship was hit or false otherwise
     */  
  
  public String shot(int xpos, int ypos)
  {
      //variable to check the return of ship.hit
      int health;
      
      // variable that is going to be sent to the protocol
      String[] tosend = new String[12];
      
      // variable to receive from the protocol
      String[] received;
      
      // placing info on the string that is going to be sent
      tosend[0] = Integer.toString(gameid);
      tosend[1] = Integer.toString(xpos);
      tosend[2] = Integer.toString(ypos);
      
      // calling the protocol and receiving an answer
      received = pirate.run(7,tosend,3);
      
      // checking what was received
      if(received[1] == "OK")
      {
          // received[2] is a string containing hitalive, hitdead or fail, to the interface to know what happened.
          return received[2];
      }
      else
      {
          // error
          return "fail";
      }
  }

  
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
      for(i=2;i<12;i++)
      {
          for(j=0;j<10;j++)
          {
              // each position of tosend is going to have a line of the board.
              tosend[i] = tosend[i] + positions[i][j];
          }
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
