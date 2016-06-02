package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/8/2016
 */
public abstract class NodeAction extends AnAction {

    private static final Logger logger = LoggerFactory.getLogger(JcgElementPropertiesSaveAction.class);

    JcgProjectComponent jcgProjectComponent;

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        jcgProjectComponent = JcgProjectComponent.getInstance(anActionEvent.getProject());
    }

    /**
     *
     */
    void marshalingAndReloadTree() {
        CodeGenerator codeGenerator = jcgProjectComponent.getCodeGenerator();
        try {
            codeGenerator.marshalling();
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("buildTemplate error in template : " + e);
        }

        jcgProjectComponent.reloadJcgTree(jcgProjectComponent.getSelectionPath());
    }

}
