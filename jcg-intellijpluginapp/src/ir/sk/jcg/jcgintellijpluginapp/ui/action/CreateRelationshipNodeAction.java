package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.RelationshipController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.RelationshipControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class CreateRelationshipNodeAction extends NodeAction {

    private DialogBuilder builder;
    private RelationshipPanel relationshipPanel;

    public CreateRelationshipNodeAction() {
        super("Create Relationship");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        Package<Entity> entityPackage = (Package<Entity>) jcgProjectComponent.parentSelectedNodeUserObject();
        relationshipPanel = new RelationshipPanel(entityPackage.getElements());
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Relationship");
        builder.setPreferredFocusComponent(relationshipPanel);
        builder.setCenterPanel(relationshipPanel);
        builder.setOkOperation(() -> {
            RelationshipController relationshipController = RelationshipControllerImpl.getInstance();
            Entity entity = (Entity) jcgProjectComponent.currentSelectedNodeUserObject();

            Relationship relationship = relationshipController.createRelationship(relationshipPanel.getRelationShipDto(), entity);

            jcgProjectComponent.addNeededNodes(relationship);

            marshalingAndReloadTree(false);
            builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
        });
        builder.showModal(true);
    }
}
