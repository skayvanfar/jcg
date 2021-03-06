package ir.sk.jcg.jcgintellijpluginapp.ui.util.execution;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public class RunnableHelper {

    public static void runWhenInitialized(final Project project, final Runnable r) {
        if (project.isDisposed()) return;

        if (!project.isInitialized()) {
            StartupManager.getInstance(project).registerStartupActivity(new RunnableHelper.WriteAction(r));
            return;
        }

        RunnableHelper.runWriteCommand(project, r);
    }

    public static void runReadCommand(Project project, Runnable cmd) {
        CommandProcessor.getInstance().executeCommand(project, new ReadAction(cmd), "Jcg", "Components");
    }


    public static void runWriteCommand(Project project, Runnable cmd) {
        CommandProcessor.getInstance().executeCommand(project, new WriteAction(cmd), "Jcg", "Components");
    }


    public static class ReadAction implements Runnable {

        public ReadAction(Runnable cmd) {
            this.cmd = cmd;
        }

        public void run() {
            ApplicationManager.getApplication().runReadAction(cmd);
        }

        Runnable cmd;
    }


    public static class WriteAction implements Runnable {

        public WriteAction(Runnable cmd) {
            this.cmd = cmd;
        }

        public void run() {
            ApplicationManager.getApplication().invokeLater(new Runnable() {
                public void run() {
                    ApplicationManager.getApplication().runWriteAction(cmd);
                }
            });
        }

        Runnable cmd;
    }

    private RunnableHelper() {
    }

}
