package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class JcgPropertiesSaveAction extends NodeAction {

    private static final Logger logger = LoggerFactory.getLogger(JcgPropertiesSaveAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
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
