package ir.sk.jcg.jcgengine.xmlParser;

import ir.sk.jcg.jcgengine.model.project.Project;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * // TODO: 5/5/2016 may use next
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/5/2016
 */
public class Marshalling<T> {

    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Marshalling(T t) {
        try {
            this.jaxbContext = JAXBContext.newInstance(t.getClass());
            this.marshaller = jaxbContext.createMarshaller();
            this.unmarshaller = jaxbContext.createUnmarshaller();

            // output pretty printed
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void marshaling(T t, File file) throws JAXBException {
        this.marshaller.marshal(t, new StreamResult(file));
    }

    public T unmarshalling(File file) throws JAXBException {
        return (T) this.unmarshaller.unmarshal(file);
    }
}
