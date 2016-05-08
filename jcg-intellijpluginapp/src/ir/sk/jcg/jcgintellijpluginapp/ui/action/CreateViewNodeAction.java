package ir.sk.jcg.jcgintellijpluginapp.ui.action;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/28/2016
 */
public class CreateViewNodeAction extends CreateNodeAction {

    public CreateViewNodeAction() {
        super("View");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        builder.setOkOperation(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
