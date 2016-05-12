package ir.sk.jcg.jcgengine.model.project;

import ir.sk.jcg.jcgcommon.PropertyView.annotation.Editable;
import ir.sk.jcg.jcgcommon.PropertyView.annotation.Prop;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Editable
public class Project extends ModelElement implements Serializable {

    // base info
    @Prop(label = "Persian Name", required = true)
    private String persianName;

    @Prop(label = "Package Prefix", required = true)
    private String packagePrefix;

    @Prop(label = "Table Name Pattern", required = true)
    private String tableNamePattern;

    private Schema<Entity> entitiesSchema = new Schema<>();
    private Schema<View> viewsSchema = new Schema<>();

    public Project() {
        super();
        entitiesSchema.setName("Domain Schema");
        viewsSchema.setName("Business Schema");
    }

    /**
     * Copy constructor
     * */
    public Project(Project anotherProject) {
        super(anotherProject);
        this.persianName = anotherProject.getPersianName();
        this.packagePrefix = anotherProject.getPackagePrefix();
        this.tableNamePattern = anotherProject.getTableNamePattern();
        this.entitiesSchema = anotherProject.getEntitiesSchema();
        this.viewsSchema = anotherProject.getViewsSchema();
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
