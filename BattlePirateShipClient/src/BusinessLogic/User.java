
package BusinessLogic;

    /** 
     * This class handles user information to send to interface.
     */

import Communications.PirateProtocol;
        
public class User 
{
  PirateProtocol pirate;
  
  public User()
  {
      pirate = new PirateProtocol();
      
  }
  
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
