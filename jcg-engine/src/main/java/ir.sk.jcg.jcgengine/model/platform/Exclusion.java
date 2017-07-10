package ir.sk.jcg.jcgengine.model.platform;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Exclusion from a dependency
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/21/2016
 */
public class Exclusion {

    private String groupId;
    private String artifactId;

    public Exclusion() {
    }

    public Exclusion(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    @XmlAttribute(name = "groupId", required = true)
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    @XmlAttribute(name = "artifactId", required = true)
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
