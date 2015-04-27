package com.galya.business.productioncapacity.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProductionCapacityDatabaseManager {

    static final int VARCHAR_MAX_LENGTH = 254;

    static final String DATABASE_NAME = "test.db"; // TODO: change to ProductionCapacity

    private static Set<TableHelper> tableHelpers = new HashSet<TableHelper>();

    public static void initDatabase() {
        tableHelpers.add(InfoTableHelper.getInstance());
        tableHelpers.add(EconomicActivitySectionTableHelper.getInstance());
        tableHelpers.add(EconomicActivityDivisionTableHelper.getInstance());
        tableHelpers.add(EconomicActivityGroupTableHelper.getInstance());
        tableHelpers.add(EconomicActivityClassTableHelper.getInstance());
        tableHelpers.add(AdministrativeRuralAreasTableHelper.getInstance());

        //dropAllTables();
        //createAllTables();
        //populateAllTables();

        for (TableHelper tableHelper : tableHelpers) {
            if (!isTableExisting(tableHelper.getTableName())) {
                try (Connection connection = getConnection();) {
                    tableHelper.createTable(connection);
                    tableHelper.populateDefaultValues(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    static boolean isTableExisting(String tableName) {
        boolean isExisting = false;
        try (Connection connection = getConnection();) {
            PreparedStatement stmt = connection
                    .prepareStatement("SELECT * FROM sqlite_master WHERE type='table' AND name=?");
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            isExisting = rs.next();
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExisting;
    }

    private static void dropTable(String tableName) {
        try (Connection connection = getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("DROP TABLE " + tableName);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropAllTables() {
        for (TableHelper tableHelper : tableHelpers) {
            dropTable(tableHelper.getTableName());
        }
    }

    private static void createAllTables() {
        try (Connection connection = getConnection();) {
            connection.setAutoCommit(false);
            for (TableHelper tableHelper : tableHelpers) {
                tableHelper.createTable(connection);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void populateAllTables() {
        try (Connection connection = getConnection();) {
            connection.setAutoCommit(false);
            for (TableHelper tableHelper : tableHelpers) {
                tableHelper.populateDefaultValues(connection);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
