package ir.sk.jcg.jcgengine.model.project;

import java.util.Set;

/**
 * An Interface for package management
 *
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/1/2016
 */
public interface Packageable<T extends SchemaItem> {
    Set<Package<T>> getPackages();

    void setPackages(Set<Package<T>> packages);

    void addPackage(Package<T> t);

    void addPackageAll(Set<? extends Package<T>> packages);

    void removePackage(Package<T> t);
}
