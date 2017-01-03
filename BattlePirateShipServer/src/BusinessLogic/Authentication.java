package BusinessLogic;

import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;
import DataAccess.JDBCHandler;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

 /**
    * This class represents the Authentication of the server. 
    * <p>
    * It has the main method 'run' which calls the other methods present in this class based on the information we received from the protocol.
    * So far, the login and register methods have been implemented.
    */

public class Authentication 
{
   //  Query operation defines
   private static final int REGISTER = 1;
   private static final int LOGIN = 2;
   private static final int NEWUSERSTATS = 13;
    
    
   // Handler variable for the DB
   private static JDBCHandler DBhandler;

   // Query we are executing on the database
   private String query;
   
   /** This method initializes the Handler Variable so we can execute a query on the DB
             */
   
        public Authentication() 
        {
            // handler variable for database access
            DBhandler = new JDBCHandler("org.postgresql.Driver","jdbc:postgresql://dbm.fe.up.pt/lpro1632","lpro1632","ttva32");
        }

     
    /** This method is the main method of this class.
     * <p>
     * It receives a series of strings from the protocol, and calls a method to  execute what we received.
     * 
     * @param Input information received from the protocol
     * 
     * @return an appropriate String[] which contains the information to be based based on what was asked for us to execute and if it was a success or not.
     */
    public String[] run(String[] Input)
    {
        
       int check;
       String[] toreturn = new String[20];
       switch ( Integer.parseInt(Input[0]) )
       {
           case REGISTER: 
                          check = register(Input[1],Input[2],Input[3],Input[4],Input[5],Input[6]);
                          toreturn[0] = REGISTER + "";
                          if(check == 1)
                          {
                              toreturn[1] = "OK";
                          }
                          else
                          {
                              toreturn[1] = "ERROR";
                          }
                          break;
                          
           case LOGIN:
                          check = login(Input[1],Input[2]);
                          toreturn[0] = LOGIN + "";
                          if(check != -1)
                          {
                              toreturn[1] = "OK";
                              toreturn[2] = Integer.toString(check);
                          }
                          else
                          {
                              toreturn[1] ="ERROR";
                          }
                          break;
       }
      return toreturn;
    }
    
    /** This method registers a user in by creating a query and passing it to the handler so it can be executed.
         * It fails if, for example, there's already a user registered with the same username.
         *
         * @param name name passed to the query
         * @param user username passed to the query
         * @param pass password passed to the query
         * @param email email passed to the query
         * @param question security question passed to the query
         * @param answer answer to the security question passed to the query
         * 
         * @return a string[] containing either "OK" or "ERROR" 
         */
    
    public int register(String name, String user, String pass, String email, String question, String answer)
    {
        query = "INSERT INTO userinfo " + "VALUES (DEFAULT,'"+ name +"','"+ user +"','"+ email +"','"+ pass +"','"+ question +"','"+ answer +"')";
        System.out.println("Executing" + query + "On the database");
        
        String[] received;
        
        // variable to check the return of the handler function
        received = DBhandler.run(REGISTER,query,null);
        if(Integer.parseInt(received[0]) != -1)
        {
        query = "INSERT INTO userstats " + "VALUES ("+Integer.parseInt(received[0])+",'"+ 0 +"','"+ 0 +"','"+ 0 +"','"+ 0 +"','"+ 0 +"','"+ 0 +"')";
        received = DBhandler.run(NEWUSERSTATS,query,null);
        }
        return Integer.parseInt(received[0]);
    }   
    
       /** This method creates a query to check if there is an user in the database with name user
        * and password pass and passes it to the handler so it can be executed
        *
        * @param user Username of the user trying to login
        * @param pass Password of the user trying to login ( already encrypted ! )
        * 
        * @return userid on success and -1 on failure
        */
    
    public int login(String user, String pass)  
    {

        // query we are executing on the database. This selects all the users registered on the login table.
        query = "select * from userinfo";
        
        // variable we use to pass to the handler function
        String[] args = new String[20];
        
        // placing the data on args so we can pass it to the handler.
        args[0] = user; 
        args[1] = pass; 
        String[] received;
        
        received = DBhandler.run(LOGIN,query,args);
        return Integer.parseInt(received[0]);
        
    }   
    
    /** This method creates a query to fetch the userinfo of a certain user
     * 
     * @param userid id of the user
     * @return a string[] containing the response from the server
     */
    public String[] get(int userid)
    {
      String query;
      String[] toreturn = new String[4];
     
      query= "SELECT * " + "FROM userinfo" + " WHERE id = " + userid +"";
      String[] received;
      received = DBhandler.run(12,query,null);
      
      toreturn[0] = ""+9;
      if(received[0].equals("-1"))
      {
          toreturn[1] = "ERROR"; 
      }
      else
      {
          System.arraycopy(received, 0, toreturn, 1, 3);
      }
         
      return toreturn;  
    }
    
}

