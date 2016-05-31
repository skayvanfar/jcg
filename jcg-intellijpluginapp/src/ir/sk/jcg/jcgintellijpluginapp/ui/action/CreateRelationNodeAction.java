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
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.JAXBException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2016
 */
public class CreateRelationNodeAction extends NodeAction {

    private DialogBuilder builder;
    protected NewRelationPanel newRelationPanel;

    public CreateRelationNodeAction() {
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        Package<Entity> entityPackage = (Package<Entity>) ((DefaultMutableTreeNode) jcgProjectComponent.getSelectionPath().getParentPath().getLastPathComponent()).getUserObject();


        newRelationPanel = new NewRelationPanel(entityPackage.getElements());
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("New Relationship");
        builder.setPreferredFocusComponent(newRelationPanel);
        builder.setCenterPanel(newRelationPanel);
        builder.setOkOperation(new Runnable() {

            private CodeGenerator codeGenerator;

            @Override
            public void run() {
                String relationName = newRelationPanel.getNodeName();
                if (StringUtil.isNotEmpty(relationName)) {
               //     entityName = correctName(entityName); // TODO: 5/12/2016
                }

                JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
                Entity entity = (Entity) jcgProjectComponent.currentSelectedNodeUserObject();
                codeGenerator = jcgProjectComponent.getCodeGenerator();

                Relationship relationship = new Relationship();
               // relationship.setName(relationName);
                relationship.setHead(true); // This Relation is head
                relationship.setCardinalityType(newRelationPanel.getCardinalitySelected());
                relationship.setDirectionalityType(newRelationPanel.getDirectionalitySelected());

                Entity targetEntity = newRelationPanel.getTargetEntitySelected();
                relationship.setTargetEntity(targetEntity);
                relationship.setCollectionType(newRelationPanel.getCollectionSelected());
                entity.addRelation(relationship);


                Relationship targetRelationship = createAnotherRelationShipIfNeed(relationship, entity);
                if (targetRelationship != null)
                    targetEntity.addRelation(targetRelationship);

                setCollectionTypes(newRelationPanel.getCardinalitySelected(), newRelationPanel.getCollectionSelected(), relationship, targetRelationship);

                try {
                    codeGenerator.marshalling();
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }

                jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());

                builder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }

            private Relationship createAnotherRelationShipIfNeed(Relationship relationship,Entity parentEntity) {

                Relationship targetRelationship = null;

                CardinalityType cardinalityType = relationship.getCardinalityType();
                DirectionalityType directionalityType = relationship.getDirectionalityType();

                if (!relationship.getTargetEntity().equals(parentEntity) && (directionalityType.equals(DirectionalityType.BIDIRECTIONAL))) {
                    Entity targetEntity = relationship.getTargetEntity();
                    targetRelationship = new Relationship();
                    targetRelationship.setName(relationship.getName());
                    targetRelationship.setDirectionalityType(directionalityType);
                    targetRelationship.setTargetEntity(parentEntity);
                    switch (cardinalityType) {
                        case ONE_TO_ONE:
                            targetRelationship.setCardinalityType(CardinalityType.ONE_TO_ONE);
                            break;
                        case ONE_TO_MANY:
                            targetRelationship.setCardinalityType(CardinalityType.Many_TO_ONE);
                            break;
                        case Many_TO_ONE:
                            targetRelationship.setCardinalityType(CardinalityType.ONE_TO_MANY);
                            break;
                        case MANY_TO_MANY:
                            targetRelationship.setCardinalityType(CardinalityType.MANY_TO_MANY);
                            break;
                    }
                }
                return targetRelationship;
            }

            /**
             * Set Collection Types of both side of a relationship
             * */
            private void setCollectionTypes(CardinalityType cardinalityType, CollectionType collectionType, Relationship relationship, Relationship targetRelationship) {
                switch (cardinalityType) {
                    case ONE_TO_ONE:
                        relationship.setName(StringUtils.toCamelCase(relationship.getTargetEntity().getName()));
                        relationship.setCollectionType(CollectionType.NOTHING);
                        if (targetRelationship != null) {
                            targetRelationship.setName(StringUtils.toCamelCase(targetRelationship.getTargetEntity().getName()));
                            targetRelationship.setCollectionType(CollectionType.NOTHING);
                        }
                        break;
                    case ONE_TO_MANY:
                        relationship.setName(StringUtils.toCamelCase(relationship.getTargetEntity().getName()) + "s");
                        relationship.setCollectionType(collectionType);
                        if (targetRelationship != null) {
                            targetRelationship.setName(StringUtils.toCamelCase(targetRelationship.getTargetEntity().getName()));
                            targetRelationship.setCollectionType(CollectionType.NOTHING);
                        }
                        break;
                    case Many_TO_ONE:
                        relationship.setName(StringUtils.toCamelCase(relationship.getTargetEntity().getName()));
                        relationship.setCollectionType(CollectionType.NOTHING);
                        if (targetRelationship != null) {
                            targetRelationship.setName(StringUtils.toCamelCase(targetRelationship.getTargetEntity().getName()) + "s");
                            targetRelationship.setCollectionType(collectionType);
                        }
                        break;
                    case MANY_TO_MANY:
                        relationship.setName(StringUtils.toCamelCase(relationship.getTargetEntity().getName()) + "s");
                        relationship.setCollectionType(collectionType);
                        if (targetRelationship != null) {
                            targetRelationship.setName(StringUtils.toCamelCase(targetRelationship.getTargetEntity().getName()) + "s");
                            targetRelationship.setCollectionType(collectionType);
                        }
                        break;
                }
            }

        });
        builder.showModal(true);
    }
}
