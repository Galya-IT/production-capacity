package com.galya.business.productioncapacity.components.tabs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public abstract class Tab {

    private static final String CONFIRM_CLOSE_DIALOG_TITLE = "Close Tab";

    private String label;
    private ImageIcon icon;
    private boolean isCloseable;
    private int preferedTabLabelWidth;
    private Component baseComponent;
    private String closeConfirmDialogMsg;
    private Dimension preferedTabDimension;

    private ActionListener onCloseListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            onClose(event);
        }
    };

    protected Tab() {
    }

    protected Tab(JFrame parentFrame, String label, int preferedTabLabelWidth, ImageIcon icon, boolean isCloseable) {
        this.preferedTabDimension = parentFrame.getSize();
        this.label = label;
        this.preferedTabLabelWidth = preferedTabLabelWidth;
        this.icon = icon;
        this.isCloseable = isCloseable;
        this.closeConfirmDialogMsg = "<html><body style=\"width: 200px\">Any unsaved changes will be lost. Are you sure that you want to close the tab "
                + label + "?";
    }

    public final String getLabel() {
        return label;
    }

    public final ImageIcon getIcon() {
        return this.icon;
    }

    public final boolean isCloseable() {
        return isCloseable;
    }

    public final int getPreferedTabLabelWidth() {
        return preferedTabLabelWidth;
    }

    public final Component getBaseComponent() {
        return baseComponent;
    }

    public final ActionListener getOnCloseListener() {
        return onCloseListener;
    }

    protected final void setLabel(String label) {
        this.label = label;
    }

    protected final void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    protected final void setCloseable(boolean isCloseable) {
        this.isCloseable = isCloseable;
    }

    protected final void setPreferedTabLabelWidth(int tabLabelWidth) {
        this.preferedTabLabelWidth = tabLabelWidth;
    }

    protected final void setBaseComponent(Component component) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        int preferedWidth = preferedTabDimension.width;
        int preferedHeight = (int)(0.9 * preferedTabDimension.height);
        scrollPane.setPreferredSize(new Dimension(preferedWidth, preferedHeight));
        scrollPane.setBorder(null);
        scrollPane.setViewportView(component);
        this.baseComponent = scrollPane;
    }

    protected final void setCloseConfirmDialogMsg(String msg) {
        this.closeConfirmDialogMsg = msg;
    }

    protected void onClose(ActionEvent event) {
        int dialogResult = JOptionPane.showConfirmDialog(null, closeConfirmDialogMsg, CONFIRM_CLOSE_DIALOG_TITLE,
                JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            baseComponent.getParent().remove(baseComponent);
        }
    }

}
