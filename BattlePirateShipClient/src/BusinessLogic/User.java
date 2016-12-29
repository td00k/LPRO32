
package BusinessLogic;

    /** 
     * This class handles user information to send to interface.
     */

import Communications.PirateProtocol;
        
public class User 
{
  private static int userid; 
  private static String name;
  private static String username; 
  private static int rank; 
  private static int wins; 
  private static int defeats; 
  private static int gamesplayed; 
  private static int surrenders; 
  
  PirateProtocol pirate;
  public User()
  {
      
      pirate = new PirateProtocol();
      
  }
  
  public String[] get(int userid)
  {
      String[] tosend = new String[1];
      tosend[0] = Integer.toString(userid);
      
      return pirate.run(8,tosend,1);
  }
    
  public String[] getFriends(int userid)
  {
    String[] tosend = new String[1];
    tosend[0] = Integer.toString(userid);
      
    return pirate.run(10,tosend,1);
  }    
}
