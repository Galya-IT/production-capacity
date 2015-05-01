package com.galya.business.productioncapacity.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.galya.business.productioncapacity.components.misc.TabsContainer;
import com.galya.business.productioncapacity.components.tabs.Tab;

public class MainTabbedPane extends JTabbedPane implements TabsContainer {

    private static final long serialVersionUID = -2639553103973198727L;

    public MainTabbedPane() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // for tab labels
    }

    public void addNewTab(Tab tab) {
        String labelText = tab.getLabel();
        ImageIcon icon = tab.getIcon();
        int preferedTabLabelWidth = tab.getPreferedTabLabelWidth();
        boolean isCloseable = tab.isCloseable();
        Component baseComponent = tab.getBaseComponent();

        int nextIndex = getTabCount();

        addTab(null, baseComponent);

        if (icon != null) {
            setIconAt(nextIndex, icon);
        }

        JLabel label = new JLabel(labelText);
        label.setLayout(new BorderLayout());
        label.setPreferredSize(new Dimension(preferedTabLabelWidth, label.getPreferredSize().height));
        label.setHorizontalAlignment(JLabel.LEFT);

        if (isCloseable) {
            JButton btnClose = new TabCloseButton(tab.getOnCloseListener(), preferedTabLabelWidth);
            label.add(btnClose, BorderLayout.EAST);
        }

        setTabComponentAt(nextIndex, label);
        setToolTipTextAt(nextIndex, labelText);
        setSelectedIndex(nextIndex);
    }

}
