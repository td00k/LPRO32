/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communications;

/**
 *
 * @author tiagosimoes
 */
public class BattleTest {

    
   public String encode(int type, String Input[], int argnum) {
        int i=0;
        String tosend = type + ""; //Placing type in 1st position
        while (i < argnum) {
        
        tosend = tosend + "#" + Input[i]; //Rest of arguments separeted by #
        i++;
        
    }
        
        
        return tosend;
    }
    
      public String[] decode(String Input, int argnum) {
        int i=0;
        String toreceive = Input;
        String decoded[] = toreceive.split("#"); 
        
        return decoded;
   }
       

 public static void main(String[] args){
     int num = 2; 
     String login1[] = {"name", "gado1234"}; 
     BattleTest pirate = new BattleTest();
     
     System.out.println("ENCODING...\n\n");
     
     String zas = pirate.encode(1,login1,num);
     System.out.println(zas);
     String[] zaszas = pirate.decode(zas,num);
     System.out.println("DECODING...\n\n");
     
     for(int i=0; i<=num; i++){
             
         System.out.println(zaszas[i]);
         }
 }
 
}

    

