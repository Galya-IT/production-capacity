package com.galya.business.productioncapacity.components.tabs;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.galya.business.productioncapacity.GridBagLayoutBuffer;
import com.galya.business.productioncapacity.components.ClientsTable;
import com.galya.business.productioncapacity.components.misc.ClientTableRowDoubleClickListener;
import com.galya.business.productioncapacity.components.misc.OpenClientTabListener;
import com.galya.business.productioncapacity.model.Client;
import com.galya.business.productioncapacity.persistence.ClientTableHelper;

public class MainTab extends Tab implements ClientTableRowDoubleClickListener {

    private static final String LABEL = "Main";
    private static final int LABEL_WIDTH = 100;

    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = false;

    private static final String LAST_ADDED_CLIENTS_TABLE_NAME = "LastAddedClientsTableName";
    private static final int MAX_LAST_ADDED_CLIENTS_COUNT = 5;
    private static final String[] LAST_ADDED_TABLE_COLUMN_NAMES = new String[] { "Име", "Булстат" };

    private ClientsTable lastAddedClientsTable;
    private JLabel noClientsAddedLabel;
    
    private OpenClientTabListener openClientTabListener;

    public MainTab(JFrame parentFrame) {
        super(parentFrame, LABEL, LABEL_WIDTH, ICON, IS_CLOSEABLE);

        JPanel panel = new JPanel(false);
        panel.setLayout(new GridBagLayout());

        JLabel lastAddedSectionLabel = new JLabel("Последно добавени клиенти:");
        noClientsAddedLabel = new JLabel("Няма добавени клиенти.");
        
        List<Client> lastAddedClients = getLastAddedClients();
        lastAddedClientsTable = new ClientsTable(LAST_ADDED_CLIENTS_TABLE_NAME, LAST_ADDED_TABLE_COLUMN_NAMES, lastAddedClients, this);
        toogleLastAddedTableNoDataLabel(lastAddedClients);
        
        // TODO: lastUpdatedClientsTable = new ClientsTable();

        JButton refreshButton = new JButton(new ImageIcon(((new ImageIcon("resources"
                + System.getProperty("file.separator") + "refresh.png").getImage().getScaledInstance(32, 32,
                Image.SCALE_SMOOTH)))));
        refreshButton.setToolTipText("Опресни");
        refreshButton.setBorder(null);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastAddedClientsTable.setData(getLastAddedClients());
            }
        });

        setBaseComponent(panel);

        GridBagLayoutBuffer layoutBuffer = new GridBagLayoutBuffer();

        layoutBuffer.add(lastAddedSectionLabel, 0, 2, true);
        layoutBuffer.add(noClientsAddedLabel, 0, 2, true);
        layoutBuffer.add(lastAddedClientsTable.getTableHeader(), 0, 2, true, 0);
        layoutBuffer.add(lastAddedClientsTable.getTableBody(), 0, 2, true, 0);
        layoutBuffer.add(refreshButton, 0, 2, true);
        layoutBuffer.add(new JLabel(), 0, 2, true, 1, 1);

        layoutBuffer.execute(panel);
    }
    
    @Override
    public void onTableRowDoubleClicked(String tableName, Client client) {
        if (LAST_ADDED_CLIENTS_TABLE_NAME.equals(tableName)) {
            openClientTabListener.onOpenClientTabEvent(client);
        }
    }

    public void setOpenClientTabListener(OpenClientTabListener openClientTabListener) {
        this.openClientTabListener = openClientTabListener;
    }

    private void toogleLastAddedTableNoDataLabel(List<Client> lastAddedClients) {
        if (lastAddedClients.size() == 0) {
            lastAddedClientsTable.setVisible(false);
            lastAddedClientsTable.getTableHeader().setVisible(false);
            noClientsAddedLabel.setVisible(true);
        } else {
            lastAddedClientsTable.setVisible(true);
            lastAddedClientsTable.getTableHeader().setVisible(true);
            noClientsAddedLabel.setVisible(false);
        }
    }
    
    private List<Client> getLastAddedClients(){
        List<Client> clients = new ArrayList<Client>();
        
        try {
            clients = ClientTableHelper.getLastAdded(MAX_LAST_ADDED_CLIENTS_COUNT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return clients;
    }
}
