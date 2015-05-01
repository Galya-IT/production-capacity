package com.galya.business.productioncapacity.model;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private int id;
    private String name;
    private String bulstat;
    private String seatAddress;

    private EconomicActivitySection economicActivitySection;
    private EconomicActivityDivision economicActivityDivision;
    private EconomicActivityGroup economicActivityGroup;
    private EconomicActivityClass economicActivityClass;

    private String investmentRegion;
    private String investmentDistrict;
    private String investmentMunicipality;
    private String investmentAddress;

    private FinancialReport lastYearReport;
    private FinancialReport twoYearsBackReport;
    private FinancialReport threeYearsBackReport;

    private Date firstDocumentsReceptionDate;
    private Date lastDocumentsReceptionDate;

    private String notes;

    private CompanyCategory category;
    private int minAmountFunding;
    private int maxAmountFunding;
    private int maxPercentageFunding;

    private String wholeInvestitionAmount;
    private String standards;
    private String softwareSystems;
    private String otherCompaniesConnections;

    private Set<File> files = new HashSet<File>();

    public Client(String name) {
    }
    
    public Client(String name, String bulstat, String seatAddress, EconomicActivitySection economicActivitySection,
            EconomicActivityDivision economicActivityDivision, EconomicActivityGroup economicActivityGroup,
            EconomicActivityClass economicActivityClass, String investmentRegion, String investmentDistrict,
            String investmentMunicipality, String investmentAddress, Date firstDocumentsReceptionDate,
            Date lastDocumentsReceptionDate, String notes, CompanyCategory category, String wholeInvestitionAmount,
            String standards, String softwareSystems, String otherCompaniesConnections) {
        this.name = name;
        this.bulstat = bulstat;
        this.seatAddress = seatAddress;
        this.economicActivitySection = economicActivitySection;
        this.economicActivityDivision = economicActivityDivision;
        this.economicActivityGroup = economicActivityGroup;
        this.economicActivityClass = economicActivityClass;
        this.investmentRegion = investmentRegion;
        this.investmentDistrict = investmentDistrict;
        this.investmentMunicipality = investmentMunicipality;
        this.investmentAddress = investmentAddress;
        this.firstDocumentsReceptionDate = firstDocumentsReceptionDate;
        this.lastDocumentsReceptionDate = lastDocumentsReceptionDate;
        this.notes = notes;
        this.category = category;
        this.wholeInvestitionAmount = wholeInvestitionAmount;
        this.standards = standards;
        this.softwareSystems = softwareSystems;
        this.otherCompaniesConnections = otherCompaniesConnections;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBulstat() {
        return bulstat;
    }

    public String getSeatAddress() {
        return seatAddress;
    }

    public EconomicActivitySection getEconomicActivitySection() {
        return economicActivitySection;
    }

    public EconomicActivityDivision getEconomicActivityDivision() {
        return economicActivityDivision;
    }

    public EconomicActivityGroup getEconomicActivityGroup() {
        return economicActivityGroup;
    }

    public EconomicActivityClass getEconomicActivityClass() {
        return economicActivityClass;
    }

    public String getInvestmentRegion() {
        return investmentRegion;
    }

    public String getInvestmentDistrict() {
        return investmentDistrict;
    }

    public String getInvestmentMunicipality() {
        return investmentMunicipality;
    }

    public String getInvestmentAddress() {
        return investmentAddress;
    }

    public FinancialReport getLastYearReport() {
        return lastYearReport;
    }

    public FinancialReport getTwoYearsBackReport() {
        return twoYearsBackReport;
    }

    public FinancialReport getThreeYearsBackReport() {
        return threeYearsBackReport;
    }

    public Date getFirstDocumentsReceptionDate() {
        return firstDocumentsReceptionDate;
    }

    public Date getLastDocumentsReceptionDate() {
        return lastDocumentsReceptionDate;
    }

    public String getNotes() {
        return notes;
    }

    public CompanyCategory getCategory() {
        return category;
    }

    public int getMinAmountFunding() {
        return minAmountFunding;
    }

    public int getMaxAmountFunding() {
        return maxAmountFunding;
    }

    public int getMaxPercentageFunding() {
        return maxPercentageFunding;
    }

    public String getWholeInvestitionAmount() {
        return wholeInvestitionAmount;
    }

    public String getStandards() {
        return standards;
    }

    public String getSoftwareSystems() {
        return softwareSystems;
    }

    public String getOtherCompaniesConnections() {
        return otherCompaniesConnections;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    public void setSeatAddress(String seatAddress) {
        this.seatAddress = seatAddress;
    }

    public void setEconomicActivitySection(EconomicActivitySection economicActivitySection) {
        this.economicActivitySection = economicActivitySection;
    }

    public void setEconomicActivityDivision(EconomicActivityDivision economicActivityDivision) {
        this.economicActivityDivision = economicActivityDivision;
    }

    public void setEconomicActivityGroup(EconomicActivityGroup economicActivityGroup) {
        this.economicActivityGroup = economicActivityGroup;
    }

    public void setEconomicActivityClass(EconomicActivityClass economicActivityClass) {
        this.economicActivityClass = economicActivityClass;
    }

    public void setInvestmentRegion(String investmentRegion) {
        this.investmentRegion = investmentRegion;
    }

    public void setInvestmentDistrict(String investmentDistrict) {
        this.investmentDistrict = investmentDistrict;
    }

    public void setInvestmentMunicipality(String investmentMunicipality) {
        this.investmentMunicipality = investmentMunicipality;
    }

    public void setInvestmentAddress(String investmentAddress) {
        this.investmentAddress = investmentAddress;
    }

    public void setLastYearReport(FinancialReport lastYearReport) {
        this.lastYearReport = lastYearReport;
    }

    public void setTwoYearsBackReport(FinancialReport twoYearsBackReport) {
        this.twoYearsBackReport = twoYearsBackReport;
    }

    public void setThreeYearsBackReport(FinancialReport threeYearsBackReport) {
        this.threeYearsBackReport = threeYearsBackReport;
    }

    public void setFirstDocumentsReceptionDate(Date firstDocumentsReceptionDate) {
        this.firstDocumentsReceptionDate = firstDocumentsReceptionDate;
    }

    public void setLastDocumentsReceptionDate(Date lastDocumentsReceptionDate) {
        this.lastDocumentsReceptionDate = lastDocumentsReceptionDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCategory(CompanyCategory category) {
        this.category = category;
    }

    public void setMinAmountFunding(int minAmountFunding) {
        this.minAmountFunding = minAmountFunding;
    }

    public void setMaxAmountFunding(int maxAmountFunding) {
        this.maxAmountFunding = maxAmountFunding;
    }

    public void setMaxPercentageFunding(int maxPercentageFunding) {
        this.maxPercentageFunding = maxPercentageFunding;
    }

    public void setWholeInvestitionAmount(String wholeInvestitionAmount) {
        this.wholeInvestitionAmount = wholeInvestitionAmount;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public void setSoftwareSystems(String softwareSystems) {
        this.softwareSystems = softwareSystems;
    }

    public void setOtherCompaniesConnections(String otherCompaniesConnections) {
        this.otherCompaniesConnections = otherCompaniesConnections;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void removeFile(File file) {
        this.files.remove(file);
    }
}
