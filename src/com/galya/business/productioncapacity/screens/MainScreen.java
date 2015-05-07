package com.galya.business.productioncapacity.screens;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.galya.business.productioncapacity.components.MainTabbedPane;
import com.galya.business.productioncapacity.components.menu.MainMenuBar;
import com.galya.business.productioncapacity.components.misc.OpenClientTabListener;
import com.galya.business.productioncapacity.components.misc.TabsContainer;
import com.galya.business.productioncapacity.components.tabs.ClientTab;
import com.galya.business.productioncapacity.components.tabs.MainTab;
import com.galya.business.productioncapacity.components.tabs.Tab;
import com.galya.business.productioncapacity.model.Client;
import com.galya.business.productioncapacity.utils.GuiUtils;

/**
 * TabsContainer implemented to provide public interface for adding new tabs.
 */
public class MainScreen implements Screen, TabsContainer, OpenClientTabListener {

    private JMenuBar menuBar;
    private MainTabbedPane mainTabbedPane;

    public MainScreen(JFrame frame, ActionListener mainMenuActionListener) {
        menuBar = new MainMenuBar(mainMenuActionListener);
        frame.setJMenuBar(menuBar);

        mainTabbedPane = new MainTabbedPane(frame, BorderLayout.NORTH);

        addNewTab(new MainTab(frame));
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
        if (tab instanceof MainTab) {
            ((MainTab) tab).setOpenClientTabListener(this);
        }
        
        mainTabbedPane.addNewTab(tab);
    }
    
    @Override
    public void onOpenClientTabEvent(Client client) {
        JFrame parentFrame = GuiUtils.getParentFrame(menuBar);
        int tabPosition = mainTabbedPane.getTabPositionByLabelText(client.getName());
        
        if (tabPosition == MainTabbedPane.DEFAULT_INVALID_TAB_POSITION) {
            mainTabbedPane.addNewTab(new ClientTab(parentFrame, client));
        } else {
            mainTabbedPane.setSelectedTab(tabPosition);
        }
    }
}
