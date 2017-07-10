package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Project extends ModelElement implements Serializable {

    private final ResourceBundle messagesBundle = java.util.ResourceBundle.getBundle("messages/messages");

    // base info
    @Prop(label = "Persian Name", editable = true, required = true)
    private String persianName;

    @Prop(label = "Package Prefix", editable = false, required = true)
    private String packagePrefix;

    @Prop(label = "Config Package", editable = false, required = true)
    private String configPackage;

    @Prop(label = "Table Name Pattern", editable = true, required = true)
    private String tableNamePattern;

    private Schema<Entity> entitiesSchema = new Schema<>();
    private Schema<View> viewsSchema = new Schema<>();

    public Project() {
        super();
        entitiesSchema.setName(messagesBundle.getString("project.entitiesSchema.name"));
        viewsSchema.setName(messagesBundle.getString("project.viewsSchema.name"));
        configPackage = messagesBundle.getString("project.configPackage");
    }

    public String getPersianName() {
        return persianName;
    }

    @XmlAttribute
    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    @XmlAttribute
    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getConfigPackage() {
        return configPackage;
    }

    public void setConfigPackage(String configPackage) {
        this.configPackage = configPackage;
    }

    public String getTableNamePattern() {
        return tableNamePattern;
    }

    @XmlAttribute
    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    public Schema<Entity> getEntitiesSchema() {
        return entitiesSchema;
    }

    @XmlElement(name = "entityModel")
    public void setEntitiesSchema(Schema<Entity> entitiesSchema) {
        this.entitiesSchema = entitiesSchema;
    }

    public Schema<View> getViewsSchema() {
        return viewsSchema;
    }

    @XmlElement(name = "viewModel")
    public void setViewsSchema(Schema<View> viewsSchema) {
        this.viewsSchema = viewsSchema;
    }

}
