package ir.sk.jcg.jcgengine.model.platform.technology.xmlAdapter;

import ir.sk.jcg.jcgengine.model.platform.technology.BuildTechnology;
import ir.sk.jcg.jcgengine.model.platform.technology.Technology;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/26/2016
 */
public class TechnologyAdapter  extends XmlAdapter<Technology,Technology> {

    @Override
    public Technology unmarshal(Technology v) throws Exception {
        return null;
    }

    @Override
    public Technology marshal(Technology v) throws Exception {
        return null;
    }
}
