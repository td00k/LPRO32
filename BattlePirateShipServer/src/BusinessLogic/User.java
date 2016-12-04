
package BusinessLogic;

    /** 
     * This class handles user information to send to interface.
     */

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
  
  public User()
  {
      
  }
  
  public String[] get()
  {
      String[] info;
      info = new String[20];
      return info;
  }
  
  public boolean updateinfo(String[] args)
  {
      return true;
  }
    
}
