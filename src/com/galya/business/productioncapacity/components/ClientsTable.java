package com.galya.business.productioncapacity.components;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.galya.business.productioncapacity.ProductionCapacity;
import com.galya.business.productioncapacity.components.misc.ClientTableRowDoubleClickListener;
import com.galya.business.productioncapacity.model.Client;

@SuppressWarnings("serial")
public class ClientsTable {

    private JTable table = new JTable();
    private ClientsTableModel model;
    private String tableName;

    public ClientsTable(String tableName, String[] columnNames, List<Client> clients,
            ClientTableRowDoubleClickListener doubleClickListener) {
        
        this.tableName = tableName;
        
        model = new ClientsTableModel(columnNames, clients);

        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        //lastAddedClientsTable.setFillsViewportHeight(true);
        //lastAddedClientsTable.getTableHeader().setResizingAllowed(false);
        //lastAddedClientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        resizeColumnWidth(table);
        addColumnNamesTooltips(table, columnNames);

        table.setAutoCreateRowSorter(true);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);

                if (mouseEvent.getClickCount() == 2) {
                    Client selectedClient = model.getClientAtRow(row);
                    doubleClickListener.onTableRowDoubleClicked(tableName, selectedClient);
                }
            }
        });
    }
    
    public String getTableName() {
        return tableName;
    }

    public void setVisible(boolean isVisible) {
        table.setVisible(isVisible);
    }

    public JTableHeader getTableHeader() {
        return table.getTableHeader();
    }

    public JTable getTableBody() {
        return table;
    }

    public void setData(List<Client> clients) {
        model.setData(clients);
    }

    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        final int EXPECTED_OFFSET = 20;

        for (int column = 0; column < table.getColumnCount(); column++) {
            final int MIN_TABLE_COL_WIDTH = (ProductionCapacity.MIN_FRAME_WIDTH / table.getColumnCount())
                    - EXPECTED_OFFSET;
            columnModel.getColumn(column).setPreferredWidth(MIN_TABLE_COL_WIDTH);
        }
    }

    private void addColumnNamesTooltips(JTable table, String[] toolTips) {
        ColumnHeaderToolTips tips = new ColumnHeaderToolTips();

        for (int colIndex = 0; colIndex < table.getColumnCount(); colIndex++) {
            TableColumn col = table.getColumnModel().getColumn(colIndex);
            tips.setToolTip(col, toolTips[colIndex]);
        }
        table.getTableHeader().addMouseMotionListener(tips);
    }

    private class ClientsTableModel extends AbstractTableModel {

        private String[] columnNames;
        private List<Client> clients = new ArrayList<Client>();

        public ClientsTableModel(String[] columnNames) {
            this.columnNames = columnNames;
        }

        public ClientsTableModel(String[] columnNames, List<Client> clients) {
            this(columnNames);
            this.clients = clients;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return clients.size();
        }

        @Override
        public Object getValueAt(int row, int col) {
            Client clientAtSpecifiedRow = clients.get(row);
            return getDataAtCol(clientAtSpecifiedRow, col);
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public void setData(List<Client> clients) {
            this.clients = clients;
            this.fireTableDataChanged();
        }

        public Client getClientAtRow(int row) {
            return clients.get(row);
        }

        private String getDataAtCol(Client client, int col) {
            String infoAtSecifiedCol = null;
            switch (col) {
                case 0:
                    infoAtSecifiedCol = client.getName();
                    break;
                case 1:
                    infoAtSecifiedCol = client.getBulstat();
                    break;
            }
            return infoAtSecifiedCol;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private class ColumnHeaderToolTips extends MouseMotionAdapter {
        TableColumn curCol;
        Map tips = new HashMap();

        public void setToolTip(TableColumn col, String tooltip) {
            if (tooltip == null) {
                tips.remove(col);
            } else {
                tips.put(col, tooltip);
            }
        }

        public void mouseMoved(MouseEvent evt) {
            JTableHeader header = (JTableHeader) evt.getSource();
            JTable table = header.getTable();
            TableColumnModel colModel = table.getColumnModel();
            int vColIndex = colModel.getColumnIndexAtX(evt.getX());
            TableColumn col = null;
            if (vColIndex >= 0) {
                col = colModel.getColumn(vColIndex);
            }
            if (col != curCol) {
                header.setToolTipText((String) tips.get(col));
                curCol = col;
            }
        }
    }
}
