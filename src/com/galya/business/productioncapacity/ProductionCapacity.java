package com.galya.business.productioncapacity;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;

import com.galya.business.productioncapacity.components.menu.FileMenu;
import com.galya.business.productioncapacity.components.tabs.ClientTab;
import com.galya.business.productioncapacity.components.tabs.SearchTab;
import com.galya.business.productioncapacity.persistence.InfoTableHelper;
import com.galya.business.productioncapacity.persistence.ProductionCapacityDatabaseManager;
import com.galya.business.productioncapacity.screens.LoginScreen;
import com.galya.business.productioncapacity.screens.MainScreen;

public class ProductionCapacity {

    private static final String APP_TITLE = "Подобряване на производствения капацитет в МСП";

    private static final String APP_ICON_PATH = "resources" + System.getProperty("file.separator") + "icon.png";

    private static final int TOOLTIP_INITIAL_DELAY_MILLISECONDS = 500;

    private static final Color LIGHT_BLUE = new Color(15792127);
    
    private static final String CONFIRM_CLOSE_DIALOG_TITLE = "Exit Confirmation";

    private static ProductionCapacity app;

    private JFrame mainFrame;
    private MainScreen mainScreen;
    private LoginScreen loginScreen;

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

    private static void initApp() {
        BasicConfigurator.configure();
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

        mainScreen = new MainScreen(mainFrame, new MainMenuActionListener());
        loginScreen = new LoginScreen(mainFrame);
    }

    public void switchToLoginScreen() {
        mainScreen.setInvisible();
        loginScreen.setVisible();
    }

    public void switchToMainScreen() {
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
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you want to exit?", CONFIRM_CLOSE_DIALOG_TITLE, JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                   System.exit(0);
                }
            }
        };
        mainFrame.addWindowListener(exitListener);
        mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainFrame.setMinimumSize(new Dimension(500, 350));
        ImageIcon appLaunchIcon = new ImageIcon(APP_ICON_PATH);
        mainFrame.setIconImage(appLaunchIcon.getImage());
        mainFrame.setTitle(APP_TITLE);
        mainFrame.setVisible(true);
    }

    private class MainMenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            switch (event.getActionCommand()) {
                case FileMenu.MENU_ITEM_NEW_CLIENT:
                    mainScreen.addNewTab(new ClientTab(mainFrame, null));
                    break;
                case FileMenu.MENU_ITEM_SEARCH:
                    mainScreen.addNewTab(new SearchTab(mainFrame));
                    break;
                case FileMenu.MENU_ITEM_LOGOUT:
                    InfoTableHelper.getInstance().setAutoLogin(false);
                    switchToLoginScreen();
                    break;
                case FileMenu.MENU_ITEM_EXIT:
                    mainFrame.dispose();
                    System.exit(0);
                    break;
            }
        }

    }

}
