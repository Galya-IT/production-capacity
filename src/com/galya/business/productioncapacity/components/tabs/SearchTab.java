package com.galya.business.productioncapacity.components.tabs;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchTab extends Tab {

    private static final String LABEL = "Search";
    private static final int LABEL_WIDTH = 100;
    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = true;

    public SearchTab(JFrame mainFrame) {
        super(mainFrame, LABEL, LABEL_WIDTH, ICON, IS_CLOSEABLE);

        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));

        JTextField textFieldComponent = new JTextField();
        textFieldComponent.setColumns(3);

        panel.add(textFieldComponent);

        setBaseComponent(panel);
    }
}
