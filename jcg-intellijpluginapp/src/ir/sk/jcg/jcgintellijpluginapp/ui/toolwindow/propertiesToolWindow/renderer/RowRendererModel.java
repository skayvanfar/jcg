package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.renderer;

import javax.swing.table.TableCellRenderer;
import java.util.HashMap;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class RowRendererModel {
    /**
     * HashMap that stores all programatically defined renderers. Stored couple (row, renderer)
     */
    private HashMap<Integer, TableCellRenderer> dataMap;

    public RowRendererModel() {
        this.dataMap = new HashMap<>();
    }

    public void addRendererForRow(int row, TableCellRenderer tableCellRenderer) {
        dataMap.put(row, tableCellRenderer);
    }

    public void removeRendererForRow(int row) {
        dataMap.remove(row);
    }

    public void removeAllRenderers() {
        this.dataMap.clear();
    }

    public TableCellRenderer getRenderer(int row) {
        return (TableCellRenderer) dataMap.get(row);
    }

    public void clearRenderers() {
        if (this.dataMap != null) this.dataMap.clear();
    }
}
