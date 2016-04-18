package ir.sk.jcg.jcgengine.model.platform.architecture;

import ir.sk.jcg.jcgengine.model.platform.technology.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/13/2016
 */
public class ThreeLayerArchitecture implements Architecture {

    private BuildTechnology buildTechnology;
    private ORMTechnology ormTechnology;
    private MVCTechnology mvcTechnology;
    private ViewTechnology viewTechnology;
    private ClientViewTechnology clientViewTechnology;

    public BuildTechnology getBuildTechnology() {
        return buildTechnology;
    }

    public void setBuildTechnology(BuildTechnology buildTechnology) {
        this.buildTechnology = buildTechnology;
    }

    public ORMTechnology getOrmTechnology() {
        return ormTechnology;
    }

    public void setOrmTechnology(ORMTechnology ormTechnology) {
        this.ormTechnology = ormTechnology;
    }

    public MVCTechnology getMvcTechnology() {
        return mvcTechnology;
    }

    public void setMvcTechnology(MVCTechnology mvcTechnology) {
        this.mvcTechnology = mvcTechnology;
    }

    public ViewTechnology getViewTechnology() {
        return viewTechnology;
    }

    public void setViewTechnology(ViewTechnology viewTechnology) {
        this.viewTechnology = viewTechnology;
    }

    public ClientViewTechnology getClientViewTechnology() {
        return clientViewTechnology;
    }

    public void setClientViewTechnology(ClientViewTechnology clientViewTechnology) {
        this.clientViewTechnology = clientViewTechnology;
    }

    @Override
    public void createView() {

    }

    @Override
    public void createBaseArchitecture() {
        buildTechnology.createBasePlatform();
        try {
            ormTechnology.createBasePlatform();
        } catch (Exception e) { // todo
            e.printStackTrace();
        }
    }
}
