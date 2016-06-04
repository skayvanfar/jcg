package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.tableModel;

import com.intellij.openapi.ui.ComboBox;
import com.siyeh.ig.ui.TextField;
import ir.sk.jcg.jcgcommon.util.ReflectionUtil;
import ir.sk.jcg.jcgcommon.PropertyView.PropertyInfo;
import ir.sk.jcg.jcgcommon.PropertyView.ComponentType;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.project.Element;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
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

    private final String[] columnNames = {"Name", "Value"};

    private Presentable presentable;

    private List<PropertyInfo> propertyInfos = new ArrayList<>();

    private PropertyTable propertyTable;

    public PropertiesTableModel(Presentable presentable, PropertyTable propertyTable) throws java.beans.IntrospectionException {
        this.presentable = presentable;

        List<Field> fields = ReflectionUtil.findFields(presentable.getClass(), Prop.class);

        PropertyInfo propertyInfo = null;
        for(Field field : fields) {
            try {
                propertyInfo = new PropertyInfo(field, presentable);
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // TODO: 5/5/2016
            }
            propertyInfos.add(propertyInfo);
        }
        if (presentable.getClass().isAnnotationPresent(Editable.class))
            editable = true; // TODO: 5/12/2016 not useable 

        this.propertyTable = propertyTable; // TODO: 5/5/2016

        setRenderers();
        setEditors();

    }

    /**
     * Set value of selected property in element
     * */
    private void setPropertyField(PropertyInfo propertyInfo) {
        try {
            Field field = ReflectionUtil.getFieldByName(propertyInfo.getName(), presentable);
            field.setAccessible(true);
            field.set(presentable, propertyInfo.getValue());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setRenderers() {
        propertyTable.clearRenderers();
//        for (int i = 0; i < fields.size(); i++) { // TODO: 5/7/2016 not worked: CellRenderer must be red border for required cell 
//            Field  field = fields.get(i);
//            Prop prop = field.getAnnotation(Prop.class);
//            boolean required = prop.required();
//            if (required) {
//                DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//                cellRenderer.setBorder(new ToolWindow.Border());///////////////////////////////////////////////////////////////////////////////////
//                this.propertyTable.getRowRendererModel().addRendererForRow(i, cellRenderer);
//            } else {
//                // Not set. default accepted
//            }
//        }
    }

    private void setEditors() {
        propertyTable.clearEditors();
        int i = 0;
        for (PropertyInfo propertyInfo : propertyInfos) {
            ComponentType componentType = propertyInfo.getComponentType();
            /////////////////////////// TODO: 5/5/2016 must create another method
            switch (componentType) {
                case NON_EDITABLE_COMBO:
                    Class<? extends Enum> classType= (Class<? extends Enum>) propertyInfo.getTypeClass();
                    Enum[] possibleValues = classType.getEnumConstants();
                   // JComboBox comboBox = new JComboBox();
                    ComboBox comboBox = new ComboBox();
                    for (Enum<?> e : possibleValues) {
                        comboBox.addItem(e);
                    }
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(comboBox));//Reducer Properties
                    break;
                case EDITABLE_COMBO:
                    String[] values = propertyInfo.getValues();
                    ComboBox comboBox2 = new ComboBox();
                    comboBox2.setEditable(true);
                    for (String value : values) {
                        comboBox2.addItem(value);
                    }
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(comboBox2));//Reducer Properties // TODO: 5/12/2016 DefaultCellEditor not worked hear and must change 
                    break;
                case BOOLEAN_CHECKBOX:
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(new JCheckBox()));//Reducer Properties // TODO: 5/7/2016 use Checkbox

                    break;
                case DEFAULT:
                    this.propertyTable.getRowEditorModel().addEditorForRow(i, new DefaultCellEditor(new JTextField()));//Reducer Properties // TODO: 5/13/2016 must not set. but because use Enum for other properties occasion Default editor not work
                    // Not set. default accepted
                    break;
                default:
                    // Not set. default accepted
            }
            ///////////////////////////
            i++;
        }
    }

    @Override
    public int getRowCount() {
        return propertyInfos.size();
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
        PropertyInfo propertyInfo = propertyInfos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return !propertyInfo.getLabel().equals("") ? propertyInfo.getLabel() : propertyInfo.getName();
            case 1:
                return propertyInfo.getValue();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        PropertyInfo propertyInfo = propertyInfos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                propertyInfo.setName((String) value);
                break;
            case 1:
                propertyInfo.setValue(value);
                break;
        }
        setPropertyField(propertyInfo);
        ReflectionUtil.printFieldNames(presentable);
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
        } else if(editable && propertyInfos.get(rowIndex).isEditable()) {
            return true;
        }
        return false;
    }
}
