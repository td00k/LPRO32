/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Vitor
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cell extends JPanel {
    private static final int CELL_SIZE = 1;
    private int xPos;
    private int yPos;
    public boolean clickflag;
    public MouseListener Mlistener;
    private int content;
    private Color defaultColor;
    private Color contentcolor;

    public Cell (int x, int y, Color background) {
        xPos = x;
        yPos = y;
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
        setBackground(background);
        defaultColor = background;
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        clickflag = false;
        contentcolor = background;
        content = 0; //water
    }

    
    public int getXPos() 
    {
        return xPos; 
    }

    public int getYPos() 
    {
	return yPos; 
    }
 
    public void setContent(int content, boolean dead)
    {
        this.content = content;
        if(!dead)
        {
            switch(content)
            {
                case 0:
                        // water 
                        contentcolor = Color.BLUE;
                        setBackground(contentcolor);
                        break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                        //hit on a ship
                        contentcolor = Color.YELLOW;
                        setBackground(contentcolor);
                default:
                        setBackground(defaultColor);
                        break;        
            }
        }
        else
        {
            //ship dead
            contentcolor = Color.RED;
            setBackground(contentcolor);
        }
    }
    
    public int getContent()
    {  
        return this.content;
    }
    
    public void setColor()
    {
        setBackground(contentcolor);
    }
            
     
}
