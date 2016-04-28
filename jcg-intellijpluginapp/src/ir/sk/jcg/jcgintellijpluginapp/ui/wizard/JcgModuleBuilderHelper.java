package ir.sk.jcg.jcgintellijpluginapp.ui.wizard;

import com.intellij.openapi.vfs.VirtualFile;
import ir.sk.jcg.jcgengine.Generator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;


/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/20/2016
 */
public class JcgModuleBuilderHelper {

    private static final Logger logger = LoggerFactory.getLogger(JcgModuleBuilderHelper.class);

   // private final Project jcgProject;
    private final Generator generator;

    private final String commandName;
    private com.intellij.openapi.project.Project intellijProject;
    private VirtualFile root;

    public JcgModuleBuilderHelper(@NotNull Generator generator, String commaneName, com.intellij.openapi.project.Project intellijProject, VirtualFile root) {
        this.generator = generator;
        this.intellijProject = intellijProject;
        this.root = root;
        this.commandName = commaneName;
    }

    /**
     * Call Engine to make base Architecture
     * */
    public void configure() {
        generator.getArchitecture().createBaseArchitecture();

        try {
            boolean result = generator.marshalling();
            if (result)
                logger.info("Marshaling finished correctly.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
