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

public class Grid extends JPanel {

  public static void main(String[] args) {
        Grid g = new Grid("player",1,1);
        JFrame frame = new JFrame();
        frame.add(g);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        g.setFocusable(true);
        g.requestFocus();
        g.placePlayerShips();
    }
    
    private final Cell squares[][];
    
    private int size;
    private boolean ShipPlaced;
    
    private boolean ListenerEnable;
    static boolean playerReady;
    private Game game;
    private Board gameBoard;
    private Ship gameShip;
    
    public boolean keyToggled;
    public boolean vertical;
    public int gameid, userid, shipid;

    public Grid(String player, int gameid, int userid){
        
        this.setSize(300,300);
        this.setLayout(new GridLayout(10,10));
        
        squares = new Cell[10][10];
        buildButtons(player);
        
        vertical = false;
        size = 5;  
        ShipPlaced = false;
        ListenerEnable = false;
        //gameBoard = new Board();
        
       
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
        if(keyToggled)
        {
            vertical = !vertical;
        }
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
        if(keyToggled)
        {
            vertical = !vertical;
            keyToggled = false;
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
        
        Color paint = Color.YELLOW;
        String result = "hitalive";//gameBoard.shot(xpos,ypos);
         if(!squares[xpos][ypos].clickflag)
        {
            if(result.equals("hitalive"))
            {
                squares[xpos][ypos].setBackground(Color.YELLOW);
            }
            else if(result.equals("hitdead"))
            {
                squares[xpos][ypos].setBackground(paint);
            }
            else
            {
                squares[xpos][ypos].setBackground(paint);
            }
            
        squares[xpos][ypos].clickflag = true;
        }
        if(!squares[xpos][ypos].clickflag)
        {
        squares[xpos][ypos].setBackground(paint);
        squares[xpos][ypos].clickflag = true;
        }
        /*ListenerEnable = false;
          
        */
    }
    
    public void doShots() 
    {
        ListenerEnable = true;
        /*
        String result;
        gameBoard.sendShot();
        */
        
    }
    
    
    public void placePlayerShips()
    {
       int i=0, j=0;
       int[] shipsizes = new int[]{5,4,3,3,2}; 
       int[] shipids = new int[]{5,4,3,1,2};
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
       gameBoard.Sendboard(gameid, userid);
       return;
    }  
    
     public void getOpponentShot()
    {
       
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