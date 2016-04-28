package ir.sk.jcg.jcgintellijpluginapp.ui.util.execution;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/19/2016
 */
public class ModalTaskImpl extends Task.Modal {

    private boolean done = false;
    private CallBackHandler callback;

    public ModalTaskImpl(@Nullable Project project, @NotNull String title, CallBackHandler callback) {
        super(project, title, true);
        this.callback = callback;
    }

    @Override
    public void run(@NotNull ProgressIndicator progressIndicator) {
        done = callback.executeTask(progressIndicator);
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
    }

    @Override
    public void onCancel() {
        super.onCancel();
        done = false;
    }

    public boolean isDone() {
        return done;
    }

}
