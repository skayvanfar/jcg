package ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology;

import ir.sk.jcg.jcgcommon.enums.EnumBase;
import ir.sk.jcg.jcgengine.model.platform.technology.mvcTechnology.SpringMVC.SpringMVCHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandlerEnumBase;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
@XmlSeeAlso({SpringMVCHandler.class})
public abstract class MVCTechnologyHandler extends TechnologyHandler {

    public static enum MVCTechnologyHandlerType implements EnumBase, TechnologyHandlerEnumBase {

        SPRING_MVC(0, "Spring MVC"),
        STRUTS(1, "Struts");

        private Integer value;
        private String desc;

        private MVCTechnologyHandlerType(Integer value, String desc) {
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

        public static MVCTechnologyHandlerType valueOf(Integer type) {
            for (MVCTechnologyHandlerType code : MVCTechnologyHandlerType.values()) {
                if (type == code.getValue()) {
                    return code;
                }
            }
            return null;
        }

        public static MVCTechnologyHandlerType valueOfs(String type) {
            for (MVCTechnologyHandlerType code : MVCTechnologyHandlerType.values()) {
                if (type == code.getDescription()) {
                    return code;
                }
            }
            return null;
        }

        @Override
        public TechnologyHandler technologyHandlerBuilder() {
            MVCTechnologyHandler mvcTechnology = null;
            switch (value) {
                case 0:
                    mvcTechnology = new SpringMVCHandler(); // Todo: must redefine.
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

}
