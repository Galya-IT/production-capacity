package com.galya.business.productioncapacity.components.tabs;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.galya.business.productioncapacity.GridBagLayoutBuffer;
import com.galya.business.productioncapacity.utils.ComponentFactory;

public class SearchTab extends Tab {

    private static final String LABEL = "Search";
    private static final int LABEL_WIDTH = 100;
    
    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = true;

    public SearchTab(JFrame mainFrame) {
        super(mainFrame, LABEL, LABEL_WIDTH, ICON, IS_CLOSEABLE);

        JPanel panel = new JPanel(false);
        panel.setLayout(new GridBagLayout());
        
        JLabel lastAddedSectionLabel = new JLabel("Критерии за търсене:");
        
        JLabel nameCriteriaLabel = ComponentFactory.generateLabel("Име");
        JTextField nameCriteriaField = new JTextField();

        JLabel bulstatCriteriaLabel = ComponentFactory.generateLabel("Булстат");
        JTextField bulstatCriteriaField = new JTextField();
        
        JTable searchResultsTable = new JTable();
        
        setBaseComponent(panel);
        
        GridBagLayoutBuffer layoutBuffer = new GridBagLayoutBuffer();
        layoutBuffer.add(lastAddedSectionLabel, 0, 2, true);
        layoutBuffer.add(nameCriteriaLabel, 0, 1, true);
        layoutBuffer.add(nameCriteriaField, 1, 1, true);
        layoutBuffer.add(bulstatCriteriaLabel, 0, 1, true);
        layoutBuffer.add(bulstatCriteriaField, 1, 1, true);
        layoutBuffer.add(new JLabel(), 0, 2, true, 1, 1);
        layoutBuffer.execute(panel);
    }
}
