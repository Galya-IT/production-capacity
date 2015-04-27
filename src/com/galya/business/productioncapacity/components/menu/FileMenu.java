package com.galya.business.productioncapacity.components.menu;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

    private static final long serialVersionUID = -65378164659350020L;

    public static final String MENU_TITLE = "File";

    public static final String MENU_ITEM_NEW_CLIENT = "New";
    public static final String MENU_ITEM_SEARCH = "Search";
    public static final String MENU_ITEM_LOGOUT = "Logout";
    public static final String MENU_ITEM_EXIT = "Exit";

    public FileMenu(ActionListener actionListener) {
        super(MENU_TITLE);

        JMenuItem menuItem = new JMenuItem();
        menuItem.setText(MENU_ITEM_NEW_CLIENT);
        menuItem.addActionListener(actionListener);
        add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(MENU_ITEM_SEARCH);
        menuItem.addActionListener(actionListener);
        add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(MENU_ITEM_LOGOUT);
        menuItem.addActionListener(actionListener);
        add(menuItem);

        menuItem = new JMenuItem();
        menuItem.setText(MENU_ITEM_EXIT);
        menuItem.addActionListener(actionListener);
        add(menuItem);

        menuItem.setPreferredSize(new Dimension(200, menuItem.getPreferredSize().height));
    }

}
