package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgengine.CodeGenerator;
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

        Element element = (Element) jcgProjectComponent.currentSelectedNodeUserObject();
        CodeGenerator codeGenerator = jcgProjectComponent.getCodeGenerator();

        if (element instanceof Relationship) {
            TreePath treePath = jcgProjectComponent.getSelectionPath();
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treePath.getParentPath().getLastPathComponent();
            Entity parentEntity = (Entity) parentNode.getUserObject();

            Relationship relationship = (Relationship) element;
            RelationshipType relationshipType = relationship.getRelationshipType();
            if (relationshipType == RelationshipType.ONE_TO_ONE_BIDIRECTIONAL
                    || relationshipType == RelationshipType.MANY_TO_ONE_BIDIRECTIONAL
                    || relationshipType == RelationshipType.MANY_TO_MANY_BIDIRECTIONAL) {
                Entity targetEntity = relationship.getTargetEntity();
                Relationship targetRelationship = new Relationship();
                targetRelationship.setTargetEntity(parentEntity);
                switch (relationshipType) {
                    case ONE_TO_ONE_BIDIRECTIONAL:
                        targetRelationship.setRelationshipType(RelationshipType.ONE_TO_ONE_BIDIRECTIONAL);
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

        jcgProjectComponent.setPropertiesModifiedElement();

        try {
            jcgProjectComponent.getCodeGenerator().marshallingProject();
        } catch (JAXBException e1) {
            e1.printStackTrace();
            logger.error("buildTemplate error in template : " + e);
        }
        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
    }
}
