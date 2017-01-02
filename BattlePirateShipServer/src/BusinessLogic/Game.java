package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import DataAccess.JDBCHandler;
import java.net.Socket;

     /** 
     * This class serves the purpose of creating games and assigning boards, players and ships into them.
     */

public class Game {
   
    
   private int player1;
   private int player2;
   //private int[] viewers;
   private Board[] boards;
     
   public Game() 
     {
            this.player1 = 0;
            this.player2 = 0;
            this.boards = new Board[2];
            
     }

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
    
    public void updatePlayers(int userid1, int userid2)
    {
            this.player1 = userid1;
            this.player2 = userid2;
    }
    
    public String[] Shot(int userid,int xpos,int ypos)
    {
        if(player1 == userid)
        {
            boards[1].shot(xpos,ypos);
        }
        else if(player2 == userid)
        {
            boards[0].shot(xpos,ypos);
        }
    }
}
