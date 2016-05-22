package ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology;

import ir.sk.jcg.jcgengine.model.platform.technology.SecurityTechnology.SpringSecurity.SpringSecurityHandler;
import ir.sk.jcg.jcgengine.model.platform.technology.SpringTechnology.Config;
import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/21/2016
 */
@XmlSeeAlso({SpringSecurityHandler.class})
public abstract class SecurityTechnologyHandler extends TechnologyHandler {

    public SecurityTechnologyHandler() {
        super("Security Technology");
    }

    public SecurityTechnologyHandler(String name) {
        super(name);
    }

}
