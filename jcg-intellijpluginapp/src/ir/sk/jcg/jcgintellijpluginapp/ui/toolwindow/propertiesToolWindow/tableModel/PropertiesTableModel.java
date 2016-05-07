package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.tableModel;

import com.intellij.openapi.ui.ComboBox;
import ir.sk.jcg.jcgcommon.util.ReflectionUtil;
import ir.sk.jcg.jcgengine.model.project.CellType;
import ir.sk.jcg.jcgengine.model.project.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.Element;
import ir.sk.jcg.jcgengine.model.project.annotation.Prop;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.PropertyTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class PropertiesTableModel extends AbstractTableModel {

    private boolean editable = false;

    private class PropertyField {

        PropertyField(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        private String name;
        private Object value;
        private boolean editable;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    private final String[] columnNames = {"Name", "Value"};

    private Element element;

    private List<PropertyField> propertyFields = new ArrayList<>();

    private PropertyTable propertyTable;

    public PropertiesTableModel(Element element, PropertyTable propertyTable) throws java.beans.IntrospectionException {
        this.element = element;

        List<Field> fields = ReflectionUtil.findFields(element.getClass(), Prop.class);
        for(Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value =  field.get(element);

            } catch (IllegalAccessException e) {
                e.printStackTrace(); // TODO: 5/5/2016
            }

            propertyFields.add(new PropertyField(field.getName(), value));
        }
        if (element.getClass().isAnnotationPresent(Editable.class))
            editable = true;

        this.propertyTable = propertyTable; // TODO: 5/5/2016

        setRenderers(fields);
        setEditors(fields);

    }

    /**
     * Set value of selected property in element
     * */
    private void setPropertyField(PropertyField propertyField) {
        try {
            Field field = ReflectionUtil.getFieldByName(propertyField.getName(), element);
            field.setAccessible(true);
            field.set(element, propertyField.getValue());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setRenderers(List<Field> fields) {
    }

    private void setEditors(List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            Field  field = fields.get(i);
            Prop prop = field.getAnnotation(Prop.class);
            CellType cellType = prop.editor();
            /////////////////////////// TODO: 5/5/2016 must create another method
            switch (cellType) {
                case NON_EDITABLE_COMBO:
                    Enum anEnum = null;
                    try {
                        anEnum = (Enum) field.get(element);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    ComboBox comboBox = new ComboBox();
                    Object[] possibleValues = anEnum.getDeclaringClass().getEnumConstants();
                    for (Object f : possibleValues) {
                        comboBox.addItem(f);
                    }
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(comboBox));//Reducer Properties
                    break;
                case EDITABLE_COMBO:
                    String[] values = prop.values();
                    ComboBox comboBox2 = new ComboBox();
                    for (String value : values) {
                        comboBox2.addItem(value);
                    }
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(comboBox2));//Reducer Properties
                    break;
                case DEFAULT:
                    // Not set. default accepted
                    break;
                default:
                    // Not set. default accepted
            }
            ///////////////////////////
        }
    }

    @Override
    public int getRowCount() {
        return propertyFields.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PropertyField propertyField = propertyFields.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return propertyField.getName();
            case 1:
                return propertyField.getValue();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        PropertyField propertyField = propertyFields.get(rowIndex);

        switch (columnIndex) {
            case 0:
                propertyField.setName((String) value);
                break;
            case 1:
                propertyField.setValue(value);
                break;
        }
        setPropertyField(propertyField);
        ReflectionUtil.printFieldNames(element);
        System.out.println();
    }

    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column
     * would contain text ("true"/"false"), rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        if (getValueAt(0, c) != null)
            return getValueAt(0, c).getClass();
        else
            return String.class; // when value is null
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else {
            return editable;
        }
    }
}