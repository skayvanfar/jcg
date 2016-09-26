package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow;

import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.editor.RowEditorModel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.renderer.RowRendererModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.Objects;
import java.util.Vector;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class PropertyTable extends JTable {
    
    private RowEditorModel rowEditorModel;
    private RowRendererModel rowRendererModel;

    public PropertyTable() {
    }

    public PropertyTable(TableModel tableModel) {
        super(tableModel);
    }

    public PropertyTable(TableModel tableModel, TableColumnModel tableColumnModel) {
        super(tableModel, tableColumnModel);
    }

    public PropertyTable(TableModel tableModel, TableColumnModel tableColumnModel, ListSelectionModel listSelectionModel) {
        super(tableModel, tableColumnModel, listSelectionModel);
    }

    public PropertyTable(int i, int i1) {
        super(i, i1);
    }

    public PropertyTable(Vector vector, Vector vector1) {
        super(vector, vector1);
    }

    public PropertyTable(Object[][] objects, Object[] objects1) {
        super(objects, objects1);
    }

    public PropertyTable(TableModel tableModel, RowEditorModel rowEditorModel) {
        super(tableModel, null, null);
        this.rowEditorModel = rowEditorModel;
    }

    // TODO: 5/5/2016 encapsulation violeted 
    public void setRowEditorModel(RowEditorModel rowEditorModel) {
        this.rowEditorModel = rowEditorModel;
    }

    public RowEditorModel getRowEditorModel() {
        return this.rowEditorModel;
    }

    public RowRendererModel getRowRendererModel() {
        return rowRendererModel;
    }

    public void setRowRendererModel(RowRendererModel rowRendererModel) {
        this.rowRendererModel = rowRendererModel;
    }

    public void clearRenderers() {
        if (this.getRowEditorModel() != null)
            this.getRowRendererModel().clearRenderers();
    }

    public void clearEditors() {
        if (this.getRowEditorModel() != null)
            this.getRowEditorModel().clearEditors();
    }

    /**
     * Overriden method getCellEditor returns editor defined by programmer if exists (for example PropertiesCellEditor),
     * or let the super class to decide which editor use
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        TableCellEditor wantedEditor = null;
        if (this.rowEditorModel != null) {
            wantedEditor = this.rowEditorModel.getEditor(row);
        }
        if (wantedEditor != null)
            return wantedEditor;
        return super.getCellEditor(row, col);
    }

    /**
     * Overriden method getCellRenderer returns editor defined by programmer if exists (for example PropertiesCellEditor),
     * or let the super class to decide which renderer use
     *
     * @param row
     * @param column
     * @return
     */
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer wantedRenderer = null;
        if (this.rowEditorModel != null) {
            wantedRenderer = this.rowRendererModel.getRenderer(row);
        }
        if (wantedRenderer != null) return wantedRenderer;

        if (Objects.equals(getColumnName(column), "Value")) {
            Class cellClass = null;
            if (getValueAt(row, column) != null)
                cellClass = getValueAt(row, column).getClass();
            if (cellClass != null)
                return getDefaultRenderer(cellClass);
            //wantedRenderer = this.rowRendererModel.getRenderer(row);
        }
        return super.getCellRenderer(row, column);
    }
}
