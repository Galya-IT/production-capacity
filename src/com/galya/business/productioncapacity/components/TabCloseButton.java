package com.galya.business.productioncapacity.components;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TabCloseButton extends JButton {

    private static final long serialVersionUID = -8755789967296837157L;
    
    private static final int CLOSE_BTN_WIDTH = 9;
    private static final int CLOSE_BTN_HEIGHT = 9;

    public TabCloseButton(ActionListener clickListener, int comtainerWidth) {
        super();
        
        setToolTipText("Close");
        setIcon(new ImageIcon("resources/close.png"));
        
        setBorderPainted(false);
        setBorder(null);
        setContentAreaFilled(false);
        
        setPreferredSize(new Dimension(CLOSE_BTN_WIDTH, CLOSE_BTN_HEIGHT));
        setBounds(comtainerWidth - CLOSE_BTN_WIDTH, 0, CLOSE_BTN_WIDTH, CLOSE_BTN_HEIGHT);
        
        addActionListener(clickListener);
    }
}
