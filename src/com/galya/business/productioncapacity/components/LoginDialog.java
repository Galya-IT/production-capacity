package com.galya.business.productioncapacity.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.galya.business.productioncapacity.ProductionCapacity;
import com.galya.business.productioncapacity.persistence.InfoTableHelper;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class LoginDialog extends JDialog {

    private static final long serialVersionUID = -1414634241243336634L;

    private static final String TITLE = "Login";

    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 250;

    private static final String DEFAULT_FONT_TYPE = Font.SANS_SERIF;
    private static final Font DIALOG_DEFAULT_FONT = new Font(DEFAULT_FONT_TYPE, Font.PLAIN, 14);
    private static final Color VIOLET = new Color(14329330);
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox autoLoginCheckBox;

    public LoginDialog(JFrame owner) {
        super(owner);

        setSettings();
        setLocation();
        setContent(owner);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void setVisible(boolean makeVisible){
        if (makeVisible) {
            setLocation();
        }
        
        super.setVisible(makeVisible);
    }

    private void setSettings() {
        setTitle(TITLE);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setUndecorated(false);

        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setResizable(false);

        setFocusable(true);
        setAutoRequestFocus(true);

        setModalityType(ModalityType.DOCUMENT_MODAL);
    }

    private void setLocation() {
        Dimension frameDimension = GuiUtils.getFrameDimension(this);
        Point framePosition = GuiUtils.getFramePosition(this);

        int x = (int) (framePosition.getX() + (frameDimension.width - DIALOG_WIDTH) / 2);
        int y = (int) (framePosition.getY() + (frameDimension.height - DIALOG_HEIGHT) / 2 - (0.1 * frameDimension.height));

        setLocation(x, y);
    }

    private void setContent(JFrame frame) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 1, 1, 1, 1 }; // 4 cols
        gridBagLayout.columnWeights = new double[] { 1, 1, 1, 1 };
        gridBagLayout.rowHeights = new int[] { 1, 1, 1, 1, 1 }; // 5 rows
        gridBagLayout.rowWeights = new double[] { 1, 1, 1, 1, 1 };

        JPanel container = new JPanel();
        container.setLayout(gridBagLayout);

        JTextPane labelPane = new JTextPane();
        labelPane.setText("Login");
        labelPane.setFont(new Font(DEFAULT_FONT_TYPE, Font.BOLD, 25));
        labelPane.setBackground(VIOLET);
        labelPane.setEditable(false);

        JLabel usernameLabel = generateLabel("Username:");

        usernameField = new JTextField();
        usernameField.setMargin(new Insets(5, 5, 5, 5));
        usernameField.setFont(DIALOG_DEFAULT_FONT);

        JLabel passwordLabel = generateLabel("Password:");

        passwordField = new JPasswordField();
        passwordField.setMargin(new Insets(5, 5, 5, 5));
        passwordField.setFont(DIALOG_DEFAULT_FONT);

        autoLoginCheckBox = new JCheckBox();
        autoLoginCheckBox.setText("Auto login until logged out.");

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        buttonCancel.setFont(DIALOG_DEFAULT_FONT);

        JButton buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String typedUsername = usernameField.getText().trim();
                String typedPassword = new String(passwordField.getPassword()).trim();

                boolean isValidCredentials = InfoTableHelper.getInstance().areValidCredentials(typedUsername, typedPassword);

                if (isValidCredentials) {
                    if (autoLoginCheckBox.isSelected()) {
                        InfoTableHelper.getInstance().setAutoLogin(true);
                    }
                    ProductionCapacity.getWindow().switchToMainScreen();
                    clearInputFields();
                } else {
                    JOptionPane.showMessageDialog(frame, "Your username or password is incorrect.", "Login error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonLogin.setFont(DIALOG_DEFAULT_FONT);

        /*
         * constraints.ipady = 0                            - component height
         * constraints.insets = new Insets(0, 0, 5, 5)      - padding
         * constraints.fill = GridBagConstraints.HORIZONTAL - fill horizontal
         * constraints.gridx = 0                            - starts with 1st cell horizontally
         * constraints.gridy = 1                            - starts with 2st cell vertically
         * constraints.gridwidth = 2                        - width 2 cols
         */
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        container.add(labelPane, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        container.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        container.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        container.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        container.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        container.add(autoLoginCheckBox, constraints);

        constraints.insets = new Insets(0, 5, 5, 5);
        constraints.fill = GridBagConstraints.NONE;

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        container.add(buttonCancel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 2;
        constraints.anchor = GridBagConstraints.WEST;
        container.add(buttonLogin, constraints);

        add(container);
    }
    
    private JLabel generateLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setBorder(null);
        label.setHorizontalAlignment(JTextField.RIGHT);
        label.setFont(DIALOG_DEFAULT_FONT);
        return label;
    }

    private void clearInputFields() {
        usernameField.setText("");
        passwordField.setText("");
        autoLoginCheckBox.setSelected(false);
    }
}
