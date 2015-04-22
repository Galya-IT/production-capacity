package com.galya.business.productioncapacity.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.galya.business.productioncapacity.screens.TabsContainer;

public class MainTabbedPane extends JTabbedPane implements TabsContainer {

    private static final long serialVersionUID = -2639553103973198727L;

    public MainTabbedPane() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public JComponent addNewTab(String labelText, ImageIcon icon, int preferredWidth,
            Component component, boolean isCloseable) {
        int nextIndex = getTabCount();

        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));

        addTab(null, panel);
        
        if (icon != null) {
            setIconAt(nextIndex, icon);
        }

        JLabel label = new JLabel(labelText);
        label.setLayout(new BorderLayout());
        label.setPreferredSize(new Dimension(preferredWidth, label.getPreferredSize().height));
        label.setHorizontalAlignment(JLabel.LEFT);

        if (isCloseable) {
            ActionListener clickListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    remove(panel);
                }
            };
            JButton btnClose = new TabCloseButton(clickListener, preferredWidth);
            label.add(btnClose, BorderLayout.EAST);
        }

        setTabComponentAt(nextIndex, label);
        setToolTipTextAt(nextIndex, labelText);

        if (component != null) {
            panel.add(component);
        }
        return panel;
    }

}
