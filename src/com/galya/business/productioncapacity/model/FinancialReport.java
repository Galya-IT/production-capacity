package com.galya.business.productioncapacity.model;

public class FinancialReport {

    private long companyId;
    private int year;
    private double assetsSum;
    private double netProfit;
    private double equity;
    private double liabilitiesProvisions;
    private double netSales;
    private double incomeOperatingActivities;
    private double outlayOperatingActivities;
    private double amortization;
    private double avgStaffNumber;
    private double investmentsEquipment;
    private double earningsExportTolling;
    
    public FinancialReport (long companyId, int year) {
        this.companyId = companyId;
        this.year = year;
    }
    
    public FinancialReport (long companyId, int year, double assetsSum, double netProfit, double equity, double liabilitiesProvisions, double netSales, double incomeOperatingActivities, double outlayOperatingActivities, double amortization, double avgStaffNumber, double investmentsEquipment, double earningsExportTolling) {
        this(companyId, year);
        this.assetsSum = assetsSum;
        this.netProfit = netProfit;
        this.equity = equity;
        this.liabilitiesProvisions = liabilitiesProvisions;
        this.netSales = netSales;
        this.incomeOperatingActivities = incomeOperatingActivities;
        this.outlayOperatingActivities = outlayOperatingActivities;
        this.amortization = amortization;
        this.avgStaffNumber = avgStaffNumber;
        this.investmentsEquipment = investmentsEquipment;
        this.earningsExportTolling = earningsExportTolling;
    }
    
    public long getCompanyId() {
        return companyId;
    }

    public int getYear() {
        return year;
    }

    public double getAssetsSum() {
        return assetsSum;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getEquity() {
        return equity;
    }

    public double getLiabilitiesProvisions() {
        return liabilitiesProvisions;
    }

    public double getNetSales() {
        return netSales;
    }

    public double getIncomeOperatingActivities() {
        return incomeOperatingActivities;
    }

    public double getOutlayOperatingActivities() {
        return outlayOperatingActivities;
    }

    public double getAmortization() {
        return amortization;
    }

    public double getAvgStaffNumber() {
        return avgStaffNumber;
    }

    public double getInvestmentsEquipment() {
        return investmentsEquipment;
    }

    public double getEarningsExportTolling() {
        return earningsExportTolling;
    }

    public void setAssetsSum(double assetsSum) {
        this.assetsSum = assetsSum;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }

    public void setLiabilitiesProvisions(double liabilitiesProvisions) {
        this.liabilitiesProvisions = liabilitiesProvisions;
    }

    public void setNetSales(double netSales) {
        this.netSales = netSales;
    }

    public void setIncomeOperatingActivities(double incomeOperatingActivities) {
        this.incomeOperatingActivities = incomeOperatingActivities;
    }

    public void setOutlayOperatingActivities(double outlayOperatingActivities) {
        this.outlayOperatingActivities = outlayOperatingActivities;
    }

    public void setAmortization(double amortization) {
        this.amortization = amortization;
    }

    public void setAvgStaffNumber(double avgStaffNumber) {
        this.avgStaffNumber = avgStaffNumber;
    }

    public void setInvestmentsEquipment(double investmentsEquipment) {
        this.investmentsEquipment = investmentsEquipment;
    }

    public void setEarningsExportTolling(double earningsExportTolling) {
        this.earningsExportTolling = earningsExportTolling;
    }

}
