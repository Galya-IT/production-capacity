package com.galya.business.productioncapacity.screens;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import com.galya.business.productioncapacity.components.MainMenu;
import com.galya.business.productioncapacity.components.MainTabbedPane;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class MainScreen implements Screen, TabsContainer {

    private JMenuBar menuBar;
    private MainTabbedPane mainTabbedPane;

    public MainScreen(JFrame frame) {
        menuBar = new MainMenu();
        frame.setJMenuBar(menuBar);

        mainTabbedPane = new MainTabbedPane();

        // To remove: START
        
        final int TAB_LABEL_WIDTH = 100;

        JTextField textFieldComponent = new JTextField();
        textFieldComponent.setColumns(3);

        addNewTab("Tab 10 fdgdfbdfb df ergerg rgherhrthrtfhb rthrthrth", null, TAB_LABEL_WIDTH, textFieldComponent,
                false);
        addNewTab("Tab 2", null, TAB_LABEL_WIDTH, null, true);
        addNewTab("Tab 3", null, TAB_LABEL_WIDTH, null, true);
        addNewTab("Tab 4", null, TAB_LABEL_WIDTH, null, true);
        
        // To remove: END
        
        frame.add(mainTabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void setInvisible() {
        menuBar.setVisible(false);
        mainTabbedPane.setVisible(false);
    }

    @Override
    public void setVisible() {
        menuBar.setVisible(true);
        mainTabbedPane.setVisible(true);
    }

    @Override
    public void destroy() {
        JFrame frame = GuiUtils.getParentFrame(menuBar);
        frame.remove(menuBar);
        frame.remove(mainTabbedPane);
    }

    @Override
    public JComponent addNewTab(String labelText, ImageIcon icon, int preferredWidth, Component component,
            boolean isCloseable) {
        return mainTabbedPane.addNewTab(labelText, icon, preferredWidth, component, isCloseable);
    }
    
    public void addTabNewBusiness() {
        
    }
}
