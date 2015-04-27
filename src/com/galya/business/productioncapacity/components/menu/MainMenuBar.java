package com.galya.business.productioncapacity.components.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

public class MainMenuBar extends JMenuBar {

    private static final long serialVersionUID = -7909692172084993621L;

    public MainMenuBar(ActionListener actionListener) {
        super();

        add(new FileMenu(actionListener));
    }
}
