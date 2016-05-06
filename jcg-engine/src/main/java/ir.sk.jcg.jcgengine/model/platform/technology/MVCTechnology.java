package ir.sk.jcg.jcgengine.model.platform.technology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.Dependency;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.File;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({SpringMVC.class})
public abstract class MVCTechnology extends Technology {

    public static enum MVCTechnologyType implements EnumBase, TechnologyEnumBase {

        SPRING_MVC(0, "Spring MVC"),
        STRUTS(1, "Struts");

        private Integer value;
        private String desc;

        private MVCTechnologyType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public String getDescription() {
            return desc;
        }

        public static MVCTechnologyType valueOf(Integer type) {
            for (MVCTechnologyType code : MVCTechnologyType.values()) {
                if (type == code.getValue()) {
                    return code;
                }
            }
            return null;
        }

        public static ORMTechnology.ORMTechnologyType valueOfs(String type) {
            for (ORMTechnology.ORMTechnologyType code : ORMTechnology.ORMTechnologyType.values()) {
                if (type == code.getDescription()) {
                    return code;
                }
            }
            return null;
        }

        @Override
        public Technology technologyBuilder() {
            MVCTechnology mvcTechnology = null;
            switch (value) {
                case 0:
                    mvcTechnology = new SpringMVC(); // Todo: must redefine.
                    break;
                case 1:
                    // todo: must complete.
                    break;
            }
            return mvcTechnology;
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    public MVCTechnology() {
    }

    @Override
    public void createBasePlatform() throws Exception {
        createDirectories();
        createBaseFiles();
    }

    protected abstract void createDirectories();

    protected abstract void createBaseFiles() throws Exception;
}
