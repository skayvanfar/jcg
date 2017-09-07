package ir.sk.jcg.lib.jcglibspringmvchandler.file.web;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class NamedInput {

    public InputStream input;
    public String name;

    public NamedInput(InputStream input) {
        this.input = input;
        this.name = String.valueOf(System.currentTimeMillis());
    }

    public NamedInput(InputStream input, String name) {
        this.input = input;
        this.name = name;
    }

    public NamedInput(MultipartFile file) throws PersistenceException {
        try {
            this.input = file.getInputStream();
            this.name = file.getOriginalFilename();
        }catch (IOException ex){
            throw new PersistenceException(PersistenceExceptionType.IO, ex, "Can not open input");
        }
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
