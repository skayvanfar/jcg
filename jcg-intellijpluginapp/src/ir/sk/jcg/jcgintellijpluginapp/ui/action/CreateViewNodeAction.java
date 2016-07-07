package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.ViewController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.ViewControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateViewNodeAction extends CreateNodeAction {

    private DialogBuilder builder;
    protected ViewPanel viewPanel;

    public CreateViewNodeAction() {
        super("Create View");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        //   Package<Entity> entityPackage = (Package<View>) ((DefaultMutableTreeNode) jcgProjectComponent.getLeadTreePath().getParentPath().getLastPathComponent()).getUserObject();

        List<Entity> entitys = jcgProjectComponent.findEntitiesByUserObject();

        viewPanel = new ViewPanel(entitys);
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New View");
        builder.setPreferredFocusComponent(viewPanel);
        builder.setCenterPanel(viewPanel);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                ViewController viewController = ViewControllerImpl.getInstance();
                Package<View> viewPackage = (Package<View>) jcgProjectComponent.currentSelectedNodeUserObject();

                viewController.createView(viewPanel.getViewDto(), viewPackage);

                marshalingAndReloadTree();
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });
        builder.showModal(true);
    }
}
