package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ComponentController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ViewController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.ComponentControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.ViewControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/8/2016
 */
public class CreateComponentNodeAction extends CreateNodeAction {

    private DialogBuilder builder;
    protected ComponentPanel componentPanel;

    public CreateComponentNodeAction() {
        super("Create Component");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());

        View view = (View) jcgProjectComponent.currentSelectedNodeUserObject();

        componentPanel = new ComponentPanel(view.getTargetEntity().getProperties());
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Component");
        builder.setPreferredFocusComponent(componentPanel);
        builder.setCenterPanel(componentPanel);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                ComponentController componentController = ComponentControllerImpl.getInstance();

                componentController.createComponent(componentPanel.getComponentDto(), view);

                marshalingAndReloadTree();
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });
        builder.showModal(true);
    }
}
