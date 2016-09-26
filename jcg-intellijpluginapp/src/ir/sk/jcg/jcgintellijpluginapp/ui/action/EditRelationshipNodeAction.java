package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Package;
import ir.sk.jcg.jcgengine.model.project.Relationship;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.RelationshipController;
import ir.sk.jcg.jcgintellijpluginapp.ui.controller.impl.RelationshipControllerImpl;
import ir.sk.jcg.jcgintellijpluginapp.ui.dto.RelationShipDto;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by saeed on 6/1/16.
 */
public class EditRelationshipNodeAction extends NodeAction  {

    private DialogBuilder builder;
    private RelationshipPanel relationshipPanel;

    public EditRelationshipNodeAction() {
        super("Edit Relationship");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        Relationship relationship = (Relationship) jcgProjectComponent.currentSelectedNodeUserObject();

        builder = new DialogBuilder(e.getProject());
        builder.setTitle("Edit Relationship");
        if (!relationship.isHead()) {
            JLabel label = new JLabel("You must edit Head side of the Relationship.");
            JPanel panel = new JPanel();
            panel.add(label);
            builder.setPreferredFocusComponent(panel);
            builder.setCenterPanel(panel);
        } else {
            // TODO: 6/1/16
            Package<Entity> entityPackage = (Package<Entity>) ((DefaultMutableTreeNode) jcgProjectComponent.getSelectionPath().getParentPath().getParentPath().getLastPathComponent()).getUserObject();

            RelationshipController relationshipController = RelationshipControllerImpl.getInstance();

            relationshipPanel = new RelationshipPanel(entityPackage.getElements());

            builder.setPreferredFocusComponent(relationshipPanel);

            RelationShipDto relationShipDto = relationshipController.initRelationshipDto(relationship);
            relationshipPanel.setRelationShipDto(relationShipDto);

            builder.setCenterPanel(relationshipPanel);
            builder.setOkOperation(() -> {
                RelationShipDto editedRelationShipDto = relationshipPanel.getRelationShipDto();

                Entity entity = (Entity) ((DefaultMutableTreeNode) jcgProjectComponent.getSelectionPath().getParentPath().getLastPathComponent()).getUserObject();
                Relationship headRelationship = (Relationship) jcgProjectComponent.currentSelectedNodeUserObject();

                relationshipController.editRelationship(editedRelationShipDto, headRelationship, entity);

                marshalingAndReloadTree();

                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            });
        }
        builder.showModal(true);
    }



}
