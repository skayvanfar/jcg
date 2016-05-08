package ir.sk.jcg.jcgengine.model.platform.technology.xmlAdapter;

import ir.sk.jcg.jcgengine.model.platform.technology.TechnologyHandler;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/26/2016
 */
public class TechnologyAdapter  extends XmlAdapter<TechnologyHandler,TechnologyHandler> {

    @Override
    public TechnologyHandler unmarshal(TechnologyHandler v) throws Exception {
        return null;
    }

    @Override
    public TechnologyHandler marshal(TechnologyHandler v) throws Exception {
        return null;
    }
}
