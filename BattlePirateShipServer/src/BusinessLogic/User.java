
package BusinessLogic;

import DataAccess.JDBCHandler;

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
  
  private static String infoargs[];
  
 private JDBCHandler DBhandler;
  
  public User()
  {
   
      infoargs[0]="id";
      infoargs[1]="state";
      infoargs[2]="gamesplayed";
      infoargs[3]="wins";
      infoargs[4]="defeats";
      infoargs[5]="surrenders";
      infoargs[6]="rank";
      
      DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
                                        
  }
  
  public int get(int userid)
  {
      String query;
      String[] toreturn;
     
     query= "SELECT * " + "FROM userstats" + "WHERE id =" + userid;
     
      return DBhandler.run(9,query,null);
       
  }
  
  
  public int updateinfo(String[] args)
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
  
  
    public int getfriends(int userid)
     {
        String query;
        String[] toreturn;
     
      query= "SELECT * " + "FROM userfriends" + "WHERE id1 =" + userid;
     
      return DBhandler.run(9,query,null);
          
     }      
  
  
 }   