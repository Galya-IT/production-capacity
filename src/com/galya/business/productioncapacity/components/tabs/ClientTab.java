package com.galya.business.productioncapacity.components.tabs;

import static com.galya.business.productioncapacity.utils.ComponentFactory.generateLabel;
import static com.galya.business.productioncapacity.utils.Validator.MAX_LENGTH_SHORT_TEXTS;
import static com.galya.business.productioncapacity.utils.Validator.MIN_COMPANY_NAME_LENGTH;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import com.galya.business.productioncapacity.GridBagLayoutBuffer;
import com.galya.business.productioncapacity.components.AdministrativeAreaComboBox;
import com.galya.business.productioncapacity.components.EconomicActivityComboBox;
import com.galya.business.productioncapacity.components.FinancialReportsModule;
import com.galya.business.productioncapacity.components.ScrollablePanel;
import com.galya.business.productioncapacity.components.misc.DatePickerFactory;
import com.galya.business.productioncapacity.components.misc.FinancialReportsModuleEventListener;
import com.galya.business.productioncapacity.components.misc.FinancialReportsModuleEventType;
import com.galya.business.productioncapacity.model.Client;
import com.galya.business.productioncapacity.model.CompanyCategory;
import com.galya.business.productioncapacity.model.EconomicActivityClass;
import com.galya.business.productioncapacity.model.EconomicActivityDivision;
import com.galya.business.productioncapacity.model.EconomicActivityGroup;
import com.galya.business.productioncapacity.model.EconomicActivitySection;
import com.galya.business.productioncapacity.model.FinancialReport;
import com.galya.business.productioncapacity.persistence.AdministrativeRuralAreasTableHelper;
import com.galya.business.productioncapacity.persistence.ClientTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityClassTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityDivisionTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityGroupTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivitySectionTableHelper;
import com.galya.business.productioncapacity.persistence.FinancialReportTableHelper;
import com.galya.business.productioncapacity.utils.CommonUtils;
import com.galya.business.productioncapacity.utils.FileSystemUtils;
import com.galya.business.productioncapacity.utils.GuiUtils;
import com.galya.business.productioncapacity.utils.Validator;

public class ClientTab extends Tab implements FinancialReportsModuleEventListener {

    private static final String NEW_CLIENT_LABEL = "New Client";

    private static final String DEFAULT_CATEGORY_COMPANY_LABEL = "Въведете нетните приходи, за да се генерира";
    private static final String DEFAULT_MIN_AMOUNT_FUNDING_LABEL = "Необходимо е първо да се генерира категория";
    private static final String DEFAULT_MAX_AMOUNT_FUNDING_LABEL = "Необходимо е първо да се генерира категория";
    private static final String DEFAULT_MAX_PERCENTAGE_FUNDING_LABEL = "Необходимо е първо да се генерира категория и да се въведе район";

    private static final int TAB_LABEL_WIDTH = 100;

    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = true;

    private boolean isOldClient = false;
    private Client oldClient;

    private JPanel mainPanel;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel bulstatLabel;
    private JTextField bulstatField;

    private JLabel seatAddressLabel;
    private JTextField seatAddressField;

    private JLabel sectionLabel;
    private EconomicActivityComboBox<EconomicActivitySection> economicActivitySectionsComboBox;

    private JLabel divisionLabel;
    private EconomicActivityComboBox<EconomicActivityDivision> economicActivityDivisionsComboBox;

    private JLabel groupLabel;
    private EconomicActivityComboBox<EconomicActivityGroup> economicActivityGroupsComboBox;

    private JLabel classLabel;
    private EconomicActivityComboBox<EconomicActivityClass> economicActivityClassesComboBox;

    private JLabel regionLabel;
    private AdministrativeAreaComboBox regionComboBox;

    private JLabel districtLabel;
    private AdministrativeAreaComboBox districtComboBox;

    private JLabel municipalityLabel;
    private AdministrativeAreaComboBox municipalityComboBox;

    private JLabel pageSectionLabelForArea;
    private JLabel pageSectionLabelForEconomicClass;

    private JLabel investitionAddressLabel;
    private JTextField investitionAddressField;

    private JLabel datePickerLabel;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;

    private JLabel notesLabel;
    private JTextArea notesTextArea;
    private JScrollPane notesTextScrollPane;

    private JLabel othersLabel;

    private JLabel categoryCompanyLabel = generateLabel("Категория:");
    private JLabel categoryCompany = new JLabel(DEFAULT_CATEGORY_COMPANY_LABEL);

    private JLabel minAmountFundingLabel = generateLabel("Минимална сума на финансиране:");
    private JLabel minAmountFunding = new JLabel(DEFAULT_MIN_AMOUNT_FUNDING_LABEL);

    private JLabel maxAmountFundingLabel = generateLabel("Максимална сума на финансиране:");
    private JLabel maxAmountFunding = new JLabel(DEFAULT_MAX_AMOUNT_FUNDING_LABEL);

    private JLabel maxPercentageFundingLabel = generateLabel("Максимален процент на съфинансиране:");
    private JLabel maxPercentageFunding = new JLabel(DEFAULT_MAX_PERCENTAGE_FUNDING_LABEL);

    private JLabel wholeInvestitionAmountLabel = generateLabel("Размер на цялата инвестиция без ДДС:");
    private JTextField wholeInvestitionAmount = new JTextField("");

    private JLabel standardsLabel = generateLabel("Внедрени стандарти - ISO,  HACCP:");
    private JTextField standards = new JTextField("");

    private JLabel softwareSystemsLabel = generateLabel("Закупен софтуер/внедрени системи за управление:");
    private JTextField softwareSystems = new JTextField("");

    private JLabel otherCompaniesConnectionsLabel = generateLabel("Свързаност с други предприятия:");
    private JTextField otherCompaniesConnections = new JTextField("");

    private JLabel attachedFilesLabel = generateLabel("Файлове:");
    private JButton attachFileBtn = new JButton("Добави нов файл");

    private JButton saveButton;

    private GridBagLayoutBuffer layoutBuffer = new GridBagLayoutBuffer();

    private FinancialReportsModule financialReportsModule = new FinancialReportsModule();

    public interface COLUMN {
        public static final int LEFT_PADDING = 0;
        public static final int LABELS = 1;
        public static final int DATA_ONE = 2;
        public static final int DATA_TWO = 3;
        public static final int DATA_THREE = 4;
        public static final int RIGHT_PADDING = 5;
    }

    public ClientTab(JFrame parentFrame, Client oldClient) {
        super(parentFrame, (oldClient == null) ? NEW_CLIENT_LABEL : oldClient.getName(), TAB_LABEL_WIDTH, ICON,
                IS_CLOSEABLE);

        if (oldClient != null) {
            isOldClient = true;
            loadOldClient(oldClient);
        }

        mainPanel = new ScrollablePanel(false);
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);

        nameField = new JTextField();
        nameLabel = generateLabel("Име:", nameField);

        bulstatField = new JTextField();
        bulstatLabel = generateLabel("Булстат:", bulstatField);

        seatAddressField = new JTextField();
        seatAddressLabel = generateLabel("Седалище:", seatAddressField);

        setEconomicActivityComboBoxes();
        setAdministrativeAreaComboBoxes();

        pageSectionLabelForEconomicClass = generateLabel("НКИД");
        pageSectionLabelForEconomicClass.setHorizontalAlignment(JLabel.CENTER);

        sectionLabel = generateLabel("Сектор:", economicActivitySectionsComboBox);
        divisionLabel = generateLabel("Раздел:", economicActivityDivisionsComboBox);
        groupLabel = generateLabel("Група:", economicActivityGroupsComboBox);
        classLabel = generateLabel("Клас:", economicActivityClassesComboBox);

        pageSectionLabelForArea = generateLabel("Място на реализиране на инвестицията");
        pageSectionLabelForArea.setHorizontalAlignment(JLabel.CENTER);

        regionLabel = generateLabel("Регион:", regionComboBox);
        districtLabel = generateLabel("Област:", districtComboBox);
        municipalityLabel = generateLabel("Община:", municipalityComboBox);

        investitionAddressField = new JTextField("");
        investitionAddressLabel = generateLabel("Адрес:", investitionAddressField);

        categoryCompany.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                String minAmountFundingText;
                String maxAmountFundingText;

                String categoryText = categoryCompany.getText();

                CompanyCategory category = null;
                if (categoryText != DEFAULT_CATEGORY_COMPANY_LABEL) {
                    category = CompanyCategory.getByText(categoryText);
                    minAmountFundingText = String.valueOf(category.getMinFundingAmount());
                    maxAmountFundingText = String.valueOf(category.getMaxFundingAmount());
                } else {
                    minAmountFundingText = DEFAULT_MIN_AMOUNT_FUNDING_LABEL;
                    maxAmountFundingText = DEFAULT_MAX_AMOUNT_FUNDING_LABEL;
                }
                setMaxPercentageFunding(category);

                minAmountFunding.setText(minAmountFundingText);
                maxAmountFunding.setText(maxAmountFundingText);
            }
        });

        othersLabel = generateLabel("Други");
        othersLabel.setHorizontalAlignment(JLabel.CENTER);

        datePickerLabel = generateLabel("Срок за прием на документи:");
        startDatePicker = DatePickerFactory.generateDatePickerBg();
        startDatePicker.setPreferredSize(new Dimension(0, startDatePicker.getPreferredSize().height));
        startDatePicker.setMinimumSize(new Dimension(0, startDatePicker.getPreferredSize().height));
        endDatePicker = DatePickerFactory.generateDatePickerBg();
        endDatePicker.setPreferredSize(new Dimension(0, endDatePicker.getPreferredSize().height));
        endDatePicker.setMinimumSize(new Dimension(0, endDatePicker.getPreferredSize().height));

        setNotesRow();

        saveButton = new JButton("Запази");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                save();
            }
        });

        addAllComponentsToLayout();

        attachFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int choice = chooser.showOpenDialog(null);
                if (choice != JFileChooser.APPROVE_OPTION)
                    return;
                File chosenFile = chooser.getSelectedFile();

                String fileExt = FileSystemUtils.getExt(chosenFile);
                String fileName = FileSystemUtils.getName(chosenFile);

                /* newFilePath =
                 * if(OldClient) "data/client" + client.getId() + "/" + fileName + "." + fileExt
                 * if(NewClient) "data/temp" (get as const from Constants) + " / " + UniqueFileNameGenerator.generate() + fileExt
                 
                 private Map<String, String> filesToBeCopiedAfterSuccessfulDbSave .add(newFilePath, oldFileName)
                 persisting after db returns an id for saved client*/

                // File newFile = new File(newFilePath);
                // isFileOk = true;

                /* if (OldClient && newFile.exists()) override prompt (if no -> isFileOk = false) */

                // if (isFileOk) Files.copy(chosenFile, newFile, REPLACE_EXISTING);
            }
        });

        financialReportsModule.registerListener(this, FinancialReportsModuleEventType.NET_SALES_CHANGED);
        setBaseComponent(mainPanel);
    }

    @Override
    public void handleFinancialReportEvent(FinancialReportsModuleEventType eventType) {
        if (eventType == FinancialReportsModuleEventType.NET_SALES_CHANGED) {

            double netSalesThreeYearsAgoDouble = financialReportsModule.getNetSalesThreeYearsAgo();
            double netSalesTwoYearsAgoDouble = financialReportsModule.getNetSalesTwoYearsAgo();
            double netSalesLastYearDouble = financialReportsModule.getNetSalesLastYear();

            // TODO: if (all 3 != 0) ???
            if (netSalesThreeYearsAgoDouble != FinancialReportsModule.INVALID_VALUE
                    && netSalesTwoYearsAgoDouble != FinancialReportsModule.INVALID_VALUE
                    && netSalesLastYearDouble != FinancialReportsModule.INVALID_VALUE) {

                double netSalesThreeYears = netSalesThreeYearsAgoDouble + netSalesTwoYearsAgoDouble
                        + netSalesLastYearDouble;

                CompanyCategory category = null;
                if (netSalesThreeYears > 0) {
                    category = CompanyCategory.getByNetSales(netSalesThreeYears);
                } else {
                    categoryCompany.setText(DEFAULT_CATEGORY_COMPANY_LABEL);
                }

                if (category != null) {
                    categoryCompany.setText(category.toString());
                }
            }
        }
    }

    private void setMaxPercentageFunding(String region) {
        CompanyCategory category = CompanyCategory.getByText(categoryCompany.getText());
        setMaxPercentageFunding(category, region);
    }

    private void setMaxPercentageFunding(CompanyCategory category) {
        String region = regionComboBox.getSelectedItem();
        setMaxPercentageFunding(category, region);
    }

    private void setMaxPercentageFunding(CompanyCategory category, String region) {
        String maxPercentageFundingString = DEFAULT_MAX_PERCENTAGE_FUNDING_LABEL;

        if (category != null && region != null && region != regionComboBox.getDefaultOption()) {
            // TODO: REFACTOR category checking
            boolean isSouthWesternRegion = "Югозападен".equals(region);
            int maxPercentageFundingInt = category.getMaxPercentageFunding(isSouthWesternRegion);
            maxPercentageFundingString = String.valueOf(maxPercentageFundingInt) + "%";
        }

        maxPercentageFunding.setText(maxPercentageFundingString);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setEconomicActivityComboBoxes() {
        economicActivityClassesComboBox = new EconomicActivityComboBox(EconomicActivityClass.class, null);

        economicActivityGroupsComboBox = new EconomicActivityComboBox(EconomicActivityGroup.class, null);
        economicActivityGroupsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedGroupId = economicActivityGroupsComboBox.getSelectedItemId();
                List<EconomicActivityClass> economicActivityGroups = EconomicActivityClassTableHelper.getInstance()
                        .getByGroupId(selectedGroupId);
                economicActivityClassesComboBox.setData(economicActivityGroups);
            }
        });

        economicActivityDivisionsComboBox = new EconomicActivityComboBox(EconomicActivityDivision.class, null);
        economicActivityDivisionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedDivisionId = economicActivityDivisionsComboBox.getSelectedItemId();
                List<EconomicActivityGroup> economicActivityGroups = EconomicActivityGroupTableHelper.getInstance()
                        .getByDivisionId(selectedDivisionId);
                economicActivityGroupsComboBox.setData(economicActivityGroups);
            }
        });

        List<EconomicActivitySection> economicActivitySections = EconomicActivitySectionTableHelper.getInstance()
                .getAll();
        economicActivitySectionsComboBox = new EconomicActivityComboBox(EconomicActivitySection.class,
                economicActivitySections);
        economicActivitySectionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedSectionId = economicActivitySectionsComboBox.getSelectedItemId();
                List<EconomicActivityDivision> economicActivityDivisions = EconomicActivityDivisionTableHelper
                        .getInstance().getBySectionId(selectedSectionId);
                economicActivityDivisionsComboBox.setData(economicActivityDivisions);
            }
        });
    }

    private void setAdministrativeAreaComboBoxes() {
        municipalityComboBox = new AdministrativeAreaComboBox(null);

        districtComboBox = new AdministrativeAreaComboBox(null);
        districtComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedDistrict = districtComboBox.getSelectedItem();
                List<String> municipalities = AdministrativeRuralAreasTableHelper.getInstance()
                        .getAllMunicipalitiesByDistrict(selectedDistrict);
                municipalityComboBox.setData(municipalities);
            }
        });

        List<String> regions = AdministrativeRuralAreasTableHelper.getInstance().getAllRegions();
        regionComboBox = new AdministrativeAreaComboBox(regions);
        regionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedRegion = regionComboBox.getSelectedItem();
                List<String> districts = AdministrativeRuralAreasTableHelper.getInstance().getAllDistrictsByRegion(
                        selectedRegion);
                districtComboBox.setData(districts);
                setMaxPercentageFunding(selectedRegion);
            }
        });
    }

    private void setNotesRow() {
        notesTextArea = new JTextArea();
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);

        notesTextScrollPane = new JScrollPane(notesTextArea);
        int notesTextScrollPaneMinHeight = (int) (GuiUtils.getScreenDimension().height / 15);
        Dimension notesTextScrollPaneDimension = new Dimension(notesTextScrollPane.getSize().width,
                notesTextScrollPaneMinHeight);
        notesTextScrollPane.setPreferredSize(notesTextScrollPaneDimension);
        notesTextScrollPane.setMaximumSize(notesTextScrollPaneDimension);

        notesLabel = generateLabel("Бележки:", notesTextScrollPane);
    }

    private void addAllComponentsToLayout() {
        layoutBuffer.add(new JLabel(), COLUMN.LEFT_PADDING, 1, false, 10, 0);
        layoutBuffer.add(new JLabel(), COLUMN.LABELS, 1, false, 15, 0);
        layoutBuffer.add(new JLabel(), COLUMN.DATA_ONE, 1, false, 19, 0);
        layoutBuffer.add(new JLabel(), COLUMN.DATA_TWO, 1, false, 19, 0);
        layoutBuffer.add(new JLabel(), COLUMN.DATA_THREE, 1, false, 19, 0);
        layoutBuffer.add(new JLabel(), COLUMN.RIGHT_PADDING, 1, false, 18, 0);

        layoutBuffer.add(nameLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(nameField, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(bulstatLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(bulstatField, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(seatAddressLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(seatAddressField, COLUMN.DATA_ONE, 3);

        /* -------- Economic Activity --------- */
        layoutBuffer.add(pageSectionLabelForEconomicClass, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(sectionLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(economicActivitySectionsComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(divisionLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(economicActivityDivisionsComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(groupLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(economicActivityGroupsComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(classLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(economicActivityClassesComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(pageSectionLabelForArea, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(regionLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(regionComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(districtLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(districtComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(municipalityLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(municipalityComboBox, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(investitionAddressLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(investitionAddressField, COLUMN.DATA_ONE, 3);

        /* -------- Reports --------- */

        financialReportsModule.addToLayout(layoutBuffer);

        /* -------- Other --------- */

        layoutBuffer.add(othersLabel, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(datePickerLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(startDatePicker, COLUMN.DATA_ONE, 1);
        layoutBuffer.add(endDatePicker, COLUMN.DATA_TWO, 1);

        layoutBuffer.add(notesLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(notesTextScrollPane, COLUMN.DATA_ONE, 3, false, 0, 1);

        layoutBuffer.add(categoryCompanyLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(categoryCompany, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(minAmountFundingLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(minAmountFunding, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(maxAmountFundingLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(maxAmountFunding, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(maxPercentageFundingLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(maxPercentageFunding, COLUMN.DATA_ONE, 3);

        layoutBuffer.add(wholeInvestitionAmountLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(wholeInvestitionAmount, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(standardsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(standards, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(softwareSystemsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(softwareSystems, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(otherCompaniesConnectionsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(otherCompaniesConnections, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(attachedFilesLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(attachFileBtn, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(saveButton, COLUMN.DATA_ONE, 3, true);

        layoutBuffer.execute(mainPanel);
    }

    private void save() {
        String companyName = nameField.getText();
        if (handleTooLongShortTextError(companyName, nameLabel.getText())) {
            return;
        } else if (companyName.length() < MIN_COMPANY_NAME_LENGTH) {
            JOptionPane.showMessageDialog(null, "Името на фирмата е твърде кратко. Минималната дължина е "
                    + MIN_COMPANY_NAME_LENGTH + " символа.", "Име на фирмата", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bulstat = bulstatField.getText();
        if (!CommonUtils.isEmpty(bulstat) && !Validator.validateBulstat(bulstat)) {
            JOptionPane.showMessageDialog(null, "Невалиден булстат.", "Булстат", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String seatAddress = seatAddressField.getText();
        if (handleTooLongShortTextError(seatAddress, seatAddressField.getText())) {
            return;
        }

        EconomicActivitySection economicActivitySection = (EconomicActivitySection) economicActivitySectionsComboBox
                .getModel().getSelectedItem();
        EconomicActivityDivision economicActivityDivision = (EconomicActivityDivision) economicActivityDivisionsComboBox
                .getModel().getSelectedItem();
        EconomicActivityGroup economicActivityGroup = (EconomicActivityGroup) economicActivityGroupsComboBox.getModel()
                .getSelectedItem();
        EconomicActivityClass economicActivityClass = (EconomicActivityClass) economicActivityClassesComboBox
                .getModel().getSelectedItem();

        Date firstDocumentsReceptionDate = (Date) startDatePicker.getModel().getValue();
        Date lastDocumentsReceptionDate = (Date) endDatePicker.getModel().getValue();

        if (firstDocumentsReceptionDate != null && lastDocumentsReceptionDate != null
                && (firstDocumentsReceptionDate.getTime() > lastDocumentsReceptionDate.getTime())) {
            JOptionPane.showMessageDialog(null, "Началната дата не може да бъде след крайната дата.",
                    "Период за подаване на документи", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String investmentRegion = regionComboBox.getSelectedItemIfDifferentFromDefault();
        String investmentDistrict = districtComboBox.getSelectedItemIfDifferentFromDefault();
        String investmentMunicipality = municipalityComboBox.getSelectedItemIfDifferentFromDefault();

        String investmentAddress = investitionAddressField.getText();
        if (handleTooLongShortTextError(investmentAddress, investitionAddressLabel.getText())) {
            return;
        }

        String notes = notesTextArea.getText();
        CompanyCategory category = CompanyCategory.getByText(categoryCompany.getText());

        String wholeInvestitionAmountString = wholeInvestitionAmount.getText();
        String standardsString = standards.getText();
        String softwareSystemsString = softwareSystems.getText();
        String otherCompaniesConnectionsString = otherCompaniesConnections.getText();
        if (handleTooLongShortTextError(wholeInvestitionAmountString, wholeInvestitionAmountLabel.getText())
                || handleTooLongShortTextError(standardsString, standardsLabel.getText())
                || handleTooLongShortTextError(softwareSystemsString, softwareSystemsLabel.getText())
                || handleTooLongShortTextError(otherCompaniesConnectionsString,
                        otherCompaniesConnectionsLabel.getText())) {
            return;
        }

        FinancialReportTableHelper financialReportTableHelper = FinancialReportTableHelper.getInstance();

        FinancialReport lastYearReport = financialReportsModule.getReportLastYear();
        FinancialReport twoYearsBackReport = financialReportsModule.getReportTwoYearsBack();
        FinancialReport threeYearsBackReport = financialReportsModule.getReportThreeYearsBack();
        
        if (!isOldClient) {
            Client newClient = new Client(companyName, bulstat, seatAddress, economicActivitySection,
                    economicActivityDivision, economicActivityGroup, economicActivityClass, investmentRegion,
                    investmentDistrict, investmentMunicipality, investmentAddress, firstDocumentsReceptionDate,
                    lastDocumentsReceptionDate, notes, category, wholeInvestitionAmountString, standardsString,
                    softwareSystemsString, otherCompaniesConnectionsString);

            long clientId = ClientTableHelper.getInstance().addClient(newClient);
            newClient.setId(clientId);
            
            long lastYearReportId = financialReportTableHelper.addReport(clientId, lastYearReport);
            lastYearReport.setId(lastYearReportId);
            newClient.setLastYearReport(lastYearReport);
            
            long twoYearsBackReportId = financialReportTableHelper.addReport(clientId, twoYearsBackReport);
            twoYearsBackReport.setId(twoYearsBackReportId);
            newClient.setTwoYearsBackReport(twoYearsBackReport);
            
            long threeYearsBackReportId = financialReportTableHelper.addReport(clientId, threeYearsBackReport);
            threeYearsBackReport.setId(threeYearsBackReportId);
            newClient.setThreeYearsBackReport(threeYearsBackReport);
            
            oldClient = newClient;
            isOldClient = true;
            setLabel(oldClient.getName());
        } else {
            if (!companyName.equals(oldClient.getName())) {
                oldClient.setName(companyName);
                setLabel(companyName);
            }
            oldClient.setBulstat(bulstat);
            oldClient.setSeatAddress(seatAddress);
            oldClient.setEconomicActivitySection(economicActivitySection);
            oldClient.setEconomicActivityDivision(economicActivityDivision);
            oldClient.setEconomicActivityGroup(economicActivityGroup);
            oldClient.setEconomicActivityClass(economicActivityClass);
            oldClient.setInvestmentRegion(investmentRegion);
            oldClient.setInvestmentDistrict(investmentDistrict);
            oldClient.setInvestmentMunicipality(investmentMunicipality);
            oldClient.setInvestmentAddress(investmentAddress);
            oldClient.setFirstDocumentsReceptionDate(firstDocumentsReceptionDate);
            oldClient.setLastDocumentsReceptionDate(lastDocumentsReceptionDate);
            oldClient.setNotes(notes);
            oldClient.setCategory(category);
            oldClient.setWholeInvestitionAmount(wholeInvestitionAmountString);
            oldClient.setStandards(standardsString);
            oldClient.setSoftwareSystems(softwareSystemsString);
            oldClient.setOtherCompaniesConnections(otherCompaniesConnectionsString);

            lastYearReport.setId(oldClient.getLastYearReport().getId());
            twoYearsBackReport.setId(oldClient.getTwoYearsBackReport().getId());
            threeYearsBackReport.setId(oldClient.getThreeYearsBackReport().getId());
            
            oldClient.setLastYearReport(lastYearReport);
            oldClient.setTwoYearsBackReport(twoYearsBackReport);
            oldClient.setThreeYearsBackReport(threeYearsBackReport);
            
            ClientTableHelper.getInstance().updateClient(oldClient);
            financialReportTableHelper.updateReport(oldClient.getId(), lastYearReport);
            financialReportTableHelper.updateReport(oldClient.getId(), twoYearsBackReport);
            financialReportTableHelper.updateReport(oldClient.getId(), threeYearsBackReport);
        }

        /*Set<File> files = new HashSet<File>();
        currentClient.setFiles(files);*/
    }

    private boolean handleTooLongShortTextError(String text, String fieldLabel) {
        boolean isError = false;
        if (text.length() > MAX_LENGTH_SHORT_TEXTS) {
            JOptionPane.showMessageDialog(null, "Максимална дължина на полето " + fieldLabel + " е "
                    + MAX_LENGTH_SHORT_TEXTS + " символа.", fieldLabel, JOptionPane.ERROR_MESSAGE);
            isError = true;
        }
        return isError;
    }

    private void loadOldClient(Client client) {

    }
}
