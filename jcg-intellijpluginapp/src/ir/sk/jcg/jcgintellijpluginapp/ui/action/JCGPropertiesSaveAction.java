package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.sk.jcg.jcgengine.JavaGenerator;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.JcgProjectComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/6/2016
 */
public class JCGPropertiesSaveAction extends NodeAction {

    private static final Logger logger = LoggerFactory.getLogger(JCGPropertiesSaveAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        JcgProjectComponent jcgProjectComponent = JcgProjectComponent.getInstance(e.getProject());
        try {
            jcgProjectComponent.getGenerator().marshallingProject();
        } catch (JAXBException e1) {
            e1.printStackTrace();
            logger.error("buildTemplate error in template : " + e);
        }
        jcgProjectComponent.reloadJcgTree();
    }
}
