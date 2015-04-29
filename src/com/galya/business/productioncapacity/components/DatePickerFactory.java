package com.galya.business.productioncapacity.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePickerFactory {

    public static JDatePickerImpl generateDatePickerBg() {
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Днес");
        properties.put("text.month", "Месец");
        properties.put("text.year", "Година");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        
        AbstractFormatter formatter = new AbstractFormatter() {
            private static final long serialVersionUID = -8683300026142745158L;
            
            private String datePattern = "dd.MM.yyyy";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        };
        
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);
        return datePicker;
    }

}
