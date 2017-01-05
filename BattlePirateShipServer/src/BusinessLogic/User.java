
package BusinessLogic;

import DataAccess.JDBCHandler;

    /** 
     * This class handles requests to get user information.
     * This can range between stats, his personal information and friends.
     */

public class User 
{
  private  int userid; 
  private  String name;
  private  String username; 
  private  int rank; 
  private  int wins; 
  private  int defeats; 
  private  int gamesplayed; 
  private  int surrenders; 
  
  private String infoargs[];
  
  private JDBCHandler DBhandler;
  
  /**
   *  The constructor simply initializes a Handler variable so we can call the run method to execute querys on the DB, and a pre-defined array to help in the creation of the querys
   */
  public User(JDBCHandler DBhandler)
  {
      infoargs = new String[7];
      infoargs[0]="id";
      infoargs[1]="gamesplayed";
      infoargs[2]="wins";
      infoargs[3]="defeats";
      infoargs[4]="surrenders";
      infoargs[5]="rank";
      
      this.DBhandler = DBhandler;                                 
  }
  
  /** This method creates a query to grab all the user stats from the userstats database table and sends it to the JDBChandler class to be executed
   * 
   * @param userid userid of the user
   * @return a string sent to the protocol to be encoded with a message containing what happened
   */
  public String[] get(int userid)
  {
      String query;
      String[] toreturn = new String[8];
      
      query= "SELECT * FROM userstats WHERE id = " + userid + "";
      String[] received = new String[8];
      received = DBhandler.run(9,query,null);
      toreturn[0] = "9";
      System.out.println("received[0] = " + received[0]);
      if(Integer.parseInt(received[0]) == -1)
      {
          toreturn[1] = "ERROR"; 
      }
      else
      {
          System.arraycopy(received, 0, toreturn, 1, 6);
      }
        
      return toreturn;   
  }
  
 /** This method creates a query to update the userstats table and sends it to the JDBChandler class to be executed
  * 
  * @param args information placed on the query to update the table
  * @return a string[] containing information depending on what the server answer is.
  */
  public String[] updateinfo(String[] args)
  {
      String query;
      query = "UPDATE userstats SET ";
      
      int i;
              
     for(i=1;i<6;i++)
     {
         query= query + infoargs[i] +"="+ Integer.parseInt(args[i]) + "," ;
     }
     
     query = query + infoargs[i] +"="+ Integer.parseInt(args[i]);
     
     query = query + "WHERE id=" + args[0];
      
     
     //E POSSIVEL A ULTIMA VIRGULA DO CICLO ESTAR A DAR ERRO MAS PARECE ME QUE ESTA A DAR
     
     return DBhandler.run(8,query,null);
     
    }
  
  /** This method creates a query to get the all the rows of the userfriends table that match a given userid
   * 
   * @param userid userid to be placed on the query
   * @return a string[] containing the server response 
   */
    public String[] getfriends(int userid)
     {
        String query;
        String[] toreturn = new String[101];
        String[] received;
        String[] id;
        int i ,j=1;
        query= "SELECT * FROM userfriends WHERE id1 =" + userid;
     
        id =  DBhandler.run(10,query,null);
        for (i = 1; i < Integer.parseInt(id[0]);i++)
        {
            query = "SELECT * FROM userinfo WHERE id = " + Integer.parseInt(id[i]);
            received = DBhandler.run(12,query,null);
            toreturn[j] = received[3];
            toreturn[j+1] = received[2];
            j = j+2;
        }
        toreturn[0] = Integer.toString(j-1);
        return toreturn;
     }   
    /** This method creates a query to add a friend to the friends list
     * 
     * @param userid userid of the user adding a friend
     * @param userid2 userid of the friend beeing added
     * 
     * @return a string[] containing the server response
     */
    public String[] addfriend(int userid, String username)
     {
        String query;
        int id;
        String[] received;
        query = "SELECT id FROM userinfo WHERE username LIKE '" + username + "'";
        
        received = DBhandler.run(14,query,null);
        
        query = "INSERT INTO userfriends VALUES('" + userid + "','" + Integer.parseInt(received[0]) +"')";
     
        return DBhandler.run(11,query,null);
          
     }      
  
  
 }   