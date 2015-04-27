package com.galya.business.productioncapacity.components.tabs;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestTab extends Tab {

    private static final String LABEL = "Tab 10 fdgdfbdfb df ergerg rgherhrthrtfhb rthrthrth";
    private static final int LABEL_WIDTH = 100;
    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = false;

    public TestTab() {
        super(LABEL, LABEL_WIDTH, ICON, IS_CLOSEABLE);

        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));

        JTextField textFieldComponent = new JTextField();
        textFieldComponent.setColumns(3);

        panel.add(textFieldComponent);

        setBaseComponent(panel);
    }

}
