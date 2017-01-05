
package BusinessLogic;



import Communications.PirateProtocol;
import Communications.SocketClient;
        
/**
 *  This class deals with the requests from the interface to get user information.
 * 
 */
public class User 
{
  private final SocketClient client;
  private PirateProtocol pirate;
  
  /**
  * The constructor does nothing
  */
  public User(SocketClient client)
  {
      this.client = client;
      pirate = new PirateProtocol(client);
      
  }
  
  /** This method attempts to get the userinfo of a given user
   * 
   * @param userid id of the user
   * @return the information required on success, -1 on failure
   */
  public String[] get(int userid)
  {
      String[] tosend = new String[1];
      tosend[0] = Integer.toString(userid);
     
      String[] received = new String[20];
      received = pirate.run(9,tosend,1);
      
      if(!received[1].equals("ERROR"))
      {
           return received;
      }
      else
      {
          return null;
      }
  }
    
  /** This method attempts to get the friends list of a given user
   * 
   * @param userid id of the user
   * @return friends list on success, null on error
   */
  public String[] getFriends(int userid)
  {
    String[] tosend = new String[1];
    tosend[0] = Integer.toString(userid);
      
    String[] received = new String[20];
    received = pirate.run(10,tosend,1);
    
     if(!received[1].equals("ERROR"))
      {
           return received;
      }
      else
      {
          return null;
      }
    
  }    
}
