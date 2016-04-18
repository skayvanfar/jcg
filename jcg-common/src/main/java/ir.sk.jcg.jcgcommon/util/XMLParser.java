package ir.sk.jcg.jcgcommon.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by sad.keyvanfar on 4/13/2016.
 */
public class XMLParser {

    public static <T> void marshaling(File file, T t) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(t, file);
        jaxbMarshaller.marshal(t, System.out);
    }

    public static <T> T unmarshalling(File file, Class c) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        T t = (T) jaxbUnmarshaller.unmarshal(file);
        return t;
    }
}
