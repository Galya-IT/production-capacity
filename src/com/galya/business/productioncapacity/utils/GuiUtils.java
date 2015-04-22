package com.galya.business.productioncapacity.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GuiUtils {

    public static JFrame getParentFrame(Component component) {
        return (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, component);

        /*        Container parent = component.getParent();
                while (!(parent instanceof JFrame)) {
                    parent = parent.getParent();
                }
                return ((JFrame) parent);*/
    }

    public static void closeApp(Component component) {
        getParentFrame(component).dispose();
        System.exit(0);
    }

    public static Dimension getScreenDimension() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        return screenDimension;
    }

    public static Dimension getFrameDimension(Component component) {
        JFrame frame = getParentFrame(component);
        Rectangle bounds = frame.getBounds();
        Dimension frameDimension = bounds.getSize();
        return frameDimension;
    }

    public static Point getFramePosition(Component component) {
        JFrame frame = getParentFrame(component);
        Point framePosition = frame.getLocation();
        return framePosition;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        URL imgURL = GuiUtils.class.getResource(path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
