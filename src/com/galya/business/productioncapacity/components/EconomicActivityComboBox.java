package com.galya.business.productioncapacity.components;

import static com.galya.business.productioncapacity.utils.CommonUtils.isEmpty;

import java.awt.Dimension;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.galya.business.productioncapacity.model.EconomicActivityType;
import com.galya.business.productioncapacity.utils.GuiUtils;

public class EconomicActivityComboBox<T extends EconomicActivityType> extends JComboBox<T> {

    private static final long serialVersionUID = 508618837605651632L;

    private static final String DEFAULT_OPTION = "------- Избери от списъка -------";
    private static final int MAX_COMBO_BOX_OPTIONS = 10;

    private EATypesComboBoxModel<T> model;
    private T defaultOption = null;

    public EconomicActivityComboBox(Class<T> clazz, List<T> economicActivities) {
        super();

        setMaximumRowCount(MAX_COMBO_BOX_OPTIONS);
        setCustomMinimumSize();
        setDefaultOption(clazz);

        if (!isEmpty(economicActivities)) {
            model = new EATypesComboBoxModel<T>(economicActivities, defaultOption);
            setModel(model);
            setSelectedIndex(0);
        }
    }

    private void setCustomMinimumSize() {
        int minWidth = (int) (GuiUtils.getScreenDimension().width / 5);
        setMinimumSize(new Dimension(minWidth, getMinimumSize().height));
    }

    @SuppressWarnings("unchecked")
    public String getSelectedItemId() {
        T selectedItem = (T) EconomicActivityComboBox.this.getModel().getSelectedItem();
        return selectedItem.getId();
    }

    public void setData(List<T> economicActivities) {
        model = new EATypesComboBoxModel<T>(economicActivities, defaultOption);
        setModel(model);
        setSelectedIndex(0);
    }

    private void setDefaultOption(Class<T> clazz) {
        try {
            try {
                Constructor<T> constructor = clazz.getConstructor(Integer.class, String.class, String.class,
                        String.class);
                defaultOption = constructor.newInstance(-1, null, DEFAULT_OPTION, null);
            } catch (NoSuchMethodException e) {
                Constructor<T> constructor = clazz.getConstructor(Integer.class, String.class, String.class);
                defaultOption = constructor.newInstance(-1, null, DEFAULT_OPTION);
            }
        } catch (Exception e) {
            System.err.println("No default option could be initialized for " + this.getClass());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("hiding")
    private class EATypesComboBoxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {

        private static final long serialVersionUID = -4109362889129863784L;

        private final T defaultOption;

        private List<T> economicActivityType;
        private T selection;

        public EATypesComboBoxModel(List<T> economicActivityTypes, T defaultOption) {
            this.defaultOption = defaultOption;
            this.economicActivityType = economicActivityTypes;
        }

        @Override
        public T getElementAt(int index) {
            T elementAtPosition = defaultOption;
            if (index > 0) {
                elementAtPosition = economicActivityType.get(index - 1);
            }
            return elementAtPosition;
        }

        @Override
        public int getSize() {
            return economicActivityType.size() + 1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void setSelectedItem(Object selectedItem) {
            selection = (T) selectedItem;
        }

        @Override
        public Object getSelectedItem() {
            return selection;
        }
    }
}
