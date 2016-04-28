package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgcommon.util.XMLParser;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.project.Entity;
import ir.sk.jcg.jcgengine.model.project.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class JavaGenerator implements Generator {

    private static final Logger logger = LoggerFactory.getLogger(JavaGenerator.class);


    private final static String JCG_CONFIG_DIR = "jcg";
    private final static String JCG_CONFIG_PROJECT_FILE_NAME = "project.xml";
    private final static String JCG_CONFIG_ARCHITECTURE_FILE_NAME = "architecture.xml";

    private String baseDir;
    private File baseXmlDir = null;

    @Override
    public String getBaseDir() {
        return baseDir;
    }

    @Override
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
        setBaseXmlDir(baseDir);
    }

    @Override
    public File getBaseXmlDir() {
        return baseXmlDir;
    }

    @Override
    public void setBaseXmlDir(String baseDir) {
        this.baseDir = baseDir;
        this.baseXmlDir = new File(baseDir + File.separator + JCG_CONFIG_DIR);

    }

    private Project jcgProject;
    private Architecture architecture;

    public JavaGenerator() {
        this.jcgProject = new Project();
    }

    @Override
    public boolean createBaseProject() {
        architecture.createBaseArchitecture();
        return true;
    }

    @Override
    public boolean marshalling() throws JAXBException {
        logger.info("marshaling ... ");
        if(!baseXmlDir.exists())
            baseXmlDir.mkdir();
        marshallingProject();
        marshallingArchitecture();
        logger.info("marshaling finished.");
        return true;
    }

    @Override
    public boolean unmarshalling() throws JAXBException {
        logger.info("unmarshaling ... ");
        unmarshallingProject();
        unmarshallingArchitecture();

        architecture.initialize(baseDir, jcgProject.getPackagePrefix());
        logger.info("unmarshaling finished.");

        return true;
    }

    @Override
    public boolean marshallingProject() throws JAXBException {
        XMLParser.marshaling(new File(baseXmlDir + File.separator + JCG_CONFIG_PROJECT_FILE_NAME), jcgProject);
        return true;
    }

    @Override
    public boolean unmarshallingProject() throws JAXBException {
        jcgProject = XMLParser.unmarshalling(new File(baseXmlDir + File.separator + JCG_CONFIG_PROJECT_FILE_NAME), Project.class);
        return true;
    }

    @Override
    public boolean marshallingArchitecture() throws JAXBException {
        XMLParser.marshaling(new File(baseXmlDir + File.separator + JCG_CONFIG_ARCHITECTURE_FILE_NAME), architecture);
        return true;
    }

    @Override
    public boolean unmarshallingArchitecture() throws JAXBException {
        architecture = XMLParser.unmarshalling(new File(baseXmlDir + File.separator + JCG_CONFIG_ARCHITECTURE_FILE_NAME), Architecture.class);
        return true;
    }

    @Override
    public boolean isProjectJcg() { // TODO: 4/28/2016  must change Evaluation
        if (baseXmlDir.exists())
            return true;
        else
            return false;
    }

    @Override
    public Project getJcgProject() {
        return jcgProject;
    }

    @Override
    public void setJcgProject(Project jcgProject) {
        this.jcgProject = jcgProject;
    }

    @Override
    public Architecture getArchitecture() {
        return architecture;
    }

    @Override
    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    @Override
    public boolean addEntity(Entity entity) {
//        VirtualFile vf_projectModulefile = LocalFileSystem.getInstance().findFileByPath("C:/a.txt");
//        System.out.println(vf_projectModulefile.isDirectory());
        return false;
    }
}
