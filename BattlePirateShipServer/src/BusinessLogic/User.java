
package BusinessLogic;

import DataAccess.JDBCHandler;

    /** 
     * This class handles requests to get user information.
     * This can range between stats, his personal information and friends.
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
  
  private static String infoargs[];
  
  private JDBCHandler DBhandler;
  
  /**
   *  The constructor simply initializes a Handler variable so we can call the run method to execute querys on the DB, and a pre-defined array to help in the creation of the querys
   */
  public User()
  {
      infoargs = new String[7];
      infoargs[0]="id";
      infoargs[1]="state";
      infoargs[2]="gamesplayed";
      infoargs[3]="wins";
      infoargs[4]="defeats";
      infoargs[5]="surrenders";
      infoargs[6]="rank";
      
      DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");                                   
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
      
      query= "SELECT * " + "FROM userstats" + "WHERE id =" + userid;
      String[] received;
      received = DBhandler.run(9,query,null);
      toreturn[0] = ""+9;
        if(received[0].equals("-1"))
      {
          toreturn[1] = "ERROR"; 
      }
      else
      {
          System.arraycopy(received, 0, toreturn, 1, 7);
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
        String[] toreturn;
     
      query= "SELECT * " + "FROM userfriends" + "WHERE id1 =" + userid;
     
      return DBhandler.run(9,query,null);
          
     }   
    /** This method creates a query to add a friend to the friends list
     * 
     * @param userid userid of the user adding a friend
     * @param userid2 userid of the friend beeing added
     * @return a string[] containing the server response
     */
    public String[] addfriend(int userid, int userid2)
     {
        String query;
        String[] toreturn;
     
      query= "SELECT * " + "FROM userfriends" + "WHERE id1 =" + userid;
     
      return DBhandler.run(9,query,null);
          
     }      
  
  
 }   