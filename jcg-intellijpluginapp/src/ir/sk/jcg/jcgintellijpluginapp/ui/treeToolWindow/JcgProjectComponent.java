package ir.sk.jcg.jcgintellijpluginapp.ui.treeToolWindow;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.DoubleClickListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import ir.sk.jcg.jcgengine.Generator;
import ir.sk.jcg.jcgengine.JavaGenerator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgProjectComponent extends DoubleClickListener implements ProjectComponent {

    private static final Logger logger = LoggerFactory.getLogger(JavaGenerator.class);

    private Project intellijProject;
    private Generator generator;
    private TreePanel treePanel;

//    public ir.sk.jcg.jcgengine.model.project.Project getJcgProject() {
//        return jcgProject;
//    }
//
//    public void setJcgProject(ir.sk.jcg.jcgengine.model.project.Project jcgProject) {
//        this.jcgProject = jcgProject;
//    }

    public JcgProjectComponent(Project intellijProject) {
        this.intellijProject = intellijProject;
    }

    public static JcgProjectComponent getInstance(Project project) {
        return project.getComponent(JcgProjectComponent.class);
    }

    @Override
    public void projectOpened() {
        try {
            initGenerator(); // load project from xml
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        initToolWindow();
    }

    /**
     * initGenerator
     * */
    private void initGenerator() throws JAXBException {
        generator = new JavaGenerator();
        generator.setBaseDir(intellijProject.getBasePath());

        if(generator.isProjectJcg()) { // When project is Jcg project
            boolean result = generator.unmarshalling();
            if (result)
                logger.info("Unmarshaling finished correctly.");// TODO: 4/28/2016
        }
    }

    /**
     * initToolWindow
     * */
    public void initToolWindow() {
      //  final ZkConfigPersistence config = ZkConfigPersistence.getInstance(project);
        ir.sk.jcg.jcgengine.model.project.Project project1 = new ir.sk.jcg.jcgengine.model.project.Project();
        project1.setName("project");
        ToolWindow toolWindow = ToolWindowManager.getInstance(intellijProject).registerToolWindow("JCG Tree", false, ToolWindowAnchor.LEFT);
        toolWindow.setTitle("JCG Tree");
     //   toolWindow.setIcon(rootIcon);

        final ContentManager contentManager = toolWindow.getContentManager();
        treePanel = new TreePanel(project1);
        final Content content = contentManager.getFactory().createContent(treePanel, null, false);
        contentManager.addContent(content);

        treePanel.initJcgTree(); // create project tree
    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {
        System.out.println();
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "JcgProjectComponent";
    }

    @Override
    protected boolean onDoubleClick(MouseEvent event) {
        return false;
    }

}
