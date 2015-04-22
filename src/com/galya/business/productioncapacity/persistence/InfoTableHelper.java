package com.galya.business.productioncapacity.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.galya.business.productioncapacity.Constants;

public class InfoTableHelper implements TableHelper {

    private static final String DATABASE_NAME = ProductionCapacityDatabaseManager.DATABASE_NAME;
    private static final String SYSTEM_INFO_TABLE = "SystemInfo";

    private static final String SYSTEM_INFO_TABLE_ID = "_id";
    private static final String SYSTEM_INFO_TABLE_KEY = "system_info_key";
    private static final String SYSTEM_INFO_TABLE_VALUE = "system_info_value";

    private static final String SYSTEM_INFO_TABLE_KEY_IS_AUTO_LOGIN = "IsAutoLogin";

    private static final String SYSTEM_INFO_TABLE_KEY_USERNAME = Constants.USERNAME;
    private static final String SYSTEM_INFO_TABLE_VALUE_PASSWORD = Constants.PASSWORD;

    private static final String SYSTEM_INFO_TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS " + SYSTEM_INFO_TABLE
            + " (" + SYSTEM_INFO_TABLE_ID + " integer primary key autoincrement, " + SYSTEM_INFO_TABLE_KEY
            + " varchar(" + ProductionCapacityDatabaseManager.VARCHAR_MAX_LENGTH + ") unique not null, " + SYSTEM_INFO_TABLE_VALUE + " varchar("
            + ProductionCapacityDatabaseManager.VARCHAR_MAX_LENGTH + ") not null" + ");";

    private static InfoTableHelper instance;

    public static InfoTableHelper getInstance() {
        if (instance == null) {
            return new InfoTableHelper();
        }
        return instance;
    }

    private InfoTableHelper() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }
    
    @Override
    public String getTableName() {
        return SYSTEM_INFO_TABLE;
    }
    
    @Override
    public void createTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(SYSTEM_INFO_TABLE_CREATION_QUERY);
        stmt.close();
    }

    @Override
    public void populateDefaultValues(Connection connection) throws SQLException {
        setDefaultAutoLoginEnabled(connection);
        setDefaultUser(connection);
    }

    public boolean isAutoLoginEnabled() {
        boolean isAutoUpdateEnabled = false;

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT " + SYSTEM_INFO_TABLE_VALUE + " FROM "
                    + SYSTEM_INFO_TABLE + " WHERE " + SYSTEM_INFO_TABLE_KEY + "=?");
            stmt.setString(1, SYSTEM_INFO_TABLE_KEY_IS_AUTO_LOGIN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String isAutoLoginEnabledString = rs.getString(SYSTEM_INFO_TABLE_VALUE);
                isAutoUpdateEnabled = Boolean.parseBoolean(isAutoLoginEnabledString);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAutoUpdateEnabled;
    }

    public void setAutoLogin(boolean enableAutoLogin) {
        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            updateValue(connection, SYSTEM_INFO_TABLE_KEY_IS_AUTO_LOGIN, String.valueOf(enableAutoLogin));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean areValidCredentials(String username, String pass) {
        boolean areValidCredentials = false;
        
        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * " + " FROM "
                    + SYSTEM_INFO_TABLE + " WHERE " + SYSTEM_INFO_TABLE_KEY + "=? AND " + SYSTEM_INFO_TABLE_VALUE + "=?");
            stmt.setString(1, username);
            stmt.setString(2, pass);
            
            ResultSet rs = stmt.executeQuery();
            areValidCredentials = rs.next();
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return areValidCredentials;
    }

    private void setDefaultAutoLoginEnabled(Connection connection) throws SQLException {
        addKeyValue(connection, SYSTEM_INFO_TABLE_KEY_IS_AUTO_LOGIN, String.valueOf(false));
    }

    private void setDefaultUser(Connection connection) throws SQLException {
        addKeyValue(connection, SYSTEM_INFO_TABLE_KEY_USERNAME, SYSTEM_INFO_TABLE_VALUE_PASSWORD);
    }

    private void addKeyValue(Connection connection, String key, String value) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + SYSTEM_INFO_TABLE + "("
                + SYSTEM_INFO_TABLE_KEY + ", " + SYSTEM_INFO_TABLE_VALUE + ")" + " VALUES (?,?)");
        stmt.setString(1, key);
        stmt.setString(2, value);
        stmt.executeUpdate();
        stmt.close();
    }
    
    private void updateValue(Connection connection, String key, String value) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE " + SYSTEM_INFO_TABLE + " SET "
                + SYSTEM_INFO_TABLE_VALUE + "=? " + " WHERE " + SYSTEM_INFO_TABLE_KEY + "=?");
        stmt.setString(1, value);
        stmt.setString(2, key);
        stmt.executeUpdate();
        stmt.close();
    }

}
