package com.galya.business.productioncapacity.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.galya.business.productioncapacity.ProductionCapacity;
import com.galya.business.productioncapacity.persistence.InfoTableHelper;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class MainMenu extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = -7909692172084993621L;

    private static final String NEW_CLIENT_MENU_ITEM = "New";
    private static final String SEARCH_MENU_ITEM = "Search";
    private static final String LOGOUT_MENU_ITEM = "Logout";
    private static final String EXIT_MENU_ITEM = "Exit";

    public MainMenu() {
        super();

        JMenu menu = new JMenu("File");

        JMenuItem menuItem = new JMenuItem();
        menuItem.setText(NEW_CLIENT_MENU_ITEM);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(SEARCH_MENU_ITEM);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(LOGOUT_MENU_ITEM);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(EXIT_MENU_ITEM);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem.setPreferredSize(new Dimension(200, menuItem.getPreferredSize().height));

        add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case NEW_CLIENT_MENU_ITEM:
                
                break;
            case SEARCH_MENU_ITEM:
                break;
            case LOGOUT_MENU_ITEM:
                InfoTableHelper.getInstance().setAutoLogin(false);
                ProductionCapacity.getWindow().switchToLoginScreen();
                break;
            case EXIT_MENU_ITEM:
                GuiUtils.closeApp(MainMenu.this);
                break;
        }
    }
}
