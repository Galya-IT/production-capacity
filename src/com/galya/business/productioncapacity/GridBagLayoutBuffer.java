package com.galya.business.productioncapacity;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

public class GridBagLayoutBuffer {
    
    public static final int DEFAULT_WEIGHT_X = 0;
    public static final int DEFAULT_WEIGHT_Y = 0;
    public static final int DEFAULT_INSETS_VALUE = 4;
    public static final Insets DEFAULT_INSETS = new Insets(DEFAULT_INSETS_VALUE, DEFAULT_INSETS_VALUE, DEFAULT_INSETS_VALUE, DEFAULT_INSETS_VALUE);
    
    private static final String KEY_STARTCOL = "StartCol";
    private static final String KEY_WIDTHCOL = "WidthCol";
    private static final String KEY_ISCENTERED = "IsCentered";
    private static final String KEY_WEIGHT_X = "WeightX";
    private static final String KEY_WEIGHT_Y = "WeightY";
    private static final String KEY_INSETS_VALUE = "InsetsValue";

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
        add(component, startCol, widthCol, isCenter, DEFAULT_WEIGHT_X, DEFAULT_WEIGHT_Y, DEFAULT_INSETS_VALUE);
    }
    
    public void add(Component component, int startCol, int widthCol, boolean isCenter, int insets) {
        add(component, startCol, widthCol, isCenter, DEFAULT_WEIGHT_X, DEFAULT_WEIGHT_Y, insets);
    }
    
    public void add(Component component, int startCol, int widthCol, boolean isCenter, int weightX, int weightY) {
        add(component, startCol, widthCol, isCenter, weightX, weightY, DEFAULT_INSETS_VALUE);
    }

    @SuppressWarnings("serial")
    public void add(Component component, int startCol, int widthCol, boolean isCenter, int weightX, int weightY, int insets) {
        int isCenterInt = (isCenter) ? 1 : 0;
        components.put(component, new HashMap<String, Integer>() {
            {
                put(KEY_STARTCOL, startCol);
                put(KEY_WIDTHCOL, widthCol);
                put(KEY_ISCENTERED, isCenterInt);
                put(KEY_WEIGHT_X, weightX);
                put(KEY_WEIGHT_Y, weightY);
                put(KEY_INSETS_VALUE, insets);
            }
        });
    }

    /**
     * New row is determined by presumption that
     * if the component is to be added to a column previous or the same as the last one,
     * then a new row should be added;
     */
    public void execute(JPanel panel) {
        for (Map.Entry<Component, HashMap<String, Integer>> entry : components.entrySet()) {
            Component component = entry.getKey();
            HashMap<String, Integer> details = entry.getValue();

            int colNumber = details.get(KEY_STARTCOL);
            boolean isCentered = details.get(KEY_ISCENTERED) == 1;
            int width = details.get(KEY_WIDTHCOL);
            int insetsValue = details.get(KEY_INSETS_VALUE);

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
            
            if (insetsValue != DEFAULT_INSETS_VALUE) {
                constraints.insets = new Insets(insetsValue, insetsValue, insetsValue, insetsValue);
            }

            panel.add(component, constraints);

            if (isCentered || insetsValue != DEFAULT_INSETS_VALUE) {
                setDefaultConstraints();
            }
        }

        components = new LinkedHashMap<Component, HashMap<String, Integer>>();
    }

    private void setDefaultConstraints() {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = DEFAULT_INSETS;
        constraints.anchor = GridBagConstraints.NORTH;
    }
}