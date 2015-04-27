package com.galya.business.productioncapacity.components;

import static com.galya.business.productioncapacity.utils.CommonUtils.isEmpty;

import java.awt.Dimension;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.galya.business.productioncapacity.utils.GuiUtils;

public class AdministrativeAreaComboBox extends JComboBox<String> {

    private static final long serialVersionUID = 508618837605651632L;

    private static final int MAX_COMBO_BOX_OPTIONS = 10;

    private CustomComboBoxModel model;

    public AdministrativeAreaComboBox(List<String> areas) {
        super();

        setMaximumRowCount(MAX_COMBO_BOX_OPTIONS);
        setCustomMinimumSize();

        if (!isEmpty(areas)) {
            model = new CustomComboBoxModel(areas);
            setModel(model);
            setSelectedIndex(0);
        }
    }

    private void setCustomMinimumSize() {
        int minWidth = (int) (GuiUtils.getScreenDimension().width / 5);
        setMinimumSize(new Dimension(minWidth, getMinimumSize().height));
    }

    public String getSelectedItem() {
        return (String) getModel().getSelectedItem();
    }

    public void setData(List<String> economicActivities) {
        model = new CustomComboBoxModel(economicActivities);
        setModel(model);
        setSelectedIndex(0);
    }

    private class CustomComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

        private static final long serialVersionUID = -257579925225294669L;

        private static final String DEFAULT_OPTION = "------- Избери от списъка -------";

        private List<String> areas;
        private String selection;

        public CustomComboBoxModel(List<String> areas) {
            this.areas = areas;
        }

        @Override
        public String getElementAt(int index) {
            String elementAtPosition = DEFAULT_OPTION;
            if (index > 0) {
                elementAtPosition = areas.get(index - 1);
            }
            return elementAtPosition;
        }

        @Override
        public int getSize() {
            return areas.size() + 1;
        }

        @Override
        public void setSelectedItem(Object selectedItem) {
            selection = (String) selectedItem;
        }

        @Override
        public Object getSelectedItem() {
            return selection;
        }
    }
}
