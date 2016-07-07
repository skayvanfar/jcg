package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/4/2016
 */
public abstract class CreateNodeAction extends NodeAction {

    private String nodeType;
    protected DialogBuilder builder;

    protected CreateNewNodePanel addNodePanel;

    public CreateNodeAction(String nodeType) {
        super("Create " + nodeType);
        this.nodeType = nodeType;

        addNodePanel = new CreateNewNodePanel(nodeType);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        addNodePanel.clearNodeName();
        addNodePanel.clearNodeName();
        builder = new DialogBuilder(e.getProject());
        builder.setTitle("Create Property");
        builder.setPreferredFocusComponent(addNodePanel);
        builder.setCenterPanel(addNodePanel);
    }

    protected String correctName(String nodeName) {
        if (nodeName.startsWith("/")) {
            nodeName = nodeName.substring(1);
        }
        if (nodeName.endsWith("/")) {
            nodeName = nodeName.substring(0, nodeName.length() - 1);
        }
        return nodeName;
    }
}
