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

public class Grid extends JPanel implements ActionListener{

  /*public static void main(String[] args) {
        Grid g = new Grid();
        JFrame frame = new JFrame();
        frame.add(g);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        g.setFocusable(true);
        g.requestFocus();
        g.placePlayerShips();
    }*/

    private Cell squares[][];
    private boolean horizontal;
    private int size;
    private boolean ShipPlaced;
    private boolean keyToggled;

    public Grid(String player){
        this.setSize(300,300);
        this.setLayout(new GridLayout(10,10));
        squares = new Cell[10][10];
        buildButtons();
        horizontal = false;
        size = 5;  
        ShipPlaced = false;
       
    }

    private void buildButtons()
    {
        int i=0,j=0;
        for(i=0;i<10;i++)
        {
            for(j=0;j<10;j++)
            {
                final Cell cell = new Cell(j,i);
                squares[j][i] = cell;
               // squares[i][j].setSize(400,400);
                this.add(squares[j][i]);
                createMListener(squares[j][i]);
            }
        }
    
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("H Key Typed!");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_H) 
                {
                    horizontal = !horizontal;
                    keyToggled = true;
                    System.out.println("H Key pressed!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
    }

    public void placeShip(MouseEvent e, Cell cell) {
        // handle the event, for instance
       // cell.setBackground(Color.blue);
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        
        Color paint = Color.GREEN;
        
        if(!horizontal)
        {
            if((xpos + size-1) > 9)
            {
                return;
            }
            for(int i=0;i<size;i++)
            {
                if(squares[ypos][xpos+i].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {

                   squares[ypos][xpos+i].setBackground(paint);
                   if(paint == Color.GREEN)
                   squares[ypos][xpos+i].clickflag = true;
            }
            if(paint == Color.GREEN)
            {
                 ShipPlaced = true;
                 System.out.println("Ship Placed!");
            }
            return;
        }
        else if(horizontal)
        {
            if((ypos + size-1) > 9)
            {
                return;
            }
             for(int i=0;i<size;i++)
            {
                if(squares[ypos+i][xpos].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }

            }
            for(int i=0;i<size;i++)
            {
                   squares[ypos+i][xpos].setBackground(paint);
                   if(paint == Color.GREEN)
                   squares[ypos+i][xpos].clickflag = true;
            }
            if(paint == Color.GREEN)
            {
                 ShipPlaced = true;
                 System.out.println("Ship Placed!");
            }
            return;
        }   
    }
    
    public void mouseExited(Cell cell) 
   {

        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        Color paint = Color.CYAN;     
        if(keyToggled)
        {
            horizontal = !horizontal;
        }
        if( !horizontal && !(xpos + size-1 > 9) )
        {
            for(int i=0;i<size;i++)
            {
                if(squares[ypos][xpos+i].clickflag == false)
                {
                    squares[ypos][xpos+i].setBackground(paint);
                }
                else
                {
                    squares[ypos][xpos+i].setBackground(Color.GREEN);
                }
            }
        }
        else if(horizontal && !(ypos + size-1 > 9))
        {

            for(int i=0;i<size;i++)
            {
                if(squares[ypos+i][xpos].clickflag == false)
                {
                    squares[ypos+i][xpos].setBackground(paint);
                } 
                else
                {
                    squares[ypos+i][xpos].setBackground(Color.GREEN);
                }
            }
        }
        else
        {
        }    
        if(keyToggled)
        {
            horizontal = !horizontal;
            keyToggled = false;
        }
            return; 
    }    
    
    
    public void mouseEntered(Cell cell) {
    	//We only handle horizontal ship placement right now!
        int xpos = cell.getXPos();
        int ypos = cell.getYPos();
        
        Color paint = Color.GREEN;
        
        if(!horizontal)
        {
            if((xpos + size-1) > 9)
            {
                return;
            }
            for(int i=0;i<size;i++)
            {
                if(squares[ypos][xpos+i].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {
                   squares[ypos][xpos+i].setBackground(paint);
            }
            return;
        }
        if(horizontal)
        {
            if((ypos + size-1) > 9)
            {
                return;
            }
             for(int i=0;i<size;i++)
            {
                if(squares[ypos+i][xpos].clickflag == true)
                {
                   paint = Color.RED;
                   break;
                }
            }
            for(int i=0;i<size;i++)
            {
                   squares[ypos+i][xpos].setBackground(paint);
            }
            return;
        }   
    }
    
    
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    
    public int[][] getPositions()
    {
        int[][] positions = new int [10][10];
    
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(squares[j][i].clickflag == true)
                {
                    positions[j][i] = 1;
                }
                else
                {
                    positions[j][i] = 0;
                }
            }
        }
        return positions;
    }   
    
    public void placePlayerShips()
    {
       int i=0, j=0;
       int[] shipsizes = new int[]{5,4,3,3,2}; 
       for(j=0;j<5;j++)
       {
            size = shipsizes[j];
            System.out.println("Waiting Ship placement!");
            while(!ShipPlaced)   
            {
                System.out.println("");
            }
            System.out.println("Out of while!");
            ShipPlaced = false;
       }
       removeMListener();
       return;
    }  
    
     public void getOpponentShips()
    {
       
    } 
     
     
    public void createMListener(Cell cell)
     {
       cell.Mlistener =  new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        
                    }
                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                            Grid.this.mouseEntered(cell);
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                            Grid.this.mouseExited(cell);
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