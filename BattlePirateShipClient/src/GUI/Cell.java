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
    public static final int CELL_SIZE = 1;
    private static final Color backgroundColor = Color.CYAN;
    private int xPos;
    private int yPos;
    public boolean clickflag;
    public MouseListener Mlistener;

    public Cell (int x, int y) {
        xPos = x;
        yPos = y;
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        clickflag = false;
    }

    
    public int getXPos() 
    {
        return xPos; 
    }

    public int getYPos() 
    {
	return yPos; 
    }
 
     
}
