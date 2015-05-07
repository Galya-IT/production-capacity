package com.galya.business.productioncapacity.model;

/**
 * Currency is BGN.
 */
public enum CompanyCategory {
    BELOW_MICRO(0, "Под микро", 0, 199999, 0, 0),
    MICRO(1, "Микро", 200000, 999999, 100000, 500000),
    SMALL(2, "Малко", 1000000, 5999999, 200000, 750000),
    AVERAGE(3, "Средно", 6000000, Integer.MAX_VALUE, 300000, 1000000);

    private String stringRepresenatation;
    private int id;
    private int lowerLimit;
    private int upperLimit;
    private int minFundingAmount;
    private int maxFundingAmount;

    public static CompanyCategory getByText(String stringRepresenatation) {
        CompanyCategory companyCategory = null;
        switch (stringRepresenatation) {
            case ("Под микро"):
                companyCategory = BELOW_MICRO;
                break;
            case ("Микро"):
                companyCategory = MICRO;
                break;
            case ("Малко"):
                companyCategory = SMALL;
                break;
            case ("Средно"):
                companyCategory = AVERAGE;
                break;
        }
        return companyCategory;
    }

    public static CompanyCategory getById(int id) {
        CompanyCategory category = null;
        switch (id) {
            case 0:
                category = BELOW_MICRO;
                break;
            case 1:
                category = MICRO;
                break;
            case 2:
                category = SMALL;
                break;
            case 3:
                category = AVERAGE;
                break;
        }
        return category;
    }

    public static CompanyCategory getByNetSales(double netSalesThreeYearsAgo, double netSalesTwoYearsAgo,
            double netSalesLastYear) {
        double netSalesLastThreeYears = netSalesThreeYearsAgo + netSalesTwoYearsAgo + netSalesLastYear;
        return getByNetSales(netSalesLastThreeYears);
    }

    public static CompanyCategory getByNetSales(double netSalesLastThreeYears) {
        CompanyCategory category = null;

        if (netSalesLastThreeYears > SMALL.getUpperLimit()) {
            category = AVERAGE;
        } else if (netSalesLastThreeYears > MICRO.getUpperLimit()) {
            category = SMALL;
        } else if (netSalesLastThreeYears > BELOW_MICRO.getUpperLimit()) {
            category = MICRO;
        } else {
            category = BELOW_MICRO;
        }

        return category;
    }

    private CompanyCategory(int id, String stringRepresenatation, int lowerLimit, int upperLimit, int minFundingAmount,
            int maxFundingAmount) {
        this.stringRepresenatation = stringRepresenatation;
        this.id = id;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.minFundingAmount = minFundingAmount;
        this.maxFundingAmount = maxFundingAmount;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public int getMinFundingAmount() {
        return minFundingAmount;
    }

    public int getMaxFundingAmount() {
        return maxFundingAmount;
    }

    public int getId() {
        return this.id;
    }

    public int getMaxPercentageFunding(boolean isSouthWesternRegion) {
        int maxPercentageFunding = 0;

        switch (this) {
            case MICRO:
            case SMALL:
                maxPercentageFunding = (isSouthWesternRegion) ? 45 : 70;
                break;
            case AVERAGE:
                maxPercentageFunding = (isSouthWesternRegion) ? 35 : 60;
                break;
            default:
                maxPercentageFunding = 0;
        }

        return maxPercentageFunding;
    }

    @Override
    public String toString() {
        return this.stringRepresenatation;
    }
}
