package ir.sk.jcg.jcgengine.model.platform;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class Dependency { // TODO: 9/26/2016 Value Object

    private String groupId;
    private String artifactId;
    private String version;
    private String scope;
    private Set<Exclusion> exclusionSet;
    //   private String comment; // TODO: 5/21/2016 may use later

    public Dependency() {
    }

    public Dependency(String groupId, String artifactId, String version, String scope) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.scope = scope;
        exclusionSet = new HashSet<>();
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

    public String getVersion() {
        return version;
    }

    @XmlAttribute(name = "version", required = true)
    public void setVersion(String version) {
        this.version = version;
    }

    public String getScope() {
        return scope;
    }

    @XmlAttribute(name = "scope", required = true)
    public void setScope(String scope) {
        this.scope = scope;
    }

    public Set<Exclusion> getExclusionSet() {
        return exclusionSet;
    }

    @XmlElement(name = "exclusion")
    public void setExclusionSet(Set<Exclusion> exclusionSet) {
        this.exclusionSet = exclusionSet;
    }

    public void addExclusion(Exclusion exclusion) {
        if (!exclusionSet.contains(exclusion))
            exclusionSet.add(exclusion);
    }

    public void removeExclusion(Exclusion exclusion) {
        if (exclusionSet.contains(exclusion))
            exclusionSet.remove(exclusion);
    }
}
