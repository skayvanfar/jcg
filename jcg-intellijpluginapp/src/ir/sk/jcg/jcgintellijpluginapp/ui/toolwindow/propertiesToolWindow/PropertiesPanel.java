package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.components.JBScrollPane;
import ir.sk.jcg.jcgengine.model.platform.technology.ORMTechnology;
import ir.sk.jcg.jcgengine.model.project.Element;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Project;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.editor.RowEditorModel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.renderer.RowRendererModel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.tableModel.PropertiesTableModel;

import javax.swing.*;
import java.awt.*;
import java.beans.IntrospectionException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/24/2016
 */
public class PropertiesPanel extends SimpleToolWindowPanel {

    private JPanel propsPanel;
    private PropertyTable propertyTable;

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

    public void setElement(Element element) {
        try {
            propertyTable.setModel(new PropertiesTableModel(element, propertyTable));
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    private JComponent createToolBar() {
        ActionGroup actionGroup = (ActionGroup) ActionManager.getInstance().getAction("JCG.PropertiesToolbar");
        String place = ActionPlaces.EDITOR_TOOLBAR;
        JPanel toolBarPanel = new JPanel(new GridLayout());
        toolBarPanel.add(ActionManager.getInstance().createActionToolbar(place, actionGroup, true).getComponent());
        return toolBarPanel;
    }
}
