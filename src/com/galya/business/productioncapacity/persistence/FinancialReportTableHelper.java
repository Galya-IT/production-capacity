package com.galya.business.productioncapacity.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.galya.business.productioncapacity.model.FinancialReport;

public class FinancialReportTableHelper implements TableHelper {

    private static final String DATABASE_NAME = ProductionCapacityDatabaseManager.DATABASE_NAME;
    private static final String FINANCIAL_REPORT_TABLE = "Financial_Report";

    private static final String FINANCIAL_REPORT_TABLE_ID = "_id";

    private static final String FINANCIAL_REPORT_TABLE_COMPANY_ID = "company_id";
    private static final String FINANCIAL_REPORT_TABLE_YEAR = "year";
    private static final String FINANCIAL_REPORT_TABLE_ASSETS_SUM = "assets_sum";
    private static final String FINANCIAL_REPORT_TABLE_NET_PROFIT = "net_profit";
    private static final String FINANCIAL_REPORT_TABLE_EQUITY = "equity";
    private static final String FINANCIAL_REPORT_TABLE_LIABILITIES_PROVISIONS = "liabilities_and_provisions";
    private static final String FINANCIAL_REPORT_TABLE_NET_SALES = "net_sales";
    private static final String FINANCIAL_REPORT_TABLE_INCOME_OP_ACTIVITIES = "income_operating_activities";
    private static final String FINANCIAL_REPORT_TABLE_OUTLAY_OP_ACTIVITIES = "outlay_operating_activities";
    private static final String FINANCIAL_REPORT_TABLE_AMORTIZATION = "amortization";
    private static final String FINANCIAL_REPORT_TABLE_AVG_STAFF_NUMBER = "avg_staff_number";
    private static final String FINANCIAL_REPORT_TABLE_INVESTMENTS_EQUIPMENT = "investments_equipment";
    private static final String FINANCIAL_REPORT_TABLE_EARNINGS_EXPORT_TOLLING = "earningsExportTolling";

    // @formatter:off
    private static final String FINANCIAL_REPORT_TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS "
            + FINANCIAL_REPORT_TABLE + " ("
            + FINANCIAL_REPORT_TABLE_ID + " integer primary key autoincrement, "
            + FINANCIAL_REPORT_TABLE_COMPANY_ID + " integer not null, "
            + FINANCIAL_REPORT_TABLE_YEAR + " integer not null, "
            + FINANCIAL_REPORT_TABLE_ASSETS_SUM + " integer, "
            + FINANCIAL_REPORT_TABLE_NET_PROFIT + " integer, "
            + FINANCIAL_REPORT_TABLE_EQUITY + " integer, "
            + FINANCIAL_REPORT_TABLE_LIABILITIES_PROVISIONS + " integer, "
            + FINANCIAL_REPORT_TABLE_NET_SALES + " integer, "
            + FINANCIAL_REPORT_TABLE_INCOME_OP_ACTIVITIES + " integer, "
            + FINANCIAL_REPORT_TABLE_OUTLAY_OP_ACTIVITIES + " integer, "
            + FINANCIAL_REPORT_TABLE_AMORTIZATION + " integer, "
            + FINANCIAL_REPORT_TABLE_AVG_STAFF_NUMBER + " integer, "
            + FINANCIAL_REPORT_TABLE_INVESTMENTS_EQUIPMENT + " integer, "
            + FINANCIAL_REPORT_TABLE_EARNINGS_EXPORT_TOLLING + " integer" + ");";
    // @formatter:on

    private static FinancialReportTableHelper instance;

    public static FinancialReportTableHelper getInstance() {
        if (instance == null) {
            return new FinancialReportTableHelper();
        }
        return instance;
    }

    private FinancialReportTableHelper() {
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public String getTableName() {
        return FINANCIAL_REPORT_TABLE;
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(FINANCIAL_REPORT_TABLE_CREATION_QUERY);
        stmt.close();
    }

    @Override
    public void populateDefaultValues(Connection connection) throws SQLException {
    }

    public long addReport(long companyId, FinancialReport report) {
        long id = -1;

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            // @formatter:off
            String insertNewReportQuery = "INSERT INTO " + FINANCIAL_REPORT_TABLE + "("
                    + FINANCIAL_REPORT_TABLE_COMPANY_ID + ", "
                    + FINANCIAL_REPORT_TABLE_YEAR + ", "
                    + FINANCIAL_REPORT_TABLE_ASSETS_SUM + ", "
                    + FINANCIAL_REPORT_TABLE_NET_PROFIT + ", "
                    + FINANCIAL_REPORT_TABLE_EQUITY + ", "
                    + FINANCIAL_REPORT_TABLE_LIABILITIES_PROVISIONS + ", "
                    + FINANCIAL_REPORT_TABLE_NET_SALES + ", "
                    + FINANCIAL_REPORT_TABLE_INCOME_OP_ACTIVITIES + ", "
                    + FINANCIAL_REPORT_TABLE_OUTLAY_OP_ACTIVITIES + ", "
                    + FINANCIAL_REPORT_TABLE_AMORTIZATION + ", "
                    + FINANCIAL_REPORT_TABLE_AVG_STAFF_NUMBER + ", "
                    + FINANCIAL_REPORT_TABLE_INVESTMENTS_EQUIPMENT + ", "
                    + FINANCIAL_REPORT_TABLE_EARNINGS_EXPORT_TOLLING + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // @formatter:on

            PreparedStatement statement = connection.prepareStatement(insertNewReportQuery,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, companyId);
            statement.setInt(2, report.getYear());
            statement.setDouble(3, report.getAssetsSum());
            statement.setDouble(4, report.getNetProfit());
            statement.setDouble(5, report.getEquity());
            statement.setDouble(6, report.getLiabilitiesProvisions());
            statement.setDouble(7, report.getNetSales());
            statement.setDouble(8, report.getIncomeOperatingActivities());
            statement.setDouble(9, report.getOutlayOperatingActivities());
            statement.setDouble(10, report.getAmortization());
            statement.setDouble(11, report.getAvgStaffNumber());
            statement.setDouble(12, report.getInvestmentsEquipment());
            statement.setDouble(13, report.getEarningsExportTolling());

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating financial report failed, no ID obtained.");
                }
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean updateReport(long companyId, FinancialReport report) {
        boolean isSuccessful = false;

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            // @formatter:off
            String updateOldReportQuery = "UPDATE " + FINANCIAL_REPORT_TABLE + " SET "
                    + FINANCIAL_REPORT_TABLE_COMPANY_ID + "=?, "
                    + FINANCIAL_REPORT_TABLE_YEAR + "=?, "
                    + FINANCIAL_REPORT_TABLE_ASSETS_SUM + "=?, "
                    + FINANCIAL_REPORT_TABLE_NET_PROFIT + "=?, "
                    + FINANCIAL_REPORT_TABLE_EQUITY + "=?, "
                    + FINANCIAL_REPORT_TABLE_LIABILITIES_PROVISIONS + "=?, "
                    + FINANCIAL_REPORT_TABLE_NET_SALES + "=?, "
                    + FINANCIAL_REPORT_TABLE_INCOME_OP_ACTIVITIES + "=?, "
                    + FINANCIAL_REPORT_TABLE_OUTLAY_OP_ACTIVITIES + "=?, "
                    + FINANCIAL_REPORT_TABLE_AMORTIZATION + "=?, "
                    + FINANCIAL_REPORT_TABLE_AVG_STAFF_NUMBER + "=?, "
                    + FINANCIAL_REPORT_TABLE_INVESTMENTS_EQUIPMENT + "=?, "
                    + FINANCIAL_REPORT_TABLE_EARNINGS_EXPORT_TOLLING + "=? "
                    + "WHERE " + FINANCIAL_REPORT_TABLE_ID + "=?";
            // @formatter:on

            PreparedStatement statement = connection.prepareStatement(updateOldReportQuery);

            statement.setLong(1, companyId);
            statement.setInt(2, report.getYear());
            statement.setDouble(3, report.getAssetsSum());
            statement.setDouble(4, report.getNetProfit());
            statement.setDouble(5, report.getEquity());
            statement.setDouble(6, report.getLiabilitiesProvisions());
            statement.setDouble(7, report.getNetSales());
            statement.setDouble(8, report.getIncomeOperatingActivities());
            statement.setDouble(9, report.getOutlayOperatingActivities());
            statement.setDouble(10, report.getAmortization());
            statement.setDouble(11, report.getAvgStaffNumber());
            statement.setDouble(12, report.getInvestmentsEquipment());
            statement.setDouble(13, report.getEarningsExportTolling());
            statement.setLong(14, report.getId());

            long affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                isSuccessful = true;
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }

    public List<FinancialReport> getReportsByClientId(long companyId) {
        List<FinancialReport> financialReportsList = new ArrayList<FinancialReport>();

        try (Connection connection = ProductionCapacityDatabaseManager.getConnection();) {
            String selectLastAddedQuery = "SELECT * FROM " + FINANCIAL_REPORT_TABLE + " WHERE "
                    + FINANCIAL_REPORT_TABLE_COMPANY_ID + "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectLastAddedQuery);
            preparedStatement.setLong(1, companyId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long databaseId = rs.getLong(FINANCIAL_REPORT_TABLE_ID);
                int year = rs.getInt(FINANCIAL_REPORT_TABLE_YEAR);
                double assetsSum = rs.getDouble(FINANCIAL_REPORT_TABLE_ASSETS_SUM);
                double netProfit = rs.getDouble(FINANCIAL_REPORT_TABLE_NET_PROFIT);
                double equity = rs.getDouble(FINANCIAL_REPORT_TABLE_EQUITY);
                double liabilitiesProvisions = rs.getDouble(FINANCIAL_REPORT_TABLE_LIABILITIES_PROVISIONS);
                double netSales = rs.getDouble(FINANCIAL_REPORT_TABLE_NET_SALES);
                double incomeOperatingActivities = rs.getDouble(FINANCIAL_REPORT_TABLE_INCOME_OP_ACTIVITIES);
                double outlayOperatingActivities = rs.getDouble(FINANCIAL_REPORT_TABLE_OUTLAY_OP_ACTIVITIES);
                double amortization = rs.getDouble(FINANCIAL_REPORT_TABLE_AMORTIZATION);
                double avgStaffNumber = rs.getDouble(FINANCIAL_REPORT_TABLE_AVG_STAFF_NUMBER);
                double investmentsEquipment = rs.getDouble(FINANCIAL_REPORT_TABLE_INVESTMENTS_EQUIPMENT);
                double earningsExportTolling = rs.getDouble(FINANCIAL_REPORT_TABLE_EARNINGS_EXPORT_TOLLING);
                
                FinancialReport financialReport = new FinancialReport(year, assetsSum, netProfit, equity,
                        liabilitiesProvisions, netSales, incomeOperatingActivities, outlayOperatingActivities,
                        amortization, avgStaffNumber, investmentsEquipment, earningsExportTolling);

                financialReport.setId(databaseId);
                financialReportsList.add(financialReport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return financialReportsList;
    }
}
