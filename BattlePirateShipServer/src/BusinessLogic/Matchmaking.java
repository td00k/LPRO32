
package BusinessLogic;

import Communications.PirateProtocol;
import DataAccess.JDBCHandler;


public class Matchmaking {
    
     //class variables
   JDBCHandler DBhandler;        // variable to use the handler methods
     
   public Matchmaking()
   {
        DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
   }
    
      /** 
     * This method creates a new game and assigns it to the game's list.
     * @param userid id of user creating the game
     * 
     * @return returns the created game id
     */
    
    public String[] create(int userid1,int userid2)
    {
        String received[];
        String query;
        
        // query possivelmente errada ?? deve ser INSERT INTO games VALUES (DEFAULT,'userid1','userid2')
        query = "INSERT INTO games " + "VALUES (DEFAULT,'" + userid1 + "','"+ userid2 + "')";
        received = DBhandler.run(4,query,null);
        
        String[] toreturn = new String[3];
        toreturn[0] = received[0];
        toreturn[1] = Integer.toString(userid1);
        toreturn[2] = Integer.toString(userid2);
        return toreturn;  
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
        String[] check = new String[3];
        check = search(userid);
        if(!check[0].equals("-1"))
        {
            tosend[0] = Integer.toString(3);
            tosend[1] = check[0];
            tosend[2] = check[1];
            tosend[3] = check[2];
        }
        else
        {
            check = create(userid,0);    
            if(!check[0].equals("-1"))
            {
            	tosend[0] = Integer.toString(3);
            	tosend[1] = check[0];
                tosend[2] = check[1];
                tosend[3] = check[2];
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
     
    public String[] search(int userid)
    {
        String received[];
        String query;
        String[] args = new String[1];
        String[] toreturn = new String[3];
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
