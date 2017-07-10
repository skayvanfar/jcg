package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.editor;

import javax.swing.table.TableCellEditor;
import java.util.HashMap;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class RowEditorModel {
    /**
     * HashMap that stores all programatically defined editors. Stored couple (row, editor)
     */
    private HashMap<Integer, TableCellEditor> dataMap;

    public RowEditorModel() {
        this.dataMap = new HashMap<>();
    }

    public void addEditorForRow(int row, TableCellEditor tableCellEditor) {
        dataMap.put(row, tableCellEditor);
    }

    public void removeEditorForRow(int row) {
        dataMap.remove(row);
    }

    public void removeAllEditors() {
        this.dataMap.clear();
    }

    public TableCellEditor getEditor(int row) {
        return dataMap.get(row);
    }

    public void clearEditors() {
        if (this.dataMap != null)
            this.dataMap.clear();
    }
}
