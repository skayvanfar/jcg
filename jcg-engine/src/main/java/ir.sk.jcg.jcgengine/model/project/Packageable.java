package ir.sk.jcg.jcgengine.model.project;

import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/1/2016
 */
public interface Packageable<T extends SubModelElement> {
    List<Package<T>> getPackages();
    void setPackages(List<Package<T>> packages);
    void addPackage(Package<T> t);
    void removePackage(Package<T> t);
}
