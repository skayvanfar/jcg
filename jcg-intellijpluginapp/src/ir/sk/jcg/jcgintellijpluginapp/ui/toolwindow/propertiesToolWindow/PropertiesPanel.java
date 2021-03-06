package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;
import ir.sk.jcg.jcgcommon.util.ReflectionUtil;
import ir.sk.jcg.jcgcommon.util.SerializationUtil;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.editor.RowEditorModel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.renderer.RowRendererModel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.tableModel.PropertiesTableModel;

import javax.swing.*;
import java.awt.*;
import java.beans.IntrospectionException;
import java.lang.reflect.Field;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/24/2016
 */
public class PropertiesPanel extends SimpleToolWindowPanel {

    private JPanel propsPanel;
    private PropertyTable propertyTable;

    // Object that show in table model
    private Presentable realPresentable;
    private Presentable clonedPresentable;

    public PropertiesPanel() {
        super(true);

        this.setLayout(new BorderLayout());
        this.propsPanel = new JPanel();
        this.propsPanel.setLayout(new GridLayout());

        this.propertyTable = new PropertyTable();

        this.propertyTable.setRowSelectionAllowed(false);
        this.propertyTable.setColumnSelectionAllowed(false);
        RowEditorModel rowEditorModel = new RowEditorModel();
        RowRendererModel rowRendererModel = new RowRendererModel();
        this.propertyTable.setRowEditorModel(rowEditorModel);
        this.propertyTable.setRowRendererModel(rowRendererModel);

        JBScrollPane sp = new JBScrollPane(this.propertyTable,
                JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        this.propsPanel.add(sp);
        this.add(this.propsPanel, BorderLayout.CENTER);
        setToolbar(createToolBar());
    }

    /**
     * Set new PropertiesTableModel for PropertyTable with clone object of element
     */
    public void setPresentable(Presentable presentable) {
        realPresentable = presentable;

        try {
            clonedPresentable = (Presentable) SerializationUtil.shallowCloneByAnnotation(realPresentable, Prop.class);
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) { // TODO: 5/15/2016
            e.printStackTrace();
        }

        try {
            propertyTable.setModel(new PropertiesTableModel(clonedPresentable, propertyTable));
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private JComponent createToolBar() {
        ActionGroup actionGroup = (ActionGroup) ActionManager.getInstance().getAction("JCG.PropertiesToolbar");
        String place = ActionPlaces.EDITOR_TOOLBAR;
        JPanel toolBarPanel = new JPanel(new GridLayout());
        toolBarPanel.add(ActionManager.getInstance().createActionToolbar(place, actionGroup, true).getComponent());
        return toolBarPanel;
    }

    /**
     * Save copy Element to real element
     */
    public void setModifiedElement() {
        java.util.List<Field> fields = ReflectionUtil.findFields(realPresentable.getClass(), Prop.class);
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String name = field.getName();
                Field copyField = ReflectionUtil.getFieldByName(name, clonedPresentable);
                copyField.setAccessible(true);
                Object newValue = copyField.get(clonedPresentable);
                field.set(realPresentable, newValue);
                copyField.setAccessible(false);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace(); // TODO: 5/5/2016
            } finally {
                field.setAccessible(false);
            }
        }
    }
}
