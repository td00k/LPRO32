/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import BusinessLogic.Board;
import BusinessLogic.Game;
import BusinessLogic.Ship;
import Communications.SocketClient;

public class Grid extends JPanel {

  /*public static void main(String[] args) {
        Grid g = new Grid("player",1,1,null);
        JFrame frame = new JFrame();
        frame.add(g);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        g.setFocusable(true);
        g.requestFocus();
        g.placePlayerShips();
    }
*/    
    private final Cell squares[][];
    
    private int size;
    private boolean ShipPlaced;
    
    private boolean ListenerEnable;
    static boolean playerReady;
    public boolean vertical;
    static boolean GameEnd;
    public boolean Winner;
    private boolean myturn;
    private boolean clicked = false;
    
    private final SocketClient client;
    private Game game;
    private Board gameBoard;
    private Ship[] Ships;
    
    public int gameid, userid, shipid;
    public int[] shipsizes;
    public int[] shipids;
    

    public Grid(String player, int gameid, int userid, SocketClient client, int startingPlayer){
        
        this.setSize(300,300);
        this.setLayout(new GridLayout(10,10));
        this.client = client;
        
        if(startingPlayer == userid)
        {
            myturn = true;
        }
        else
        {
            myturn = false;
        }
        
        squares = new Cell[10][10];
        buildButtons(player);
        
        this.userid = userid;
        this.gameid = gameid;
        vertical = false;
        size = 5;  
        ShipPlaced = false;
        ListenerEnable = false;
        gameBoard = new Board(client);
        Ships = new Ship[5];
        shipsizes = new int[]{5,4,3,3,2}; 
        shipids = new int[]{5,4,3,1,2};
        
        for(int i=4;i>-1;i--)
        {
            Ships[i] = new Ship(shipsizes[i]);
        }

    }

    private void buildButtons(String player)
    {
        int i=0,j=0;
        for(i=0;i<10;i++)
        {
            for(j=0;j<10;j++)
            {
                squares[i][j] = new Cell(i,j);
                if (player.equals("enemy"))
                {
                    squares[i][j].setBackground(Color.GRAY);
                }
                this.add(squares[i][j]);
                createMListener(squares[i][j],player);
            }
        }
    }

    public void placeShip(MouseEvent e, Cell cell) {
        // handle the event, for instance
       // cell.setBackground(Color.blue);
       
    
       
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        
        Color paint = Color.GREEN;
        
        if(!vertical)
        {
            if((ypos + size-1) > 9)
            {
                return;
            }
            for(int i=0;i<size;i++)
            {
                if(squares[xpos][ypos+i].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {

                   squares[xpos][ypos+i].setBackground(paint);
                   if(paint == Color.GREEN)
                   {
                        squares[xpos][ypos+i].clickflag = true;
                        gameBoard.positions[xpos][ypos+i] = shipid; 
                   }
            }
            if(paint == Color.GREEN)
            {
                 ShipPlaced = true;
                 System.out.println("Ship Placed!");
            }
            return;
        }
        else if(vertical)
        {
            if((xpos + size-1) > 9)
            {
                return;
            }
             for(int i=0;i<size;i++)
            {
                if(squares[xpos+i][ypos].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }

            }
            for(int i=0;i<size;i++)
            {
                   squares[xpos+i][ypos].setBackground(paint);
                   if(paint == Color.GREEN)
                   {
                        squares[xpos+i][ypos].clickflag = true;
                        gameBoard.positions[xpos+i][ypos] = shipid;
                   }
            }
            if(paint == Color.GREEN)
            {
                 ShipPlaced = true;
                 System.out.println("Ship Placed!");
            }
            return;
        }  
    }
    
    public void mouseExitedPlayerGrid(Cell cell) 
   {

        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        Color paint = Color.CYAN;     
        if( !vertical && !(ypos + size-1 > 9) )
        {
            for(int i=0;i<size;i++)
            {
                if(squares[xpos][ypos+i].clickflag == false)
                {
                    squares[xpos][ypos+i].setBackground(paint);
                }
                else
                {
                    squares[xpos][ypos+i].setBackground(Color.GREEN);
                }
            }
        }
        else if(vertical && !(xpos + size-1 > 9))
        {

            for(int i=0;i<size;i++)
            {
                if(squares[xpos+i][ypos].clickflag == false)
                {
                    squares[xpos+i][ypos].setBackground(paint);
                } 
                else
                {
                    squares[xpos+i][ypos].setBackground(Color.GREEN);
                }
            }
        }
        else
        {
        }    
            return; 
    }    
    
    
    public void mouseEnteredPlayerGrid(Cell cell) {
    	//We only handle vertical ship placement right now!
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        
        Color paint = Color.GREEN;
        
        if(!vertical)
        {
            if((ypos + size-1) > 9)
            {
                return;
            }
            for(int i=0;i<size;i++)
            {
                if(squares[xpos][ypos+i].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {
                   squares[xpos][ypos+i].setBackground(paint);
            }
            return;
        }
        if(vertical)
        {
            if((xpos + size-1) > 9)
            {
                return;
            }
             for(int i=0;i<size;i++)
            {
                if(squares[xpos+i][ypos].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {
                   squares[xpos+i][ypos].setBackground(paint);
            }
            return;
        }   
    }
    
    public void mouseEnteredEnemyGrid(Cell cell) 
    {
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        
        if(!squares[xpos][ypos].clickflag)
        squares[xpos][ypos].setBackground(Color.YELLOW);
        else
        squares[xpos][ypos].setBackground(Color.RED);
    }
    
    public void mouseExitedEnemyGrid(Cell cell) 
    {
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();

        if(!squares[xpos][ypos].clickflag)
        squares[xpos][ypos].setBackground(Color.GRAY);
        else
        squares[xpos][ypos].setBackground(Color.YELLOW);
    }
    
    public void sendShot(Cell cell) 
    {
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        int[] retpos = new int[10];
        
        Color paint = Color.YELLOW;
        
        if(!squares[xpos][ypos].clickflag)
        {
            String result = gameBoard.shot(userid,xpos,ypos);
            switch(Integer.parseInt(result))
            {
                    case 0:
                            // water hit
                            squares[xpos][ypos].setBackground(Color.CYAN);
                            break;
                    case 1:
                            // hit one of the 3 health ships
                            Ships[1].insertPos(xpos, ypos);
                            if(Ships[1].getHealth() == 0)
                            {
                               retpos = Ships[1].getPositions();
                               for(int i=0;i<6;i=i+2)
                               {
                                   squares[retpos[i]][retpos[i+1]].setBackground(Color.RED);
                               }

                            }
                            else
                            {
                               squares[xpos][ypos].setBackground(Color.YELLOW); 
                            }
                            break;
                    case 2:
                            // hit the 2 health ship
                              Ships[0].insertPos(xpos, ypos);
                            if(Ships[0].getHealth() == 0)
                            {
                               retpos = Ships[0].getPositions();
                               for(int i=0;i<4;i=i+2)
                               {
                                   squares[retpos[i]][retpos[i+1]].setBackground(Color.RED);
                               }

                            }
                            else
                            {
                               squares[xpos][ypos].setBackground(Color.YELLOW); 
                            }
                            break;
                    case 3:
                            // hit the other 3 health ship
                              Ships[2].insertPos(xpos, ypos);
                            if(Ships[2].getHealth() == 0)
                            {
                               retpos = Ships[2].getPositions();
                               for(int i=0;i<6;i=i+2)
                               {
                                   squares[retpos[i]][retpos[i+1]].setBackground(Color.RED);
                               }

                            }
                            else
                            {
                               squares[xpos][ypos].setBackground(Color.YELLOW); 
                            }
                            break;
                    case 4:
                            // hit the 4 health ship
                              Ships[3].insertPos(xpos, ypos);
                            if(Ships[3].getHealth() == 0)
                            {
                               retpos = Ships[3].getPositions();
                               for(int i=0;i<8;i=i+2)
                               {
                                   squares[retpos[i]][retpos[i+1]].setBackground(Color.RED);
                               }
                            }
                            else
                            {
                               squares[xpos][ypos].setBackground(Color.YELLOW); 
                            }
                            break;
                    case 5:
                            // hit the 5 health ship
                            Ships[4].insertPos(xpos, ypos);
                            if(Ships[4].getHealth() == 0)
                            {
                               retpos = Ships[4].getPositions();
                               for(int i=0;i<10;i=i+2)
                               {
                                   squares[retpos[i]][retpos[i+1]].setBackground(Color.RED);
                               }

                            }
                            else
                            {
                               squares[xpos][ypos].setBackground(Color.YELLOW); 
                            }
                            break;
                     case 6:
                            ListenerEnable = false;
                            GameEnd = true;
                            Winner = true;
                            break;               
  		}
        clicked = true;
        }
    }  
        // wait for other player ListenerEnable = true;
          
        
    public void receiveShots() 
    {
            
    }
    
    public void doShots() 
    {
        while(true)
        {
            if(GameEnd == true)
            {
                return;
            }
            if(myturn == true)
            { 
                ListenerEnable = true;
                while(!clicked)
                {      
                }
                myturn = false;
                clicked = false;   
            }
        }
    }
    
    
    public void placePlayerShips()
    {
       int i=0, j=0;
     
       for(j=0;j<5;j++)
       {
            size = shipsizes[j];
            shipid = shipids[j];
            System.out.println("Waiting Ship placement!");
            while(ShipPlaced == false)   
            {
                try {
                Thread.sleep(10); // for 100 FPS
                } 
                catch (InterruptedException ignore) 
                {
                }
            }
            System.out.println("Out of while!");
            ShipPlaced = false;
       }
       removeMListener();
       return;
    }  
    
    public void sendBoard()
    {
         gameBoard.Sendboard(gameid, userid);
         return;
    }
    
     
    public void createMListener(Cell cell,String player)
    {
       if(player.equals("player"))
       {
         cell.Mlistener =  new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                            Grid.this.mouseEnteredPlayerGrid(cell);
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                            Grid.this.mouseExitedPlayerGrid(cell);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) 
                    {
                        Grid.this.placeShip(e,cell);
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                    }
                };
                cell.addMouseListener(cell.Mlistener);
        }
       else
       {
           cell.Mlistener =  new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        if(ListenerEnable)   
                        Grid.this.mouseEnteredEnemyGrid(cell);
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                            if(ListenerEnable)
                            Grid.this.mouseExitedEnemyGrid(cell);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) 
                    {
                        if(ListenerEnable)
                        Grid.this.sendShot(cell);
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                    }
                };
                cell.addMouseListener(cell.Mlistener);
       }
    }
     
     private void removeMListener()
     {
         int i = 0, j = 0;
          for(i=0;i<10;i++)
          {
            for(j=0;j<10;j++)
            {
                squares[j][i].removeMouseListener(squares[j][i].Mlistener);
            }
          }
     }
}