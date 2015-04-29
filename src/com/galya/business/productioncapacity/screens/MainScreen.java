package com.galya.business.productioncapacity.screens;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.galya.business.productioncapacity.components.MainTabbedPane;
import com.galya.business.productioncapacity.components.TabsContainer;
import com.galya.business.productioncapacity.components.menu.MainMenuBar;
import com.galya.business.productioncapacity.components.tabs.NewClientTab;
import com.galya.business.productioncapacity.components.tabs.Tab;
import com.galya.business.productioncapacity.components.tabs.TestTab;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class MainScreen implements Screen, TabsContainer {

    private JMenuBar menuBar;
    private MainTabbedPane mainTabbedPane;

    public MainScreen(JFrame frame, ActionListener mainMenuActionListener) {
        menuBar = new MainMenuBar(mainMenuActionListener);
        frame.setJMenuBar(menuBar);

        mainTabbedPane = new MainTabbedPane();

        addNewTab(new TestTab(frame));
        addNewTab(new NewClientTab(frame));

        frame.add(mainTabbedPane, BorderLayout.NORTH);
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
    public void addNewTab(Tab tab) {
        mainTabbedPane.addNewTab(tab);
    }

    public void addTabNewBusiness() {

    }
}
