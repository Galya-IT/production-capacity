package com.galya.business.productioncapacity.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class ComponentFactory {

    public static JLabel generateLabel(String labelText) {
        final int FIELD_LABEL_MIN_WIDTH = 50;
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setMinimumSize(new Dimension(FIELD_LABEL_MIN_WIDTH, label.getMinimumSize().height));
        label.setToolTipText(labelText);
        return label;
    }
    
    public static JLabel generateLabel(String labelText, Component component) {
        JLabel label = generateLabel(labelText);
        label.setLabelFor(component);
        return label;
    }

    public static JLabel generateLabel(String labelText, Font dialogFont) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(dialogFont);
        return label;
    }
    
    public static JFormattedTextField generateNumericField(double defaultValue) {
        final String NUMBER_FORMAT_THOUSANDS = "#.#####";
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_FORMAT_THOUSANDS);
        decimalFormat.setGroupingUsed(false);
        JFormattedTextField numericField = new JFormattedTextField(decimalFormat);
        numericField.setValue(defaultValue);
        numericField.setColumns(15);
        return numericField;
    }
    
}
