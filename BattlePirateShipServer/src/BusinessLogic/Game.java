package BusinessLogic;

import Communications.PirateProtocol;
import Communications.HandleClient;
import DataAccess.JDBCHandler;
import java.net.Socket;

     /** 
     * This class serves the purpose of creating games and assigning boards, players and ships into them.
     */

public class Game {
    
   private static int GamesCount;
   private static int player1;
   private static int player2;
   private static int time;
   private static int[] viewers;
   
   //class variables
   PirateProtocol pirate;       // variable to use the protocol methods
   JDBCHandler DBhandler;        // variable to use the handler methods
     
   public Game() 
     {
        pirate = new PirateProtocol();
        DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
     }
   
    /** 
     * This method creates a new game and assigns it to the game's list.
     * @param userid id of user creating the game
     * 
     * @return returns the created game id
     */
   
    public int create(int userid1,int userid2)
    {
        String received[];
        String query;
        
        // query possivelmente errada ?? deve ser INSERT INTO games VALUES (DEFAULT,'userid1','userid2')
        query = "INSERT INTO games " + "VALUES (DEFAULT,'" + userid1 + "','"+ userid2 + "')";
        received = DBhandler.run(4,query,null);
        return Integer.parseInt(received[0]);  
    }
     
     /** 
     * This method tries to find a game waiting for player. If none is found, it will create a new one.
     * @param userid id of user 
     * 
     * @return returns the assigned game id
     */
     
    public String[] quickgame(int userid)
    {
        int gameid;
        String[] tosend = new String[3];
        gameid = search(userid);
        if( gameid != -1)
        {
            tosend[0] = Integer.toString(3);
            tosend[1] = Integer.toString(gameid);
        }
        else
        {
            if( (gameid = create(userid,0)) != -1)
            {
            	tosend[0] = Integer.toString(3);
            	tosend[1] = Integer.toString(gameid);
            }
            else
            {
            	tosend[0] = Integer.toString(3);
            	tosend[1] = "ERROR";
            }
        }
        return tosend;
    }
    
    public String[] playwithfriend(int userid1, int userid2)
    {
        int gameid;
        String[] tosend = new String[2];
        if ( (gameid = create(userid1,userid2)) != -1)
        {
        	tosend[0] = Integer.toString(4);
        	tosend[1] = Integer.toString(gameid);
        }
        else
        {
        	tosend[0] = Integer.toString(4);
        	tosend[1] = "ERROR";
        }
        return tosend;
    }
     
    /** 
     * This method searches the games list to look for a game waiting for players.
     * 
     * @param userid userid of the person that created the game
     * 
     * @return returns the created game id, or -1 on error
     */
     
    public int search(int userid)
    {
        String received[];
        String query;
        String[] args = new String[1];
        args[0] = Integer.toString(userid);
        query = "SELECT * FROM games";
        received = DBhandler.run(3,query,args);
        return  Integer.parseInt(received[0]);
    }
     
       /** 
     * This method removes a game from the list after it is finished.
     * @param gameid id of game to remove
     * 
     * @return returns 1 if everything went okay, -1 otherwise
     */
     
    public int remove(int gameid)
    {
        return 1;
    }
}
