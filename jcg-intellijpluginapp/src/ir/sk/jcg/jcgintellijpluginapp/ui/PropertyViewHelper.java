package ir.sk.jcg.jcgintellijpluginapp.ui;

import com.intellij.openapi.ui.ComboBox;
import ir.sk.jcg.jcgcommon.PropertyView.PropertyInfo;

import javax.swing.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/10/2016
 */
public class PropertyViewHelper {

    public static JComponent getJComponentByPropertyField(PropertyInfo propertyInfo) {
        JComponent propertyFieldComponent = null;
        switch (propertyInfo.getComponentType()) {
            case NON_EDITABLE_COMBO:
                    Class<? extends Enum> classType= (Class<? extends Enum>) propertyInfo.getValue(); // TODO: 5/10/2016
                    Enum[] possibleValues = classType.getEnumConstants();
                    ComboBox comboBox = new ComboBox();
                    for (Enum<?> e : possibleValues) {
                        comboBox.addItem(e);
                    }
                break;
            case EDITABLE_COMBO:
                String[] values = propertyInfo.getValues();
                ComboBox comboBox2 = new ComboBox();
                for (String value : values) {
                    comboBox2.addItem(value);
                }
                break;
            case BOOLEAN_CHECKBOX:
                propertyFieldComponent = new JCheckBox();
                break;
            case DEFAULT:
                JTextField textField = new JTextField();
                Object value = propertyInfo.getValue();
                if (value != null)
                    textField.setText((String) value);
                propertyFieldComponent = (textField);
                break;
            default:
                propertyFieldComponent = new JTextField();
        }
        return propertyFieldComponent;
    }
}
