package com.galya.business.productioncapacity.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.galya.business.productioncapacity.components.misc.TabsContainer;
import com.galya.business.productioncapacity.components.tabs.Tab;
import com.galya.business.productioncapacity.components.tabs.TabLabelChangeListener;

@SuppressWarnings("serial")
public class MainTabbedPane extends Component implements TabsContainer {
    
    public static final int DEFAULT_INVALID_TAB_POSITION = -1;
    
    private JTabbedPane tabbedPane;

    public MainTabbedPane(JFrame frame, String positionOnTheFrame) {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // for tab labels
        tabbedPane.setVisible(true);
        frame.add(tabbedPane, positionOnTheFrame);
    }

    @Override
    public void addNewTab(Tab tab) {
        String labelText = tab.getLabel();
        ImageIcon icon = tab.getIcon();
        int preferedTabLabelWidth = tab.getPreferedTabLabelWidth();
        boolean isCloseable = tab.isCloseable();
        Component baseComponent = tab.getBaseComponent();

        int nextIndex = tabbedPane.getTabCount();

        tabbedPane.addTab(null, baseComponent);

        if (icon != null) {
            tabbedPane.setIconAt(nextIndex, icon);
        }

        JLabel label = new JLabel(labelText);
        label.setLayout(new BorderLayout());
        label.setPreferredSize(new Dimension(preferedTabLabelWidth, label.getPreferredSize().height));
        label.setHorizontalAlignment(JLabel.LEFT);

        if (isCloseable) {
            JButton btnClose = new TabCloseButton(tab.getOnCloseListener(), preferedTabLabelWidth);
            label.add(btnClose, BorderLayout.EAST);
        }

        tabbedPane.setTabComponentAt(nextIndex, label);
        tabbedPane.setToolTipTextAt(nextIndex, labelText);
        tabbedPane.setSelectedIndex(nextIndex);

        tab.setTabLabelChangeListener(new TabLabelChangeListener() {

            @Override
            public void onTabLabelChange(String newLabelText) {
                label.setText(newLabelText);
            }

        });
    }
    
    public int getTabPositionByLabelText(String tabLabel) {
        int tabPosition = DEFAULT_INVALID_TAB_POSITION;
        
        for (int position = 0; position < tabbedPane.getTabCount(); position++) {
            Component component = tabbedPane.getTabComponentAt(position);
            if (component instanceof JLabel) {
                String labelText = ((JLabel) component).getText();
                if (labelText.equals(tabLabel)) {
                    tabPosition = position;
                    break;
                }
            }
        }
        
        return tabPosition;
    }
    
    public void setSelectedTab(int position) {
        tabbedPane.setSelectedIndex(position);
    }

}
