package ir.sk.jcg.jcgengine.velocity;

import ir.sk.jcg.jcgcommon.util.FileUtils;
import ir.sk.jcg.jcgengine.codeFormatter.CodeFormatter;
import ir.sk.jcg.jcgengine.codeFormatter.JavaCodeFormatter;
import ir.sk.jcg.jcgengine.exception.ExporterException;
import ir.sk.jcg.jcgengine.exception.ModelElementAlreadyExistException;
import ir.sk.jcg.jcgengine.regexp.GeneratedCodeType;
import ir.sk.jcg.jcgengine.regexp.JavaRegExSrc;
import ir.sk.jcg.jcgengine.regexp.RegExSrc;

import java.io.*;
import java.util.Properties;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 7/15/2017
 */
public class AddElementToSectionGenerateTemplate extends GenerateTemplate {

    private GeneratedCodeType generatedCodeType;

    public AddElementToSectionGenerateTemplate(String name, String outfilePath, GeneratedCodeType generatedCodeType) {
        super(name, outfilePath);
        this.generatedCodeType = generatedCodeType;
    }

    @Override
    public void mergeTemplate() {

        // Generate element
        StringWriter stringWriter = VelocityTemplate.mergeTemplateInWriter(generatedCodeType.getPathTemplate(), velocityContext);

        // Get content of file
       // ClassLoader classLoader = getClass().getClassLoader();
      //  InputStream inputStream = classLoader.getResourceAsStream(outfilePath);

        Properties props = new Properties();
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(outfilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newFileContent = "";
        String fileContent = FileUtils.getFileContentByInputStream(in);
        RegExSrc regExSrc = new JavaRegExSrc(fileContent);
        try {
            newFileContent = regExSrc.addModelElement(generatedCodeType, stringWriter.toString());
        } catch (ModelElementAlreadyExistException e) {
            e.printStackTrace(); // TODO: 9/15/2017
            return;
        }

        // Write to file again
        FileUtils.writeToFile(outfilePath, newFileContent);

        // format just for java files // TODO: 9/15/2017 Must be in one place
        if (outfilePath.endsWith(".java")) {
            CodeFormatter codeFormatter = new JavaCodeFormatter(); // TODO: 7/13/2017 Must be singleton
            try {
                boolean result = codeFormatter.formatFile(new File(outfilePath));
            } catch (ExporterException e) {
                e.printStackTrace();
            }
        }
    }
}
