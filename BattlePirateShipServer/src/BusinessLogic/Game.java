package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import DataAccess.JDBCHandler;
import java.net.Socket;

     /** 
     * This class serves the purpose of playing games
     */

public class Game {
   
    
   private int player1;
   private int player2;
   //private int[] viewers;
   private Board[] boards;
     
   /**
    * The constructor initializes player1,player2 and the 2 boards
    */
   public Game() 
     {
            this.player1 = 0;
            this.player2 = 0;
            this.boards = new Board[2];
            
     }

   /** This method is called whenever we get a board update from the client after the ships have been placed
    * 
    * @param userid userid of the player updating the board
    * @param args the board itself
    */
    public void updateBoard(int userid, String[] args)
    {
        if(player1 == userid)
        {
            boards[0].update(args);
        }
        else if(player2 == userid)
        {
            boards[1].update(args);
        }
    }
    
    /** This method is used to update the players on a game
     * It only updates if the player is equal to zero ( has not been set yet)
     * @param userid1 if of the first player
     * @param userid2 id of the second player
     */
    public void updatePlayers(int userid1, int userid2)
    {
            if(this.player1 == 0)
            {
                this.player1 = userid1;
            }
            if(this.player2 == 0)
            {
                this.player2 = userid2;
            }
    }
    
    /** This method is used to register a shot on the opponent board.
     * 
     * @param userid id of the user who sent the shot
     * @param xpos position of the shot
     * @param ypos position of the shot
     * @return string[] containing an appropriate answer based on what happened
     */
    public String[] Shot(int userid,int xpos,int ypos)
    {
        String[] toreturn = new String[2];
        if(player1 == userid)
        {
            toreturn = boards[1].shot(xpos,ypos);
        }
        else if(player2 == userid)
        {
            toreturn =boards[0].shot(xpos,ypos);
        }
        return toreturn;
    }
}
