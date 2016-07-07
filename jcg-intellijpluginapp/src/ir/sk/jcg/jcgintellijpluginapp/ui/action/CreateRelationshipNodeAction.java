package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import ir.sk.jcg.jcgcommon.util.StringUtils;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.CollectionType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.RelationshipController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.RelationshipControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class CreateRelationshipNodeAction extends NodeAction {

    private DialogBuilder builder;
    protected RelationshipPanel relationshipPanel;

    public CreateRelationshipNodeAction() {
        super("Create Relationship");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        Package<Entity> entityPackage = (Package<Entity>) ((DefaultMutableTreeNode) jcgProjectComponent.getSelectionPath().getParentPath().getLastPathComponent()).getUserObject();

        relationshipPanel = new RelationshipPanel(entityPackage.getElements());
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Relationship");
        builder.setPreferredFocusComponent(relationshipPanel);
        builder.setCenterPanel(relationshipPanel);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {
                RelationshipController relationshipController = RelationshipControllerImpl.getInstance();
                Entity entity = (Entity) jcgProjectComponent.currentSelectedNodeUserObject();

                relationshipController.createRelationship(relationshipPanel.getRelationShipDto(), entity);

                marshalingAndReloadTree();
                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        });
        builder.showModal(true);
    }
}
