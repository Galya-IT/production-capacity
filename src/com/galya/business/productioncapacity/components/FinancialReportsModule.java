package com.galya.business.productioncapacity.components;

import static com.galya.business.productioncapacity.utils.ComponentFactory.generateLabel;
import static com.galya.business.productioncapacity.utils.ComponentFactory.generateNumericField;
import static com.galya.business.productioncapacity.utils.Validator.validateDouble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import com.galya.business.productioncapacity.GridBagLayoutBuffer;
import com.galya.business.productioncapacity.components.misc.FinancialReportsModuleEventListener;
import com.galya.business.productioncapacity.components.misc.FinancialReportsModuleEventType;
import com.galya.business.productioncapacity.components.tabs.ClientTab.COLUMN;
import com.galya.business.productioncapacity.model.FinancialReport;

public class FinancialReportsModule {

    public static final double INVALID_VALUE = Double.MIN_VALUE;

    private static final int FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED = 2012;

    private JLabel reportsLabel;
    private JLabel yearLabel;
    private JComboBox<Integer> threeYearsBackYearComboBox;
    private JLabel twoYearsBackYearLabel;
    private JLabel lastYearLabel;

    private JLabel assetsSumLabel = generateLabel("Сума на актива:");
    private JFormattedTextField assetsSumThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField assetsSumTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField assetsSumLastYear = generateNumericField(0);

    private JLabel netProfitLabel = generateLabel("Нетна печалба/нетен финансов резултат:");
    private JFormattedTextField netProfitThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField netProfitTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField netProfitLastYear = generateNumericField(0);

    private JLabel equityLabel = generateLabel("Собствен капитал:");
    private JFormattedTextField equityThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField equityTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField equityLastYear = generateNumericField(0);

    private JLabel liabilitiesProvisionsLabel = generateLabel("Общо задължения и провизии - 06000+07000:");
    private JFormattedTextField liabilitiesProvisionsThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField liabilitiesProvisionsTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField liabilitiesProvisionsLastYear = generateNumericField(0);

    private JLabel netSalesIncomeLabel = generateLabel("Нетни приходи от продажби:");
    private JFormattedTextField netSalesThreeYearsAgo;
    private JFormattedTextField netSalesTwoYearsAgo;
    private JFormattedTextField netSalesLastYear;

    private JLabel incomeOperatingActivitiesLabel = generateLabel("Общи приходи от оперативна дейност:");
    private JFormattedTextField incomeOperatingActivitiesThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField incomeOperatingActivitiesTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField incomeOperatingActivitiesLastYear = generateNumericField(0);

    private JLabel outlayOperatingActivitiesLabel = generateLabel("Общо разходи за оперативна дейност:");
    private JFormattedTextField outlayOperatingActivitiesThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField outlayOperatingActivitiesTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField outlayOperatingActivitiesLastYear = generateNumericField(0);

    private JLabel amortizationLabel = generateLabel("Разходи за амортизации:");
    private JFormattedTextField amortizationThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField amortizationTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField amortizationLastYear = generateNumericField(0);

    private JLabel avgStaffNumberLabel = generateLabel("Средно списъчен брой на персонала:");
    private JFormattedTextField avgStaffNumberThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField avgStaffNumberTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField avgStaffNumberLastYear = generateNumericField(0);

    private JLabel investmentsEquipmentLabel = generateLabel("Реализирани инвестиции в машини и съоръжения:");
    private JFormattedTextField investmentsEquipmentThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField investmentsEquipmentTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField investmentsEquipmentLastYear = generateNumericField(0);

    private JLabel earningsExportTollingLabel = generateLabel("Приходи от износ и ишлеме:");
    private JFormattedTextField earningsExportTollingThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField earningsExportTollingTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField earningsExportTollingLastYear = generateNumericField(0);

    private Map<FinancialReportsModuleEventType, HashSet<FinancialReportsModuleEventListener>> eventTypeEventListenersPairs = new HashMap<FinancialReportsModuleEventType, HashSet<FinancialReportsModuleEventListener>>();

    public FinancialReportsModule() {
        reportsLabel = generateLabel("<html><body><center>Отчети</center>(в хил. лв)");
        reportsLabel.setHorizontalAlignment(JLabel.CENTER);
        yearLabel = generateLabel("Година:");

        twoYearsBackYearLabel = new JLabel(String.valueOf(FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 1));
        lastYearLabel = new JLabel(String.valueOf(FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 2));

        threeYearsBackYearComboBox = new JComboBox<Integer>(new Integer[] { FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED,
                FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 1, FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 2,
                FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 3, FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED + 4 });
        threeYearsBackYearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedYear = (int) threeYearsBackYearComboBox.getSelectedItem();
                twoYearsBackYearLabel.setText(String.valueOf(selectedYear + 1));
                lastYearLabel.setText(String.valueOf(selectedYear + 2));
            }
        });

        Runnable focusLostRunnable = new Runnable() {
            @Override
            public void run() {
                emitEvent(FinancialReportsModuleEventType.NET_SALES_CHANGED);
            }
        };
        netSalesThreeYearsAgo = generateNumericField(0, true, focusLostRunnable);
        netSalesTwoYearsAgo = generateNumericField(0, true, focusLostRunnable);
        netSalesLastYear = generateNumericField(0, true, focusLostRunnable);
    }

    public double getNetSalesLastYear() {
        double netSalesDouble = INVALID_VALUE;

        String netSalesStringValue = netSalesLastYear.getText();

        if (validateDouble(netSalesStringValue)) {
            netSalesDouble = Double.parseDouble(netSalesStringValue);
        }

        return netSalesDouble;
    }

    public double getNetSalesTwoYearsAgo() {
        double netSalesDouble = INVALID_VALUE;

        String netSalesStringValue = netSalesTwoYearsAgo.getText();

        if (validateDouble(netSalesStringValue)) {
            netSalesDouble = Double.parseDouble(netSalesStringValue);
        }

        return netSalesDouble;
    }

    public double getNetSalesThreeYearsAgo() {
        double netSalesDouble = INVALID_VALUE;

        String netSalesStringValue = netSalesThreeYearsAgo.getText();

        if (validateDouble(netSalesStringValue)) {
            netSalesDouble = Double.parseDouble(netSalesStringValue);
        }

        return netSalesDouble;
    }

    public void addToLayout(GridBagLayoutBuffer layoutBuffer) {
        layoutBuffer.add(reportsLabel, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(yearLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(threeYearsBackYearComboBox, COLUMN.DATA_ONE, 1, true);
        layoutBuffer.add(twoYearsBackYearLabel, COLUMN.DATA_TWO, 1, true);
        layoutBuffer.add(lastYearLabel, COLUMN.DATA_THREE, 1, true);

        layoutBuffer.add(assetsSumLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(assetsSumThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(assetsSumTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(assetsSumLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(netProfitLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(netProfitThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(netProfitTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(netProfitLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(equityLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(equityThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(equityTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(equityLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(liabilitiesProvisionsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(liabilitiesProvisionsThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(liabilitiesProvisionsTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(liabilitiesProvisionsLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(netSalesIncomeLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(netSalesThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(netSalesTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(netSalesLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(incomeOperatingActivitiesLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(incomeOperatingActivitiesThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(incomeOperatingActivitiesTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(incomeOperatingActivitiesLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(outlayOperatingActivitiesLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(outlayOperatingActivitiesThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(outlayOperatingActivitiesTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(outlayOperatingActivitiesLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(amortizationLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(amortizationThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(amortizationTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(amortizationLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(avgStaffNumberLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(avgStaffNumberThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(avgStaffNumberTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(avgStaffNumberLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(investmentsEquipmentLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(investmentsEquipmentThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(investmentsEquipmentTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(investmentsEquipmentLastYear, COLUMN.DATA_THREE, 1);

        layoutBuffer.add(earningsExportTollingLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(earningsExportTollingThreeYearsAgo, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(earningsExportTollingTwoYearsAgo, COLUMN.DATA_TWO, 1);
        layoutBuffer.add(earningsExportTollingLastYear, COLUMN.DATA_THREE, 1);
    }

    public FinancialReport getReportLastYear() {
        FinancialReport lastYearReport = new FinancialReport(Integer.parseInt(lastYearLabel.getText()));
        lastYearReport.setAssetsSum(Double.parseDouble(assetsSumLastYear.getText()));
        lastYearReport.setNetProfit(Double.parseDouble(netProfitLastYear.getText()));
        lastYearReport.setEquity(Double.parseDouble(equityLastYear.getText()));
        lastYearReport.setLiabilitiesProvisions(Double.parseDouble(liabilitiesProvisionsLastYear.getText()));
        lastYearReport.setNetSales(Double.parseDouble(netSalesLastYear.getText()));
        lastYearReport.setIncomeOperatingActivities(Double.parseDouble(incomeOperatingActivitiesLastYear.getText()));
        lastYearReport.setOutlayOperatingActivities(Double.parseDouble(outlayOperatingActivitiesLastYear.getText()));
        lastYearReport.setAmortization(Double.parseDouble(amortizationLastYear.getText()));
        lastYearReport.setAvgStaffNumber(Double.parseDouble(avgStaffNumberLastYear.getText()));
        lastYearReport.setInvestmentsEquipment(Double.parseDouble(investmentsEquipmentLastYear.getText()));
        lastYearReport.setEarningsExportTolling(Double.parseDouble(earningsExportTollingLastYear.getText()));
        return lastYearReport;
    }

    public FinancialReport getReportTwoYearsBack() {
        FinancialReport twoYearsBackReport = new FinancialReport(Integer.parseInt(twoYearsBackYearLabel
                .getText()));
        twoYearsBackReport.setAssetsSum(Double.parseDouble(assetsSumTwoYearsAgo.getText()));
        twoYearsBackReport.setNetProfit(Double.parseDouble(netProfitTwoYearsAgo.getText()));
        twoYearsBackReport.setEquity(Double.parseDouble(equityTwoYearsAgo.getText()));
        twoYearsBackReport.setLiabilitiesProvisions(Double.parseDouble(liabilitiesProvisionsTwoYearsAgo.getText()));
        twoYearsBackReport.setNetSales(Double.parseDouble(netSalesTwoYearsAgo.getText()));
        twoYearsBackReport.setIncomeOperatingActivities(Double.parseDouble(incomeOperatingActivitiesTwoYearsAgo
                .getText()));
        twoYearsBackReport.setOutlayOperatingActivities(Double.parseDouble(outlayOperatingActivitiesTwoYearsAgo
                .getText()));
        twoYearsBackReport.setAmortization(Double.parseDouble(amortizationTwoYearsAgo.getText()));
        twoYearsBackReport.setAvgStaffNumber(Double.parseDouble(avgStaffNumberTwoYearsAgo.getText()));
        twoYearsBackReport.setInvestmentsEquipment(Double.parseDouble(investmentsEquipmentTwoYearsAgo.getText()));
        twoYearsBackReport.setEarningsExportTolling(Double.parseDouble(earningsExportTollingTwoYearsAgo.getText()));
        return twoYearsBackReport;
    }

    public FinancialReport getReportThreeYearsBack() {
        FinancialReport threeYearsBackReport = new FinancialReport((int) threeYearsBackYearComboBox.getSelectedItem());
        threeYearsBackReport.setAssetsSum(Double.parseDouble(assetsSumThreeYearsAgo.getText()));
        threeYearsBackReport.setNetProfit(Double.parseDouble(netProfitThreeYearsAgo.getText()));
        threeYearsBackReport.setEquity(Double.parseDouble(equityThreeYearsAgo.getText()));
        threeYearsBackReport.setLiabilitiesProvisions(Double.parseDouble(liabilitiesProvisionsThreeYearsAgo.getText()));
        threeYearsBackReport.setNetSales(Double.parseDouble(netSalesThreeYearsAgo.getText()));
        threeYearsBackReport.setIncomeOperatingActivities(Double.parseDouble(incomeOperatingActivitiesThreeYearsAgo
                .getText()));
        threeYearsBackReport.setOutlayOperatingActivities(Double.parseDouble(outlayOperatingActivitiesThreeYearsAgo
                .getText()));
        threeYearsBackReport.setAmortization(Double.parseDouble(amortizationThreeYearsAgo.getText()));
        threeYearsBackReport.setAvgStaffNumber(Double.parseDouble(avgStaffNumberThreeYearsAgo.getText()));
        threeYearsBackReport.setInvestmentsEquipment(Double.parseDouble(investmentsEquipmentThreeYearsAgo.getText()));
        threeYearsBackReport.setEarningsExportTolling(Double.parseDouble(earningsExportTollingThreeYearsAgo.getText()));
        return threeYearsBackReport;
    }

    @SuppressWarnings({ "serial" })
    public void registerListener(FinancialReportsModuleEventListener listener, FinancialReportsModuleEventType eventType) {
       HashSet<FinancialReportsModuleEventListener> listenersSet = eventTypeEventListenersPairs.get(eventType);
       if (listenersSet == null) {
           eventTypeEventListenersPairs.put(eventType, new HashSet<FinancialReportsModuleEventListener>() {{
               add(listener);
           }});
       } else {
           listenersSet.add(listener);
       }
    }

    private void emitEvent(FinancialReportsModuleEventType eventType) {
        for (Map.Entry<FinancialReportsModuleEventType, HashSet<FinancialReportsModuleEventListener>> entry : eventTypeEventListenersPairs
                .entrySet()) {
            FinancialReportsModuleEventType type = entry.getKey();
            if (eventType == type) {
                HashSet<FinancialReportsModuleEventListener> listeners = entry.getValue();
                for (FinancialReportsModuleEventListener listener : listeners) {
                    listener.handleFinancialReportEvent(eventType);
                }
            }
        }
    }
}
