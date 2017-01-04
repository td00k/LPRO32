package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import Communications.SocketServer;
import DataAccess.JDBCHandler;
import java.net.Socket;
import java.util.Random;

     /** 
     * This class serves the purpose of playing games
     */

public class Game {
   
   public int player1;
   public int player2;
   //private int[] viewers;
   private Board[] boards;
   private int playersready;
   private int randomNum;
     
   /**
    * The constructor initializes player1,player2 and the 2 boards
    */
   
   public Game() 
     {
        this.player1 = 0;
        this.player2 = 0;
        this.boards = new Board[2];
        boards[0] = new Board();
        boards[1] = new Board(); 
        playersready = 0;

        int max = 2;
        int min = 0;
        
        Random rand = new Random();

        randomNum = rand.nextInt((max - min) + 1) + min;
            
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
            playersready++;
            
            boards[0].update(args);
        }
        else if(player2 == userid)
        {
            playersready++;
            boards[1].update(args);
        }
        System.out.println("Board received! Flag is now " + playersready);
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
        String[] toreturn = new String[3];
        if(player1 == userid)
        {
            toreturn = boards[1].shot(xpos,ypos);
            boards[1].updateShotFlag();
            if(toreturn[0].equals("6"))
            {
                toreturn[2] = Integer.toString(player1); // player 1 wins
            }
        }
        else if(player2 == userid)
        {
            toreturn = boards[0].shot(xpos,ypos);
            boards[0].updateShotFlag();
            if(toreturn[0].equals("6"))
            {
                toreturn[2] = Integer.toString(player2); //player 2 wins
            }
        }
        return toreturn;
    }
    
    public String[] receiveShot(int userid)
    {
        String[] toreturn = new String[4];
        int[] lastshot;
        if(player1 == userid)
        {
            System.out.println("shot flag p1 is " + boards[0].getShotFlag() );
           while(!boards[0].getShotFlag())
           {
                try {
                Thread.sleep(50); // for 100 FPS
                } 
                catch (InterruptedException ignore) 
                {
                }
           }
           boards[0].updateShotFlag();
           System.out.println("out of while flag p1 is " + boards[0].getShotFlag() );
           lastshot = boards[0].getLastShot();
            for (int i = 0; i < 4; i++) 
            {
                toreturn[i] = Integer.toString(lastshot[i]);
            }
        }
        else if(player2 == userid)   
        {
           System.out.println("shot flag p2 is " + boards[1].getShotFlag() );
           while(!boards[1].getShotFlag())
           {
                try {
                Thread.sleep(50); // for 100 FPS
                } 
                catch (InterruptedException ignore) 
                {
                }
           }
           boards[1].updateShotFlag();
           System.out.println("out of while p2 flag is " + boards[1].getShotFlag() );
           lastshot = boards[0].getLastShot();
             for (int i = 0; i < 4; i++) 
             {
                toreturn[i] = Integer.toString(lastshot[i]);
             }     
        }
        return toreturn;
    }
    
    public void readyToStart()
    {
        while(playersready != 2)
        {    
            try {
                Thread.sleep(50); // for 100 FPS
                } 
                catch (InterruptedException ignore) 
                {
                }
        }
    }
    
    public int readyToBegin()
    {
        while((this.player1 == 0) || (this.player2 == 0))
        {   
            try {
                Thread.sleep(10); // for 100 FPS
                } 
                catch (InterruptedException ignore) 
                {
                }
        }
  
        if(randomNum > 1)
        {
            return this.player1;
        }
        else
        {
            return this.player2;
        }
    }
}
