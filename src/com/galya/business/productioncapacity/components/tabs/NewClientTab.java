package com.galya.business.productioncapacity.components.tabs;

import static com.galya.business.productioncapacity.utils.ComponentFactory.generateLabel;
import static com.galya.business.productioncapacity.utils.ComponentFactory.generateNumericField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import com.galya.business.productioncapacity.components.AdministrativeAreaComboBox;
import com.galya.business.productioncapacity.components.DatePickerFactory;
import com.galya.business.productioncapacity.components.EconomicActivityComboBox;
import com.galya.business.productioncapacity.components.ScrollablePanel;
import com.galya.business.productioncapacity.model.EconomicActivityClass;
import com.galya.business.productioncapacity.model.EconomicActivityDivision;
import com.galya.business.productioncapacity.model.EconomicActivityGroup;
import com.galya.business.productioncapacity.model.EconomicActivitySection;
import com.galya.business.productioncapacity.persistence.AdministrativeRuralAreasTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityClassTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityDivisionTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityGroupTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivitySectionTableHelper;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class NewClientTab extends Tab {

    private static final String LABEL = "New Client";

    private static final int TAB_LABEL_WIDTH = 100;

    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = true;

    private static final int FIRST_YEAR_ANNUAL_FINANCE_REPORT_REQUIRED = 2012;

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

    private JLabel investitionLocationLabel;
    private JTextField investitionLocationField;

    private JLabel datePickerLabel;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;

    private JLabel notesLabel;
    private JScrollPane notesTextScrollPane;

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
    private JFormattedTextField netSalesThreeYearsAgo = generateNumericField(0);
    private JFormattedTextField netSalesTwoYearsAgo = generateNumericField(0);
    private JFormattedTextField netSalesLastYear = generateNumericField(0);

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

    private JLabel othersLabel;

    private JLabel categoryCompanyLabel = generateLabel("Категория:");
    private JLabel categoryCompany = new JLabel("Въведете нетните приходи, за да се генерира");

    private JLabel minAmountFundingLabel = generateLabel("Минимална сума на финансиране:");
    private JLabel minAmountFunding = new JLabel("Необходимо е първо да се генерира категория");

    private JLabel maxAmountFundingLabel = generateLabel("Максимална сума на финансиране:");
    private JLabel maxAmountFunding = new JLabel("Необходимо е първо да се генерира категория");

    private JLabel maxPercentageFundingLabel = generateLabel("Максимален процент на съфинансиране:");
    private JLabel maxPercentageFunding = new JLabel("Необходимо е първо да се генерира категория и да се въведе район");

    private JLabel wholeInvestitionAmountLabel = generateLabel("Размер на цялата инвестиция без ДДС:");
    private JTextField wholeInvestitionAmount = new JTextField("");

    private JLabel standartsLabel = generateLabel("Внедрени стандарти - ISO,  HACCP:");
    private JTextField standarts = new JTextField("");

    private JLabel softwareSystemsLabel = generateLabel("Закупен софтуер/внедрени системи за управление:");
    private JTextField softwareSystems = new JTextField("");

    private JLabel otherCompaniesConnectionsLabel = generateLabel("Свързаност с други предприятия:");
    private JTextField otherCompaniesConnections = new JTextField("");

    private JLabel attachedFilesLabel = generateLabel("Файлове:");
    private JButton attachFileBtn = new JButton("Добави нов файл");

    private JButton saveButton;
    
    private GridBagLayoutBuffer layoutBuffer = new GridBagLayoutBuffer();

    public NewClientTab(JFrame parentFrame) {
        super(parentFrame, LABEL, TAB_LABEL_WIDTH, ICON, IS_CLOSEABLE);

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

        investitionLocationField = new JTextField("");
        investitionLocationLabel = generateLabel("Адрес:", investitionLocationField);

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
                // TODO: copy file in client's folder - (clients/id <- get id from DB) + override prompt
            }
        });

        setBaseComponent(mainPanel);
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
            }
        });
    }

    private void setNotesRow() {
        JTextArea notesTextArea = new JTextArea();
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
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(4, 4, 4, 4);

        //constraints = setInivisibleColumns(constraints);
        //constraints = addGeneralRows(constraints);
        //constraints = addEconomicActivityRows(constraints);
        //constraints = addReportsRows(constraints);
        //constraints = addOtherRows(constraints);

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
        
        layoutBuffer.add(investitionLocationLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(investitionLocationField, COLUMN.DATA_ONE, 3);
        
        /* -------- Reports --------- */
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

        layoutBuffer.add(standartsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(standarts, COLUMN.DATA_ONE, 1);
        
        layoutBuffer.add(softwareSystemsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(softwareSystems, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(otherCompaniesConnectionsLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(otherCompaniesConnections, COLUMN.DATA_ONE, 1);
        
        layoutBuffer.add(attachedFilesLabel, COLUMN.LABELS, 1);
        layoutBuffer.add(attachFileBtn, COLUMN.DATA_ONE, 1);

        layoutBuffer.add(saveButton, COLUMN.DATA_ONE, 3, true);
        
        layoutBuffer.execute(mainPanel);
    }

    private GridBagConstraints setInivisibleColumns(GridBagConstraints constraints) {
        constraints.gridy = 0;

        JLabel leftSidePaddingCol = new JLabel();
        constraints.gridx = 0;
        constraints.weightx = 0.10;
        mainPanel.add(leftSidePaddingCol, constraints);

        JLabel labelsCol = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.15;
        mainPanel.add(labelsCol, constraints);

        JLabel firstDataCol = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.19;
        mainPanel.add(firstDataCol, constraints);

        JLabel secondDataCol = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.19;
        mainPanel.add(secondDataCol, constraints);

        JLabel thirdDataCol = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.19;
        mainPanel.add(thirdDataCol, constraints);

        JLabel rightSidePaddingCol = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.18;
        mainPanel.add(rightSidePaddingCol, constraints);

        return constraints;
    }
    

    private GridBagConstraints addGeneralRows(GridBagConstraints constraints) {

                constraints.gridy++;

                constraints.gridx = 1;
                constraints.gridwidth = 1;
                nameLabel.setVisible(true);
                mainPanel.add(nameLabel, constraints);

                constraints.gridx = 2;
                constraints.gridwidth = 3;
                mainPanel.add(nameField, constraints);

                constraints.gridy++;

                constraints.gridx = 1;
                constraints.gridwidth = 1;
                mainPanel.add(bulstatLabel, constraints);

                constraints.gridx = 2;
                constraints.gridwidth = 3;
                mainPanel.add(bulstatField, constraints);

                constraints.gridy++;

                constraints.gridx = 1;
                constraints.gridwidth = 1;
                mainPanel.add(seatAddressLabel, constraints);

                constraints.gridx = 2;
                constraints.gridwidth = 3;
                mainPanel.add(seatAddressField, constraints);

        return constraints;
    }

    private GridBagConstraints addEconomicActivityRows(GridBagConstraints constraints) {
        constraints.gridy++;

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(pageSectionLabelForEconomicClass, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(sectionLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(economicActivitySectionsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(divisionLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(economicActivityDivisionsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(groupLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(economicActivityGroupsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(classLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(economicActivityClassesComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(pageSectionLabelForArea, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(regionLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(regionComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(districtLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(districtComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(municipalityLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(municipalityComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(investitionLocationLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(investitionLocationField, constraints);

        return constraints;
    }

    private GridBagConstraints addReportsRows(GridBagConstraints constraints) {
        constraints.gridy++;

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(reportsLabel, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(yearLabel, constraints);

        constraints.fill = GridBagConstraints.CENTER;

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(threeYearsBackYearComboBox, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(twoYearsBackYearLabel, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(lastYearLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH; // reset

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(assetsSumLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(assetsSumThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(assetsSumTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(assetsSumLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(netProfitLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(netProfitThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(netProfitTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(netProfitLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(equityLabel, constraints);
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(equityThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(equityTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(equityLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(liabilitiesProvisionsLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(liabilitiesProvisionsThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(liabilitiesProvisionsTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(liabilitiesProvisionsLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(netSalesIncomeLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(netSalesThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(netSalesTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(netSalesLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(incomeOperatingActivitiesLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(incomeOperatingActivitiesThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(incomeOperatingActivitiesTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(incomeOperatingActivitiesLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(outlayOperatingActivitiesLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(outlayOperatingActivitiesThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(outlayOperatingActivitiesTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(outlayOperatingActivitiesLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(amortizationLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(amortizationThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(amortizationTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(amortizationLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(avgStaffNumberLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(avgStaffNumberThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(avgStaffNumberTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(avgStaffNumberLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(investmentsEquipmentLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(investmentsEquipmentThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(investmentsEquipmentTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(investmentsEquipmentLastYear, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(earningsExportTollingLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(earningsExportTollingThreeYearsAgo, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(earningsExportTollingTwoYearsAgo, constraints);

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        mainPanel.add(earningsExportTollingLastYear, constraints);

        return constraints;
    }

    private GridBagConstraints addOtherRows(GridBagConstraints constraints) {
        constraints.gridy++;

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(othersLabel, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(datePickerLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(startDatePicker, constraints);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        mainPanel.add(endDatePicker, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(notesLabel, constraints);

        constraints.weighty = 1;
        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(notesTextScrollPane, constraints);

        constraints.weighty = 0; // reset
        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(categoryCompanyLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(categoryCompany, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(minAmountFundingLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(minAmountFunding, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(maxAmountFundingLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(maxAmountFunding, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(maxPercentageFundingLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(maxPercentageFunding, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(wholeInvestitionAmountLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(wholeInvestitionAmount, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(standartsLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(standarts, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(softwareSystemsLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(softwareSystems, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(otherCompaniesConnectionsLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(otherCompaniesConnections, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        constraints.gridwidth = 1;
        mainPanel.add(attachedFilesLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        mainPanel.add(attachFileBtn, constraints);

        constraints.gridy++;

        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridwidth = 3;
        mainPanel.add(saveButton, constraints);

        return constraints;
    }
    

    private void save() {
        Date applyPeriodStartDate = (Date) startDatePicker.getModel().getValue();
        Date applyPeriodEndDate = (Date) endDatePicker.getModel().getValue();

        if (applyPeriodStartDate != null && applyPeriodEndDate != null
                && (applyPeriodStartDate.getTime() > applyPeriodEndDate.getTime())) {
            JOptionPane.showMessageDialog(null, "Началната дата не може да бъде след крайната дата.",
                    "Период за подаване на документи", JOptionPane.ERROR_MESSAGE);
        }
    }

    private interface COLUMN {
        public static final int LEFT_PADDING = 0;
        public static final int LABELS = 1;
        public static final int DATA_ONE = 2;
        public static final int DATA_TWO = 3;
        public static final int DATA_THREE = 4;
        public static final int RIGHT_PADDING = 5;
    }

    private class GridBagLayoutBuffer {
        private static final String KEY_STARTCOL = "StartCol";
        private static final String KEY_WIDTHCOL = "WidthCol";
        private static final String KEY_ISCENTERED = "IsCentered";
        private static final String KEY_WEIGHT_X = "WeightX";
        private static final String KEY_WEIGHT_Y = "WeightY";
        
        private static final int DEFAULT_WEIGHT_X = 0;
        private static final int DEFAULT_WEIGHT_Y = 0;

        private LinkedHashMap<Component, HashMap<String, Integer>> components = new LinkedHashMap<Component, HashMap<String, Integer>>();
        private GridBagConstraints constraints = new GridBagConstraints();
        private int lastCol = -1;

        public GridBagLayoutBuffer() {
            setDefaultConstraints();
            constraints.gridy = 1;
        }
        
        public void add(Component component, int startCol, int widthCol) {
            add(component, startCol, widthCol, false);
        }

        public void add(Component component, int startCol, int widthCol, boolean isCenter) {
            add(component, startCol, widthCol, isCenter, DEFAULT_WEIGHT_X, DEFAULT_WEIGHT_Y);
        }
        
        public void add(Component component, int startCol, int widthCol, boolean isCenter, int weightX, int weightY) {
            int isCenterInt = (isCenter) ? 1 : 0;
            components.put(component, new HashMap<String, Integer>() {
                {
                    put(KEY_STARTCOL, startCol);
                    put(KEY_WIDTHCOL, widthCol);
                    put(KEY_ISCENTERED, isCenterInt);
                    put(KEY_WEIGHT_X, weightX);
                    put(KEY_WEIGHT_Y, weightY);
                }
            });
        }

        public void execute(JPanel panel) {
            for (Map.Entry<Component, HashMap<String, Integer>> entry : components.entrySet()) {
                Component component = entry.getKey();
                HashMap<String, Integer> details = entry.getValue();

                int colNumber = details.get(KEY_STARTCOL);
                boolean isCentered = details.get(KEY_ISCENTERED) == 1;
                int width = details.get(KEY_WIDTHCOL);
                
                int weightX = details.get(KEY_WEIGHT_X);
                int weightY = details.get(KEY_WEIGHT_Y);
                constraints.weightx = weightX;
                constraints.weighty = weightY;

                if (lastCol >= colNumber) {
                    constraints.gridy++;
                }
                lastCol = colNumber;

                constraints.gridx = colNumber;
                constraints.gridwidth = width;

                if (isCentered) {
                    constraints.fill = GridBagConstraints.CENTER;
                }

                panel.add(component, constraints);

                if (isCentered) {
                    setDefaultConstraints();
                }
            }

            components = new LinkedHashMap<Component, HashMap<String, Integer>>();
        }

        private void setDefaultConstraints() {
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(4, 4, 4, 4);
        }
    }
}
