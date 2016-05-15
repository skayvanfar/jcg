package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.enums.RelationshipType;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.JAXBException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/13/2016
 */
public class JcgElementPropertiesSaveAction extends NodeAction {

    private static final Logger logger = LoggerFactory.getLogger(JcgElementPropertiesSaveAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());

        jcgProjectComponent.setPropertiesModifiedElement();

        doBeforeSave(jcgProjectComponent);

        try {
            jcgProjectComponent.getCodeGenerator().marshalling();
        } catch (JAXBException e1) {
            e1.printStackTrace();
            logger.error("buildTemplate error in template : " + e);
        }
        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
    }

    /**
     * Do stuffs need tob done before save like bidirectional relationships
     * */
    private void doBeforeSave(JcgProjectComponent jcgProjectComponent) {
        Presentable presentable = (Presentable) jcgProjectComponent.currentSelectedNodeUserObject();

        if (presentable instanceof Relationship) {
            TreePath treePath = jcgProjectComponent.getSelectionPath();
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treePath.getParentPath().getLastPathComponent();
            Entity parentEntity = (Entity) parentNode.getUserObject();

            Relationship relationship = (Relationship) presentable;
            RelationshipType relationshipType = relationship.getRelationshipType();
            if (!relationship.getTargetEntity().equals(parentEntity) && (relationshipType.equals(RelationshipType.ONE_TO_ONE_BIDIRECTIONAL)
                    || relationshipType.equals(RelationshipType.ONE_TO_MANY_BIDIRECTIONAL)
                    || relationshipType.equals(RelationshipType.MANY_TO_ONE_BIDIRECTIONAL)
                    || relationshipType.equals(RelationshipType.MANY_TO_MANY_BIDIRECTIONAL))) {
                Entity targetEntity = relationship.getTargetEntity();
                Relationship targetRelationship = new Relationship();
                targetRelationship.setName(relationship.getName());
                targetRelationship.setTargetEntity(parentEntity);
                switch (relationshipType) {
                    case ONE_TO_ONE_BIDIRECTIONAL:
                        targetRelationship.setRelationshipType(RelationshipType.ONE_TO_ONE_BIDIRECTIONAL);
                        break;
                    case ONE_TO_MANY_BIDIRECTIONAL:
                        targetRelationship.setRelationshipType(RelationshipType.MANY_TO_ONE_BIDIRECTIONAL);
                        break;
                    case MANY_TO_ONE_BIDIRECTIONAL:
                        targetRelationship.setRelationshipType(RelationshipType.ONE_TO_MANY_BIDIRECTIONAL);
                        break;
                    case MANY_TO_MANY_BIDIRECTIONAL:
                        targetRelationship.setRelationshipType(RelationshipType.MANY_TO_MANY_BIDIRECTIONAL);
                        break;
                }
                targetEntity.addRelation(targetRelationship);
            }
        }
    }
}
