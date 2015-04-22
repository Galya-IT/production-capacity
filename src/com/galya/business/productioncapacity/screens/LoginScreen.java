package com.galya.business.productioncapacity.screens;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.galya.business.productioncapacity.components.LoginDialog;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class LoginScreen implements Screen {

    private JDialog loginDialog;

    public LoginScreen(JFrame frame) {
        loginDialog = new LoginDialog(frame);
    }

    @Override
    public void setInvisible() {
        loginDialog.setVisible(false);
    }

    @Override
    public void setVisible() {
        loginDialog.setVisible(true);
    }

    @Override
    public void destroy() {
        JFrame frame = GuiUtils.getParentFrame(loginDialog);
        frame.remove(loginDialog);
    }
}
