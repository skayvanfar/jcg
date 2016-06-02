package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.project.*;
import ir.sk.jcg.jcgengine.model.project.enums.CardinalityType;
import ir.sk.jcg.jcgengine.model.project.enums.DirectionalityType;
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
        super.actionPerformed(e);
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());

        jcgProjectComponent.setPropertiesModifiedElement();

        doBeforeSave(jcgProjectComponent);

        marshalingAndReloadTree();
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
            CardinalityType cardinalityType = relationship.getCardinalityType();
            DirectionalityType directionalityType = relationship.getDirectionalityType();

            if (!relationship.getTargetEntity().equals(parentEntity) && (directionalityType.equals(DirectionalityType.BIDIRECTIONAL))) {
                Entity targetEntity = relationship.getTargetEntity();
                Relationship targetRelationship = new Relationship();
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
                targetEntity.addRelation(targetRelationship);
            }
        }
    }
}
