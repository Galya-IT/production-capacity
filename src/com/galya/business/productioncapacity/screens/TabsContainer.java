package com.galya.business.productioncapacity.screens;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public interface TabsContainer {
    
    JComponent addNewTab(String labelText, ImageIcon icon, int preferredWidth, Component component, boolean isCloseable);
    
}
