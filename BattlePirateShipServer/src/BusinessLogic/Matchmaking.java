
package BusinessLogic;

import DataAccess.JDBCHandler;

/** This class deals with creating the querys to add and create a game.
 * It proceeds to send them to the DB so they are executed, and returns an answer depending on what happened
 */
public class Matchmaking {
    
     //class variables
   JDBCHandler DBhandler;        // variable to use the handler methods
     
   /**
    * The constructor simply initializes the handler variable
    */
   public Matchmaking()
   {
        DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
   }
    
 /** This method creates a query to create a new game, and assign it to the games list
  * 
  * @param userid1 userid of player1
  * @param userid2 userid of player 2
  * @return String[] containing a response ready to be encoded, based on what happened.
  */
    
    public String[] create(int userid1,int userid2)
    {
        String received[];
        String query;
        String[] toreturn = new String[3];
         
        query = "INSERT INTO games VALUES (DEFAULT,'" + userid1 + "','"+ userid2 + "')";
        received = DBhandler.run(4,query,null);
        if(received[0].equals("-1"))
        {
            toreturn[0] = Integer.toString(-1);
        }
        else
        {
            toreturn[0] = received[0];
            toreturn[1] = Integer.toString(userid1);
            toreturn[2] = Integer.toString(userid2);
        }
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
        String[] tosend = new String[4];
        String[] check = new String[3];
        check = search(userid);
        System.out.println("Chegou!");
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
        System.out.println("Chegou!");
        return tosend;
    }
    
    /** This method creates a query to place both users with the passed id's on a game
     * 
     * @param userid1 id of the first user
     * @param userid2 id of the "friend"
     * 
     * @return an appropriate answer ready to be encoded and setnt to the client
     */
    public String[] playwithfriend(int userid1, int userid2)
    {
        int gameid;
        String[] tosend = new String[2];
        String[] check = new String[3];
        check = create(userid1,userid2);
        if (!check[0].equals(-1))
        {
        	tosend[0] = Integer.toString(4);
        	tosend[1] = check[0];
                tosend[2] = check[1];
                tosend[3] = check[2];
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
     * @param userid userid of the user trying to look for a game
     * 
     * @return returns a string[] containing the appropriate response from the server
     */
     
    public String[] search(int userid)
    {
        String[] received;
        String query;
        String[] args = new String[1];
        String[] toreturn = new String[3];
        
        args[0] = Integer.toString(userid);
        query = "SELECT * FROM games";
        System.out.println("Antes dbhanlder");
        received = DBhandler.run(3,query,args);
        System.out.println("depois" + received[0]);
        if(received[0].equals("-1"))
        {
            System.out.println("nao é aqui");
            toreturn[0] = "-1";
            
        }
        else
        {
            System.out.println("nope");
            toreturn[0] = received[0];
            toreturn[2] = Integer.toString(userid);
            toreturn[1] = received[1];
        }
        
        return toreturn;
    }
     
    /** 
     * This method removes a game from the list after it is finished.
     * @param gameid id of game to remove
     * 
     * @return a string[] containing the server response
     */
     
    public String[] remove(int gameid)
    {
       String query;
       String received[];
       String toreturn[] = new String[2];
       toreturn[0] = Integer.toString(5);
       query = "DELETE FROM games where id = " + gameid;
       received = DBhandler.run(5,query,null);
       toreturn[1] = received[0];
       return toreturn;
    }
  
}
