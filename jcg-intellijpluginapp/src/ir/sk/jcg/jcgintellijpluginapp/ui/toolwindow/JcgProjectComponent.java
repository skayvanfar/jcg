package ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.DoubleClickListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import ir.sk.jcg.jcgengine.CodeGenerator;
import ir.sk.jcg.jcgengine.JavaCodeGenerator;
import ir.sk.jcg.jcgengine.model.Presentable;
import ir.sk.jcg.jcgengine.model.project.Element;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.ModelElement;
import ir.sk.jcg.jcgengine.model.project.View;
import ir.sk.jcg.jcgintellijpluginapp.ui.icon.JcgIcons;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.propertiesToolWindow.PropertiesPanel;
import ir.sk.jcg.jcgintellijpluginapp.ui.toolwindow.treeToolWindow.TreePanel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.tree.TreePath;
import javax.xml.bind.JAXBException;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/25/2016
 */
public class JcgProjectComponent extends DoubleClickListener implements ProjectComponent {

    private static final Logger logger = LoggerFactory.getLogger(JavaCodeGenerator.class);

    private Project intellijProject;
    private CodeGenerator codeGenerator;
    private TreePanel treePanel;
    private ToolWindow jcgTreeToolWindow;
    private PropertiesPanel propertiesPanel;
    private ToolWindow jcgPropertiesToolWindow;

    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    public JcgProjectComponent(Project intellijProject) {
        this.intellijProject = intellijProject;
    }

    public static JcgProjectComponent getInstance(Project project) {
        return project.getComponent(JcgProjectComponent.class);
    }

    public void updateUI(TreePath treePath) {
        treePanel.updateAndExpandUI(treePath);;
    }

    @Override
    public void projectOpened() {
        // TODO: 5/5/2016 add is this project is jcp project call two init method
        if (isCorrectJcgProject()) {
            try {
                initGenerator(); // load project from xml
                initToolWindows();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadJcgTree(TreePath treePath) {
        try {
            initGenerator();
            addContentToJcgTreeToolWindow();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        treePanel.updateAndExpandUI(treePath);
    }

    /**
     * initGenerator
     */
    private void initGenerator() throws JAXBException {
        codeGenerator = new JavaCodeGenerator();
        codeGenerator.setBaseDir(intellijProject.getBasePath());

        //   if(isCorrectJcgProject()) { // When project is Jcg project
        boolean result = codeGenerator.unmarshalling();
        if (result) {
            logger.info("Unmarshaling finished correctly.");// TODO: 4/28/2016
        }
        //   }
    }

    /**
     * initToolWindows
     */
    private void initToolWindows() {
        // init jcgTree tool window
        jcgTreeToolWindow = ToolWindowManager.getInstance(intellijProject).registerToolWindow("JCG Tree", false, ToolWindowAnchor.LEFT);
        jcgTreeToolWindow.setTitle("JCG Tree");
        jcgTreeToolWindow.setIcon(JcgIcons.JcgTreeToolWindow); // TODO: 4/28/2016 must change
        if (!jcgTreeToolWindow.isVisible()) jcgTreeToolWindow.show(() -> {
        });
        addContentToJcgTreeToolWindow();

        // init jcgProperties tool window
        jcgPropertiesToolWindow = ToolWindowManager.getInstance(intellijProject).registerToolWindow("JCG Properties", false, ToolWindowAnchor.LEFT, true);
        jcgPropertiesToolWindow.setTitle("JCG Properties");
        jcgPropertiesToolWindow.setIcon(JcgIcons.JcgTreeToolWindow); // TODO: 4/28/2016 must change
        jcgPropertiesToolWindow.setSplitMode(true, () -> {
        });
        if (!jcgPropertiesToolWindow.isVisible()) jcgPropertiesToolWindow.show(() -> {
        });
        addContentToJcgPropertiesToolWindow();
    }

    private void addContentToJcgTreeToolWindow() {
        final ContentManager contentManager = jcgTreeToolWindow.getContentManager();
        treePanel = new TreePanel(codeGenerator.getJcgProject(), codeGenerator.getArchitecture());
        treePanel.setTreeSelectionChangedListener(userObject -> {
            propertiesPanel.setPresentable((Presentable) userObject);
            if (!jcgPropertiesToolWindow.isVisible())
                jcgPropertiesToolWindow.show(() -> {
                });
        });
        final Content content = contentManager.getFactory().createContent(treePanel, null, false);
        contentManager.removeAllContents(true);
        contentManager.addContent(content);

        //      this.installOn(treePanel.getJcgTree());
        this.installOn(treePanel); // TODO: 5/4/2016 may cuase error
    }

    private void addContentToJcgPropertiesToolWindow() {
        final ContentManager contentManager = jcgPropertiesToolWindow.getContentManager();
        //   propertiesPanel = new PropertiesPanel(codeGenerator.getJcgProject());
        propertiesPanel = new PropertiesPanel();
        final Content content = contentManager.getFactory().createContent(propertiesPanel, null, false);
        contentManager.removeAllContents(true);
        contentManager.addContent(content);

        //      this.installOn(treePanel.getJcgTree());
        this.installOn(propertiesPanel); // TODO: 5/4/2016 may cuase error
    }

    /**
     * check to see jcg project is exist and valid
     */
    private boolean isCorrectJcgProject() { // TODO: 5/6/2016 must check existence and correction of project and architectur xml files
        File baseXmlDir = new File(intellijProject.getBasePath() + File.separator + CodeGenerator.JCG_CONFIG_DIR);
        return (baseXmlDir.exists());
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

    public TreePath getSelectionPath() {
        return treePanel.getSelectionPath();
    }

    /**
     * Facade
     */
    public void addNeededNodes(ModelElement modelElement) {
        treePanel.addNeededNodes(modelElement);
    }

    /**
     * Facade
     */
    public Object currentSelectedNodeUserObject() {
        return treePanel.currentSelectedNodeUserObject();
    }

    /**
     * Facade
     */
    public Object parentSelectedNodeUserObject() {
        return treePanel.parentSelectedNodeUserObject();
    }

    /**
     * Set properties
     */
    public void setPropertiesModifiedElement() {
        propertiesPanel.setModifiedElement();
    }

    /**
     * Facade
     */
    public TreePath getLeadTreePath() {
        return treePanel.getLeadTreePath();
    }

    /**
     * Facade
     */
    public List<Entity> findEntitiesByUserObject() {
        return treePanel.findEntitiesByUserObject();
    }
}
