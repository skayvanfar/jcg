package ir.sk.jcg.jcgengine.model.project.component;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgengine.model.project.Component;
import ir.sk.jcg.jcgengine.model.project.Property;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 9/23/2017.
 */
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class FileUpload extends Component {

    public FileUpload() {
        super.name = "FileUpload";
    }

    private String fileName;

    private Property fileNameProperty;

    private String contentType;

    private Property contentTypeProperty;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
