package com.galya.business.productioncapacity.persistence;

import static com.galya.business.productioncapacity.persistence.ProductionCapacityDatabaseManager.VARCHAR_MAX_LENGTH;
import static com.galya.business.productioncapacity.utils.CommonUtils.isEmpty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.galya.business.productioncapacity.model.Client;

public class ClientTableHelper implements TableHelper {

    private static final String DATABASE_NAME = ProductionCapacityDatabaseManager.DATABASE_NAME;
    private static final String CLIENT_TABLE = "Client";

    private static final String CLIENT_TABLE_ID = "_id";
    private static final String CLIENT_TABLE_NAME = "name";
    private static final String CLIENT_TABLE_BULSTAT = "bulstat";
    private static final String CLIENT_TABLE_SEAT = "seat_address";
    private static final String CLIENT_TABLE_EA_SECTION_ID = "economic_activity_section_id";
    private static final String CLIENT_TABLE_EA_DIVISION_ID = "economic_activity_division_id";
    private static final String CLIENT_TABLE_EA_GROUP_ID = "economic_activity_group_id";
    private static final String CLIENT_TABLE_EA_CLASS_ID = "economic_activity_class_id";
    private static final String CLIENT_TABLE_INV_REGION = "investment_region";
    private static final String CLIENT_TABLE_INV_DISTRICT = "investment_district";
    private static final String CLIENT_TABLE_INV_MUNICIPALITY = "investment_municipality";
    private static final String CLIENT_TABLE_INV_ADDRESS = "investment_address";
    private static final String CLIENT_TABLE_FIRST_RECEPTION_DATE = "first_documents_reception_date_timestamp";
    private static final String CLIENT_TABLE_LAST_RECEPTION_DATE = "last_documents_Reception_date_timestamp";
    private static final String CLIENT_TABLE_NOTES = "notes";
    private static final String CLIENT_TABLE_CATEGORY_ID = "category_id";
    private static final String CLIENT_TABLE_WHOLE_INV_AMOUNT = "whole_investition_amount";
    private static final String CLIENT_TABLE_STANDARDS = "standards";
    private static final String CLIENT_TABLE_SOFTWARE_SYSTEMS = "software_systems";
    private static final String CLIENT_TABLE_COMPANIES_CONNECTIONS = "other_companies_connections";

    // this limit doesn't work for SQLite, but for other SQL DBs will be fine
    private static final String TABLE_CREATION_QUERY_TYPE_VARCHAR = " varchar(" + VARCHAR_MAX_LENGTH + ") ";

    // @formatter:off
    private static final String CLIENT_TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS " + CLIENT_TABLE + " ("
            + CLIENT_TABLE_ID + " integer primary key autoincrement, "
            + CLIENT_TABLE_NAME + TABLE_CREATION_QUERY_TYPE_VARCHAR + "unique not null, "
            + CLIENT_TABLE_BULSTAT + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_SEAT + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_EA_SECTION_ID + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_EA_DIVISION_ID + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_EA_GROUP_ID + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_EA_CLASS_ID + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_INV_REGION + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_INV_DISTRICT + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_INV_MUNICIPALITY + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_INV_ADDRESS + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_FIRST_RECEPTION_DATE + " integer, "
            + CLIENT_TABLE_LAST_RECEPTION_DATE + " integer, "
            + CLIENT_TABLE_NOTES + " varchar, "
            + CLIENT_TABLE_CATEGORY_ID + " integer, "
            + CLIENT_TABLE_WHOLE_INV_AMOUNT + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_STANDARDS + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_SOFTWARE_SYSTEMS + TABLE_CREATION_QUERY_TYPE_VARCHAR + ", "
            + CLIENT_TABLE_COMPANIES_CONNECTIONS + TABLE_CREATION_QUERY_TYPE_VARCHAR + ");";
    // @formatter:on

    private static ClientTableHelper instance;

    public static ClientTableHelper getInstance() {
        if (instance == null) {
            return new ClientTableHelper();
        }
        return instance;
    }

    private ClientTableHelper() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public String getTableName() {
        return CLIENT_TABLE;
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(CLIENT_TABLE_CREATION_QUERY);
        stmt.close();
    }

    @Override
    public void populateDefaultValues(Connection connection) throws SQLException {
    }

    public long addClient(Client client) {
        long id = -1;

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            // @formatter:off
            String insertNewClientQuery = "INSERT INTO " + CLIENT_TABLE + "("
                    + CLIENT_TABLE_NAME + ", "
                    + CLIENT_TABLE_BULSTAT + ", "
                    + CLIENT_TABLE_SEAT + ", "
                    + CLIENT_TABLE_EA_SECTION_ID + ", "
                    + CLIENT_TABLE_EA_DIVISION_ID + ", "
                    + CLIENT_TABLE_EA_GROUP_ID + ", "
                    + CLIENT_TABLE_EA_CLASS_ID + ", "
                    + CLIENT_TABLE_INV_REGION + ", "
                    + CLIENT_TABLE_INV_DISTRICT + ", "
                    + CLIENT_TABLE_INV_MUNICIPALITY + ", "
                    + CLIENT_TABLE_INV_ADDRESS + ", "
                    + CLIENT_TABLE_FIRST_RECEPTION_DATE + ", "
                    + CLIENT_TABLE_LAST_RECEPTION_DATE + ", "
                    + CLIENT_TABLE_NOTES + ", "
                    + CLIENT_TABLE_CATEGORY_ID + ", "
                    + CLIENT_TABLE_WHOLE_INV_AMOUNT + ", "
                    + CLIENT_TABLE_STANDARDS + ", "
                    + CLIENT_TABLE_SOFTWARE_SYSTEMS + ", "
                    + CLIENT_TABLE_COMPANIES_CONNECTIONS + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // @formatter:on

            PreparedStatement statement = connection.prepareStatement(insertNewClientQuery,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.getName());

            if (!isEmpty(client.getBulstat())) {
                statement.setString(2, client.getBulstat());
            }

            if (!isEmpty(client.getSeatAddress())) {
                statement.setString(3, client.getSeatAddress());
            }

            if (client.getEconomicActivitySection() != null) {
                statement.setString(4, client.getEconomicActivitySection().getId());
            }

            if (client.getEconomicActivityDivision() != null) {
                statement.setString(5, client.getEconomicActivityDivision().getId());
            }

            if (client.getEconomicActivityGroup() != null) {
                statement.setString(6, client.getEconomicActivityGroup().getId());
            }

            if (client.getEconomicActivityClass() != null) {
                statement.setString(7, client.getEconomicActivityClass().getId());
            }

            if (client.getInvestmentRegion() != null) {
                statement.setString(8, client.getInvestmentRegion());
            }

            if (client.getInvestmentDistrict() != null) {
                statement.setString(9, client.getInvestmentDistrict());
            }

            if (client.getInvestmentMunicipality() != null) {
                statement.setString(10, client.getInvestmentMunicipality());
            }

            if (!isEmpty(client.getInvestmentAddress())) {
                statement.setString(11, client.getInvestmentAddress());
            }

            if (client.getFirstDocumentsReceptionDate() != null) {
                statement.setLong(12, client.getFirstDocumentsReceptionDate().getTime());
            }

            if (client.getLastDocumentsReceptionDate() != null) {
                statement.setLong(13, client.getLastDocumentsReceptionDate().getTime());
            }

            if (!isEmpty(client.getNotes())) {
                statement.setString(14, client.getNotes());
            }

            if (client.getCategory() != null) {
                statement.setInt(15, client.getCategory().getId());
            }

            if (!isEmpty(client.getWholeInvestitionAmount())) {
                statement.setString(16, client.getWholeInvestitionAmount());
            }

            if (!isEmpty(client.getStandards())) {
                statement.setString(17, client.getStandards());
            }

            if (!isEmpty(client.getSoftwareSystems())) {
                statement.setString(18, client.getSoftwareSystems());
            }

            if (!isEmpty(client.getOtherCompaniesConnections())) {
                statement.setString(19, client.getOtherCompaniesConnections());
            }

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean updateClient(Client client) {
        boolean isSuccessful = false;
        
        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            // @formatter:off
            String updateOldClientQuery = "UPDATE " + CLIENT_TABLE + " SET "
                    + CLIENT_TABLE_NAME + "=?, "
                    + CLIENT_TABLE_BULSTAT + "=?, "
                    + CLIENT_TABLE_SEAT + "=?, "
                    + CLIENT_TABLE_EA_SECTION_ID + "=?, "
                    + CLIENT_TABLE_EA_DIVISION_ID + "=?, "
                    + CLIENT_TABLE_EA_GROUP_ID + "=?, "
                    + CLIENT_TABLE_EA_CLASS_ID + "=?, "
                    + CLIENT_TABLE_INV_REGION + "=?, "
                    + CLIENT_TABLE_INV_DISTRICT + "=?, "
                    + CLIENT_TABLE_INV_MUNICIPALITY + "=?, "
                    + CLIENT_TABLE_INV_ADDRESS + "=?, "
                    + CLIENT_TABLE_FIRST_RECEPTION_DATE + "=?, "
                    + CLIENT_TABLE_LAST_RECEPTION_DATE + "=?, "
                    + CLIENT_TABLE_NOTES + "=?, "
                    + CLIENT_TABLE_CATEGORY_ID + "=?, "
                    + CLIENT_TABLE_WHOLE_INV_AMOUNT + "=?, "
                    + CLIENT_TABLE_STANDARDS + "=?, "
                    + CLIENT_TABLE_SOFTWARE_SYSTEMS + "=?, "
                    + CLIENT_TABLE_COMPANIES_CONNECTIONS + "=? "
                    + "WHERE " + CLIENT_TABLE_ID + "=?";
            // @formatter:on

            PreparedStatement statement = connection.prepareStatement(updateOldClientQuery);

            statement.setString(1, client.getName());

            if (!isEmpty(client.getBulstat())) {
                statement.setString(2, client.getBulstat());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!isEmpty(client.getSeatAddress())) {
                statement.setString(3, client.getSeatAddress());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (client.getEconomicActivitySection() != null) {
                statement.setString(4, client.getEconomicActivitySection().getId());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (client.getEconomicActivityDivision() != null) {
                statement.setString(5, client.getEconomicActivityDivision().getId());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            if (client.getEconomicActivityGroup() != null) {
                statement.setString(6, client.getEconomicActivityGroup().getId());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            if (client.getEconomicActivityClass() != null) {
                statement.setString(7, client.getEconomicActivityClass().getId());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }

            if (client.getInvestmentRegion() != null) {
                statement.setString(8, client.getInvestmentRegion());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }

            if (client.getInvestmentDistrict() != null) {
                statement.setString(9, client.getInvestmentDistrict());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }

            if (client.getInvestmentMunicipality() != null) {
                statement.setString(10, client.getInvestmentMunicipality());
            } else {
                statement.setNull(10, Types.VARCHAR);
            }

            if (!isEmpty(client.getInvestmentAddress())) {
                statement.setString(11, client.getInvestmentAddress());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }

            if (client.getFirstDocumentsReceptionDate() != null) {
                statement.setLong(12, client.getFirstDocumentsReceptionDate().getTime());
            } else {
                statement.setNull(12, Types.INTEGER);
            }

            if (client.getLastDocumentsReceptionDate() != null) {
                statement.setLong(13, client.getLastDocumentsReceptionDate().getTime());
            } else {
                statement.setNull(13, Types.INTEGER);
            }

            if (!isEmpty(client.getNotes())) {
                statement.setString(14, client.getNotes());
            } else {
                statement.setNull(14, Types.VARCHAR);
            }

            if (client.getCategory() != null) {
                statement.setInt(15, client.getCategory().getId());
            } else {
                statement.setNull(15, Types.INTEGER);
            }

            if (!isEmpty(client.getWholeInvestitionAmount())) {
                statement.setString(16, client.getWholeInvestitionAmount());
            } else {
                statement.setNull(16, Types.VARCHAR);
            }

            if (!isEmpty(client.getStandards())) {
                statement.setString(17, client.getStandards());
            } else {
                statement.setNull(17, Types.VARCHAR);
            }

            if (!isEmpty(client.getSoftwareSystems())) {
                statement.setString(18, client.getSoftwareSystems());
            } else {
                statement.setNull(18, Types.VARCHAR);
            }

            if (!isEmpty(client.getOtherCompaniesConnections())) {
                statement.setString(19, client.getOtherCompaniesConnections());
            } else {
                statement.setNull(19, Types.VARCHAR);
            }

            statement.setLong(20, client.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                isSuccessful = true;
            }
            
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }
}
