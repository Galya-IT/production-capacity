package com.galya.business.productioncapacity.components.tabs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.galya.business.productioncapacity.components.AdministrativeAreaComboBox;
import com.galya.business.productioncapacity.components.EconomicActivityComboBox;
import com.galya.business.productioncapacity.model.EconomicActivityClass;
import com.galya.business.productioncapacity.model.EconomicActivityDivision;
import com.galya.business.productioncapacity.model.EconomicActivityGroup;
import com.galya.business.productioncapacity.model.EconomicActivitySection;
import com.galya.business.productioncapacity.persistence.AdministrativeRuralAreasTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityClassTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityDivisionTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivityGroupTableHelper;
import com.galya.business.productioncapacity.persistence.EconomicActivitySectionTableHelper;

public class NewClientTab extends Tab {

    private static final String LABEL = "New Client";

    private static final int TAB_LABEL_WIDTH = 100;
    private static final int FIELD_LABEL_MIN_WIDTH = 50;

    private static final ImageIcon ICON = null;
    private static final boolean IS_CLOSEABLE = true;

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

    /*    private JTextField name;
        private JTextField activity;
        private JTextField bulstat;
        private JTextField seatOfCompany;
        private JTextField locationOfInvestition;
        private JTextField annualFinancialReportThreeYearsBack;
        private JTextField annualFinancialReportTwoYearsBack;
        private JTextField annualFinancialReportLastYear;
        private JTextField periodDocumentsAccepted;
        private JTextField attachedFiles;
        private JTextField generatedEvaluations;*/

    public NewClientTab() {
        super(LABEL, TAB_LABEL_WIDTH, ICON, IS_CLOSEABLE);

        mainPanel = new JPanel(false);
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

        pageSectionLabelForEconomicClass = generateLabel("НКИД", null);
        pageSectionLabelForEconomicClass.setHorizontalAlignment(JLabel.CENTER);
        
        sectionLabel = generateLabel("Сектор:", economicActivitySectionsComboBox);
        divisionLabel = generateLabel("Раздел:", economicActivityDivisionsComboBox);
        groupLabel = generateLabel("Група:", economicActivityGroupsComboBox);
        classLabel = generateLabel("Клас:", economicActivityClassesComboBox);
        
        pageSectionLabelForArea = generateLabel("Място на реализиране на инвестицията", null);
        pageSectionLabelForArea.setHorizontalAlignment(JLabel.CENTER);
        
        regionLabel = generateLabel("Регион:", regionComboBox);
        districtLabel = generateLabel("Област:", districtComboBox);
        municipalityLabel = generateLabel("Община:", municipalityComboBox);
        
        investitionLocationField = new JTextField("");
        investitionLocationLabel = generateLabel("Адрес:", investitionLocationField);
        
        addAllComponentsToLayout();

        JButton saveButton = new JButton("Запази");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: save
            }
        });

        setBaseComponent(mainPanel);

        //TODO: setCloseConfirmDialogMsg(changesNotSavedMsg);
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

    private JLabel generateLabel(String labelText, Component component) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setMinimumSize(new Dimension(FIELD_LABEL_MIN_WIDTH, label.getMinimumSize().height));
        label.setLabelFor(component);
        //label.setBorder(null);
        //label.setFont(DIALOG_DEFAULT_FONT);
        return label;
    }

    private GridBagConstraints setInivisibleColumns(GridBagConstraints constraints) {
        constraints.gridy = 0;

        JLabel a = new JLabel();
        constraints.gridx = 0;
        constraints.weightx = 0.15;
        mainPanel.add(a, constraints);

        JLabel b = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.15;
        mainPanel.add(b, constraints);

        JLabel c = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.55;
        mainPanel.add(c, constraints);

        JLabel d = new JLabel();
        constraints.gridx++;
        constraints.weightx = 0.15;
        mainPanel.add(d, constraints);

        return constraints;
    }

    private void addAllComponentsToLayout() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints = setInivisibleColumns(constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(nameLabel, constraints);

        constraints.gridx = 2;
        mainPanel.add(nameField, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(bulstatLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(bulstatField, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(seatAddressLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(seatAddressField, constraints);
        
        constraints.gridy++;
        
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        mainPanel.add(pageSectionLabelForEconomicClass, constraints);
        
        constraints.gridwidth = 1; // resets default
        
        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(sectionLabel, constraints);

        constraints.gridx = 2;
        mainPanel.add(economicActivitySectionsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(divisionLabel, constraints);

        constraints.gridx = 2;
        mainPanel.add(economicActivityDivisionsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(groupLabel, constraints);

        constraints.gridx = 2;
        mainPanel.add(economicActivityGroupsComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(classLabel, constraints);

        constraints.gridx = 2;
        mainPanel.add(economicActivityClassesComboBox, constraints);
        
        constraints.gridy++;
        
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        mainPanel.add(pageSectionLabelForArea, constraints);
        
        constraints.gridwidth = 1; // resets default
        
        constraints.gridy++;
        
        constraints.gridx = 1;
        mainPanel.add(regionLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(regionComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(districtLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(districtComboBox, constraints);

        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(municipalityLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(municipalityComboBox, constraints);
        
        constraints.gridy++;

        constraints.gridx = 1;
        mainPanel.add(investitionLocationLabel, constraints);
        
        constraints.gridx = 2;
        mainPanel.add(investitionLocationField, constraints);
    }

}
