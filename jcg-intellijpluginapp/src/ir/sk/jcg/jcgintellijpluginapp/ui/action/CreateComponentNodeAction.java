package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ComponentController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.ComponentControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class CreateComponentNodeAction extends CreateNodeAction {

    private DialogBuilder builder;
    private ComponentPanel componentPanel;

    public CreateComponentNodeAction() {
        super("Create Component");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        ModelElement modelElement = (ModelElement) jcgProjectComponent.currentSelectedNodeUserObject();
        boolean viewOrDataGrid = true; // Default is View
        View view = null;
        if (modelElement instanceof SearchView) {
            view = (View) modelElement;
            componentPanel = new ComponentPanel(view.getTargetEntity().getAllEntityElements(), true);
        } if (modelElement instanceof DisplayView) {
            viewOrDataGrid = false;
            view = (View) modelElement;
            componentPanel = new ComponentPanel(view.getTargetEntity().getAllEntityElements(), false);
        }
        else if (modelElement instanceof DataGrid) {
            viewOrDataGrid = false;
            view = (View) jcgProjectComponent.parentSelectedNodeUserObject();
            componentPanel = new ComponentPanel(view.getTargetEntity().getAllEntityElements(), false);
        }

        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Component");
        builder.setPreferredFocusComponent(componentPanel);
        builder.setCenterPanel(componentPanel);
        boolean finalViewOrDataGrid = viewOrDataGrid;
        View finalView = view;
        builder.setOkOperation(() -> {
            ComponentController componentController = ComponentControllerImpl.getInstance();

            Component component = null;
            if (finalViewOrDataGrid)
                component = componentController.createInputComponent(componentPanel.getComponentDto(), finalView);
            else {
                ComponentContainer componentContainer = (ComponentContainer) modelElement;
                component = componentController.createOutputComponent(componentPanel.getComponentDto(), componentContainer);
            }

            jcgProjectComponent.addNeededNodes(component);

            marshalingAndReloadTree(false);
            builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });
        builder.showModal(true);
    }
}
