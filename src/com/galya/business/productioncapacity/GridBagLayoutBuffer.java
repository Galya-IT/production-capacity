package com.galya.business.productioncapacity;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

public class GridBagLayoutBuffer {
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