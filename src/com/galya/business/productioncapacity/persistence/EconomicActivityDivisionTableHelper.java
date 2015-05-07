package com.galya.business.productioncapacity.persistence;

import static com.galya.business.productioncapacity.persistence.ProductionCapacityDatabaseManager.VARCHAR_MAX_LENGTH;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.galya.business.productioncapacity.model.EconomicActivityDivision;

public class EconomicActivityDivisionTableHelper implements TableHelper {

    private static final String DATABASE_NAME = ProductionCapacityDatabaseManager.DATABASE_NAME;

    private static final String EA_DIVISION_TABLE = "Economic_Activity_Division";

    private static final String EA_DIVISION_TABLE_ID = "_id";
    private static final String EA_DIVISION_TABLE_DIVISION_ID = "id";
    private static final String EA_DIVISION_TABLE_DIVISION_NAME = "name";
    private static final String EA_DIVISION_TABLE_SECTION_ID = "section_id";

    private static final String EA_DIVISION_TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS " + EA_DIVISION_TABLE
            + " (" + EA_DIVISION_TABLE_ID + " integer primary key autoincrement, " + EA_DIVISION_TABLE_DIVISION_ID
            + " varchar(" + VARCHAR_MAX_LENGTH + ") unique not null, " + EA_DIVISION_TABLE_DIVISION_NAME + " varchar("
            + VARCHAR_MAX_LENGTH + ") not null, " + EA_DIVISION_TABLE_SECTION_ID + " varchar(" + VARCHAR_MAX_LENGTH
            + ") not null" + ");";

    private static EconomicActivityDivisionTableHelper instance;

    public static EconomicActivityDivisionTableHelper getInstance() {
        if (instance == null) {
            return new EconomicActivityDivisionTableHelper();
        }
        return instance;
    }

    private EconomicActivityDivisionTableHelper() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public String getTableName() {
        return EA_DIVISION_TABLE;
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(EA_DIVISION_TABLE_CREATION_QUERY);
        stmt.close();
    }

    @Override
    public void populateDefaultValues(Connection connection) throws SQLException {
        PreparedStatement stmt = connection
                .prepareStatement("INSERT INTO "
                        + EA_DIVISION_TABLE
                        + " ("
                        + EA_DIVISION_TABLE_DIVISION_ID
                        + ", "
                        + EA_DIVISION_TABLE_DIVISION_NAME
                        + ", "
                        + EA_DIVISION_TABLE_SECTION_ID
                        + ") VALUES ('01', 'Растениевъдство, животновъдство и лов; спомагателни дейности', 'A'),('02', 'Горско стопанство', 'A'),('03', 'Рибно стопанство', 'A'),('05', 'Добив на въглища', 'B'),('06', 'Добив на нефт и природен газ', 'B'),('07', 'Добив на метални руди', 'B'),('08', 'Добив на неметални материали и суровини', 'B'),('09', 'Спомагателни дейности в добива', 'B'),('10', 'Производство на хранителни продукти', 'C'),('11', 'Производство на напитки', 'C'),('12', 'Производство на тютюневи изделия', 'C'),('13', 'Производство на текстил и изделия от текстил, без облело', 'C'),('14', 'Производство на облекло', 'C'),('15', 'Обработка на кожи; производство на обувки и други изделия от обработени кожи без косъм', 'C'),('16', 'Производство на дървен материал и изделия от дървен материал и корк, без мебели; производство на изделия от слама и материали за плетене', 'C'),('17', 'Производство на хартия, картон и изделия от хартия и картон', 'C'),('18', 'Печатна дейност и възпроизвеждане на записани носители', 'C'),('19', 'Производство на кокс и рафинирани нефтопродукти', 'C'),('20', 'Производство на химични продукти', 'C'),('21', 'Производство на лекарствени вещества и продукти', 'C'),('22', 'Производство на изделия от каучук и пластмаси', 'C'),('23', 'Производство на изделия от други неметални минерални суровини', 'C'),('24', 'Производство на основни метали', 'C'),('25', 'Производство на метални изделия, без машини и оборудване', 'C'),('26', 'Производство на компютърна и комуникационна техника, електронни и оптични продукти', 'C'),('27', 'Производство на електрически съоръжения', 'C'),('28', 'Производство на машини и оборудване, с общо и специално предназначение', 'C'),('29', 'Производство на автомобили, ремаркета и полуремаркета', 'C'),('30', 'Производство на превозни средства, без автомобили', 'C'),('31', 'Производство на мебели', 'C'),('32', 'Производство, некласифицирано другаде', 'C'),('33', 'Ремонт и инсталиране на машини и оборудване', 'C'),('35', 'Производство и разпределение на електрическа и топлинна енергия и на газообразни горива', 'D'),('36', 'Събиране, пречистване и доставяне на води', 'E'),('37', 'Събиране, отвеждане и пречистване на отпадъчни води', 'E'),('38', 'Събиране и обезвреждане на отпадъци; рециклиране на материали', 'E'),('39', 'Възстановяване и други услуги по управление на отпадъци', 'E'),('41', 'Строителство на сгради', 'F'),('42', 'Строителство на съоръжения', 'F'),('43', 'Специализирани строителни дейности', 'F'),('45', 'Търговия на едро и дребно с автомобили и мотоциклети, техническо обслужване и ремонт', 'G'),('46', 'Търговия на едро, без търговията с автомобили и мотоциклети', 'G'),('47', 'Търговия на дребно, без търговията с автомобили и мотоциклети', 'G'),('49', 'Сухопътен транспорт', 'H'),('50', 'Воден транспорт', 'H'),('51', 'Въздушен транспорт', 'H'),('52', 'Складиране на товари и спомагателни дейности в транспорта', 'H'),('53', 'Пощенски и куриерски дейности', 'H'),('55', 'Хотелиерство', 'I'),('56', 'Ресторантьорство', 'I'),('58', 'Издателска дейност', 'J'),('59', 'Производство на филми и телевизионни предавания, звукозаписване и издаване на музика', 'J'),('60', 'Радио- и телевизионна дейност', 'J'),('61', 'Далекосъобщения', 'J'),('62', 'Дейности в областта на информационните технологии', 'J'),('63', 'Информационни услуги', 'J'),('64', 'Предоставяне на финансови услуги, без застраховане и допълнително пенсионно осигуряване', 'K'),('65', 'Застраховане, презастраховане и допълнително пенсионно осигуряване', 'K'),('66', 'Спомагателни дейности във финансовите услуги и застраховането', 'K'),('68', 'Операции с недвижими имоти', 'L'),('69', 'Юридически и счетоводни дейности', 'M'),('70', 'Дейност на централни офиси; консултантски дейности в областта на управлението', 'M'),('71', 'Архитектурни и инженерни дейности; технически изпитвания и анализи', 'M'),('72', 'Научноизследователска и развойна дейност', 'M'),('73', 'Рекламна дейност и проучване на пазари', 'M'),('74', 'Други професионални дейности', 'M'),('75', 'Ветеринарномедицинска дейност', 'M'),('77', 'Даване под наем и оперативен лизинг', 'N'),('78', 'Дейности по наемане и предоставяне на работна сила', 'N'),('79', 'Туристическа агентска и операторска дейност; други дейности, свързани с пътувания и резервации', 'N'),('80', 'Дейности по охрана и разследване', 'N'),('81', 'Дейности по обслужване на сгради и озеленяване', 'N'),('82', 'Административни офис дейности и друго спомагателно обслужване на стопанската дейност', 'N'),('84', 'Държавно управление', 'O'),('85', 'Образование', 'P'),('86', 'Хуманно здравеопазване', 'Q'),('87', 'Медико-социални грижи с настаняване', 'Q'),('88', 'Социална работа без настаняване', 'Q'),('90', 'Артистична и творческа дейност', 'R'),('91', 'Други дейности в областта на културата', 'R'),('92', 'Организиране на хазартни игри', 'R'),('93', 'Спортни и други дейности, свързани с развлечения и отдих', 'R'),('94', 'Дейности на организации с нестопанска цел', 'S'),('95', 'Ремонт на компютърна техника, на лични и домакински вещи', 'S'),('96', 'Други персонални услуги', 'S'),('97', 'Дейности на домакинства като работодатели на домашен персонал', 'T'),('98', 'Недиференцирани дейности на домакинства по производство на стоки и услуги за собствено потребление', 'T'),('99', 'Дейности на екстериториални организации и служби', 'U')");
        stmt.executeUpdate();
        stmt.close();
    }

    public List<EconomicActivityDivision> getAll() {
        List<EconomicActivityDivision> allDivisions = new ArrayList<EconomicActivityDivision>();

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + EA_DIVISION_TABLE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long databaseId = rs.getInt(EA_DIVISION_TABLE_ID);
                String divisionId = rs.getString(EA_DIVISION_TABLE_DIVISION_ID);
                String divisionName = rs.getString(EA_DIVISION_TABLE_DIVISION_NAME);
                String sectionId = rs.getString(EA_DIVISION_TABLE_SECTION_ID);
                EconomicActivityDivision currentDivision = new EconomicActivityDivision(databaseId, divisionId,
                        divisionName, sectionId);
                allDivisions.add(currentDivision);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDivisions;
    }

    public List<EconomicActivityDivision> getBySectionId(String sectionId) {
        List<EconomicActivityDivision> divisionsBySectionId = new ArrayList<EconomicActivityDivision>();

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + EA_DIVISION_TABLE + " WHERE "
                    + EA_DIVISION_TABLE_SECTION_ID + "=?");
            stmt.setString(1, sectionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long databaseId = rs.getInt(EA_DIVISION_TABLE_ID);
                String divisionId = rs.getString(EA_DIVISION_TABLE_DIVISION_ID);
                String divisionName = rs.getString(EA_DIVISION_TABLE_DIVISION_NAME);
                EconomicActivityDivision currentClass = new EconomicActivityDivision(databaseId, divisionId,
                        divisionName, sectionId);
                divisionsBySectionId.add(currentClass);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisionsBySectionId;
    }

    public EconomicActivityDivision getByDivisionId(String divisionId) {
        EconomicActivityDivision division = null;

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + EA_DIVISION_TABLE + " WHERE "
                    + EA_DIVISION_TABLE_DIVISION_ID + "=?");
            stmt.setString(1, divisionId);

            ResultSet rs = stmt.executeQuery();

            boolean isAnyResult = rs.next();
            if (isAnyResult) {
                division = new EconomicActivityDivision(rs.getLong(EA_DIVISION_TABLE_ID), divisionId, rs.getString(EA_DIVISION_TABLE_DIVISION_NAME), rs.getString(EA_DIVISION_TABLE_SECTION_ID));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return division;
    }
}
