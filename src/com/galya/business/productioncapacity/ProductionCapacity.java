package com.galya.business.productioncapacity;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import com.galya.business.productioncapacity.persistence.ProductionCapacityDatabaseManager;
import com.galya.business.productioncapacity.persistence.InfoTableHelper;
import com.galya.business.productioncapacity.screens.LoginScreen;
import com.galya.business.productioncapacity.screens.MainScreen;
import com.galya.business.productioncapacity.screens.Screen;

public class ProductionCapacity {
    
    private static final String APP_TITLE = "Подобряване на производствения капацитет в МСП";

    private static final String APP_ICON_PATH = "resources" + System.getProperty("file.separator") + "icon.png";

    private static final int TOOLTIP_INITIAL_DELAY_MILLISECONDS = 500;

    private static final Color LIGHT_BLUE = new Color(15792127);

    private static ProductionCapacity app;
    
    private JFrame mainFrame;
    private Screen mainScreen;
    private Screen loginScreen;
    
    public static void main(String[] args) {
        String appId = ProductionCapacity.class.getPackage().getName();

        boolean alreadyRunning;
        try {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        } catch (AlreadyLockedException e) {
            alreadyRunning = true;
        }
        
        if (!alreadyRunning) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    initApp();
                }
            });
        }
    }
    
    public static ProductionCapacity getWindow() {
        return app;
    }
    
    private static void initApp(){
        ProductionCapacityDatabaseManager.initDatabase();
        app = new ProductionCapacity();

        if (InfoTableHelper.getInstance().isAutoLoginEnabled()) {
            app.switchToMainScreen();
        } else {
            app.switchToLoginScreen();
        }
    }

    private ProductionCapacity() {
        setUiSettings();
        setMainFrame();

        mainScreen = new MainScreen(mainFrame);
        loginScreen = new LoginScreen(mainFrame);
    }
    
    public void switchToLoginScreen(){
        mainScreen.setInvisible();
        loginScreen.setVisible();
    }

    public void switchToMainScreen(){
        mainScreen.setVisible();
        loginScreen.setInvisible();
    }
    
    public JFrame getFrame() {
        return mainFrame;
    }

    private void setUiSettings() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ToolTipManager.sharedInstance().setInitialDelay(TOOLTIP_INITIAL_DELAY_MILLISECONDS);
        UIManager.put("ToolTip.background", LIGHT_BLUE);
    }

    private void setMainFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainFrame.setMinimumSize(new Dimension(500, 350));
        ImageIcon appLaunchIcon = new ImageIcon(APP_ICON_PATH);
        mainFrame.setIconImage(appLaunchIcon.getImage());
        mainFrame.setTitle(APP_TITLE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setVisible(true);
    }
}
