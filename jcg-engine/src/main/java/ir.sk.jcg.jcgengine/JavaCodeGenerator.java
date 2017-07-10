package ir.sk.jcg.jcgengine;

import ir.sk.jcg.jcgcommon.util.XMLParser;
import ir.sk.jcg.jcgengine.model.platform.architecture.Architecture;
import ir.sk.jcg.jcgengine.model.project.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class JavaCodeGenerator implements CodeGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JavaCodeGenerator.class);

    //   private JAXBContext jaxbContext; // TODO: 5/5/2016 add marshaler hear
    //   private Marshaller marshaller;
    //   private Unmarshaller unmarshaller;

    private Project jcgProject;
    private Architecture architecture;

    // base directory path of project
    private String baseDir;
    // Path of model xml files
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

    public JavaCodeGenerator() {
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
        if (!baseXmlDir.exists())
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

        architecture.initialize(baseDir, jcgProject.getPackagePrefix(), jcgProject.getConfigPackage()); // TODO: 5/20/2016 Configuration must be dynamic
        logger.info("unmarshaling finished.");

        return true;
    }

    @Override
    public boolean marshallingProject() throws JAXBException {
        XMLParser.marshaling(new File(baseXmlDir + File.separator + JCG_CONFIG_PROJECT_FILE_NAME), jcgProject);
        //   File outputFile = new File(baseXmlDir + File.separator + JCG_CONFIG_PROJECT_FILE_NAME);
        //    this.marshaller.marshal(jcgProject, new StreamResult(outputFile));
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
    public List<DomainImplElement> addEntity(Entity entity, String packagePath) {
        return architecture.createEntity(entity, packagePath);
    }

    @Override
    public List<BusinessImplElement> addView(View view, String packagePath) {
        return architecture.createView(view, packagePath);
    }

//    private void initJAXBContext() {
//        try {
//            this.jaxbContext = JAXBContext.newInstance(Project.class);
//            this.marshaller = jaxbContext.createMarshaller();
//            this.unmarshaller = jaxbContext.createUnmarshaller();
//
//            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }
}
