package com.galya.business.productioncapacity.persistence;

import static com.galya.business.productioncapacity.persistence.ProductionCapacityDatabaseManager.VARCHAR_MAX_LENGTH;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.galya.business.productioncapacity.model.EconomicActivitySection;

public class EconomicActivitySectionTableHelper implements TableHelper {

    private static final String DATABASE_NAME = ProductionCapacityDatabaseManager.DATABASE_NAME;

    private static final String EA_SECTION_TABLE = "Economic_Activity_Section";

    private static final String EA_SECTION_TABLE_ID = "_id";
    private static final String EA_SECTION_TABLE_SECTION_ID = "id";
    private static final String EA_SECTION_TABLE_SECTION_NAME = "name";

    private static final String EA_SECTION_TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS " + EA_SECTION_TABLE
            + " (" + EA_SECTION_TABLE_ID + " integer primary key autoincrement, " + EA_SECTION_TABLE_SECTION_ID
            + " varchar(" + VARCHAR_MAX_LENGTH + ") unique not null, " + EA_SECTION_TABLE_SECTION_NAME + " varchar("
            + VARCHAR_MAX_LENGTH + ") not null" + ");";

    private static EconomicActivitySectionTableHelper instance;

    public static EconomicActivitySectionTableHelper getInstance() {
        if (instance == null) {
            return new EconomicActivitySectionTableHelper();
        }
        return instance;
    }

    private EconomicActivitySectionTableHelper() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public String getTableName() {
        return EA_SECTION_TABLE;
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(EA_SECTION_TABLE_CREATION_QUERY);
        stmt.close();
    }

    @Override
    public void populateDefaultValues(Connection connection) throws SQLException {
        PreparedStatement stmt = connection
                .prepareStatement("INSERT INTO "
                        + EA_SECTION_TABLE
                        + " ("
                        + EA_SECTION_TABLE_SECTION_ID
                        + ", "
                        + EA_SECTION_TABLE_SECTION_NAME
                        + ") VALUES ('A', 'СЕЛСКО, ГОРСКО И РИБНО СТОПАНСТВО'),('B', 'ДОБИВНА ПРОМИШЛЕНОСТ'),('C', 'ПРЕРАБОТВАЩА ПРОМИШЛЕНОСТ'),('D', 'ПРОИЗВОДСТВО И РАЗПРЕДЕЛЕНИЕ НА ЕЛЕКТРИЧЕСКА И ТОПЛИННА ЕНЕРГИЯ И НА ГАЗООБРАЗНИ ГОРИВА'),('E', 'ДОСТАВЯНЕ НА ВОДИ;КАНАЛИЗАЦИОННИ УСЛУГИ, УПРАВЛЕНИЕ НА ОТПАДЪЦИ И ВЪЗСТАНОВЯВАНЕ'),('F', 'СТРОИТЕЛСТВО'),('G', 'ТЪРГОВИЯ;РЕМОНТ НА АВТОМОБИЛИ И МОТОЦИКЛЕТИ'),('H', 'ТРАНСПОРТ, СКЛАДИРАНЕ И ПОЩИ'),('I', 'ХОТЕЛИЕРСТВО И РЕСТОРАНТЬОРСТВО'),('J', 'СЪЗДАВАНЕ И РАЗПРОСТРАНЕНИЕ НА ИНФОРМАЦИЯ И ТВОРЧЕСКИ ПРОДУКТИ;ДАЛЕКОСЪОБЩЕНИЯ'),('K', 'ФИНАНСОВИ И ЗАСТРАХОВАТЕЛНИ ДЕЙНОСТИ'),('L', 'ОПЕРАЦИИ С НЕДВИЖИМИ ИМОТИ'),('M', 'ПРОФЕСИОНАЛНИ ДЕЙНОСТИ И НАУЧНИ ИЗСЛЕДВАНИЯ'),('N', 'АДМИНИСТРАТИВНИ И СПОМАГАТЕЛНИ ДЕЙНОСТИ'),('O', 'ДЪРЖАВНО УПРАВЛЕНИЕ'),('P', 'ОБРАЗОВАНИЕ'),('Q', 'ХУМАННО ЗДРАВЕОПАЗВАНЕ И СОЦИАЛНА РАБОТА'),('R', 'КУЛТУРА, СПОРТ И РАЗВЛЕЧЕНИЯ'),('S', 'ДРУГИ ДЕЙНОСТИ'),('T', 'ДЕЙНОСТИ НА ДОМАКИНСТВА КАТО РАБОТОДАТЕЛИ;НЕДИФЕРЕНЦИРАНИ ДЕЙНОСТИ НА ДОМАКИНСТВА ПО ПРОИЗВОДСТВО НА СТОКИ И УСЛУГИ ЗА СОБСТВЕНО ПОТРЕБЛЕНИЕ'),('U', 'ДЕЙНОСТИ НА ЕКСТЕРИТОРИАЛНИ ОРГАНИЗАЦИИ И СЛУЖБИ')");
        stmt.executeUpdate();
        stmt.close();
    }

    public List<EconomicActivitySection> getAll() {
        List<EconomicActivitySection> allSections = new ArrayList<EconomicActivitySection>();
        
        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + EA_SECTION_TABLE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int databaseId = rs.getInt(EA_SECTION_TABLE_ID);
                String sectionId = rs.getString(EA_SECTION_TABLE_SECTION_ID);
                String sectionName = rs.getString(EA_SECTION_TABLE_SECTION_NAME);
                EconomicActivitySection currentSection = new EconomicActivitySection(databaseId, sectionId, sectionName);
                allSections.add(currentSection);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSections;
    }

}
