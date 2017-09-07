package ir.sk.jcg.lib.jcglibspringmvchandler.file.web;

import ir.sk.jcg.jcglibcommon.persistence.PersistenceException;
import ir.sk.jcg.jcglibcommon.persistence.PersistenceExceptionType;
import ir.sk.jcg.jcglibcommon.util.Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/9/2017.
 */
public class TempFileAccess {

    private static TempFileAccess instance;
    private String temp;

    public TempFileAccess() {
        instance = this;
    }

    public static TempFileAccess i() {
        return instance;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) throws PersistenceException {
        try {
            this.temp = Utils.createRoot(temp).toAbsolutePath().toString();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.Fatal, "can not create temp directory " + temp);
        }
    }

    public Path upload(MultipartFile multipartFile) throws IOException {
        return upload(multipartFile.getInputStream(), null);
    }

    public Path upload(byte[] fileBytes) throws IOException {
        return upload(new ByteArrayInputStream(fileBytes), null);
    }

    public Path upload(MultipartFile multipartFile, String extension) throws IOException {
        return upload(multipartFile.getInputStream(), extension);
    }

    public Path uploadWithExtension(MultipartFile multipartFile) throws IOException {
        final String filename = multipartFile.getOriginalFilename();
        final String extension;
        if (filename.contains("."))
            extension = filename.substring(filename.lastIndexOf('.'));
        else extension = null;
        return upload(multipartFile.getInputStream(), extension);
    }

    public Path upload(byte[] fileBytes, String extension) throws IOException {
        return upload(new ByteArrayInputStream(fileBytes), extension);
    }

    public Path upload(InputStream inputStream) throws IOException {
        return upload(inputStream, null);
    }

    public Path upload(InputStream inputStream, String extension) throws IOException {
        final Path tempPath = Files.createTempFile(Paths.get(temp), "upload",
                extension == null ? ".tmp" : "." + extension);
        Files.copy(inputStream, tempPath, StandardCopyOption.REPLACE_EXISTING);
        return tempPath;
    }

    public void move(ICopyStream copier, String addressInTemp)
            throws PersistenceException {
        try {
            final Path path = Paths.get(getTemp(), addressInTemp);
            if (Files.exists(path)) {
                try (InputStream inputStream = Files.newInputStream(path)) {
                    copier.copy(inputStream);
                }
                Files.delete(path);
            }
        } catch (IOException e) {
            throw new PersistenceException(PersistenceExceptionType.IO, e);
        }
    }

    public Path duplicate(String addressInTemp) throws IOException {
        final Path path = Paths.get(getTemp(), addressInTemp);
        final String[] splits = addressInTemp.split("\\.");
        String extension = splits.length > 1 ? splits[splits.length - 1] : "";
        final Path duplicatePath = Files.createTempFile(Paths.get(temp), "t_",
                extension.length() > 0 ? "." + extension : "");
        Files.copy(path, duplicatePath, StandardCopyOption.REPLACE_EXISTING);
        return duplicatePath;
    }

    public void clean(Long timeAgo) {
        if (timeAgo == null) timeAgo = 60l * 60 * 1000;//one hour
        final long someTimeAgo = System.currentTimeMillis() - timeAgo;
        Path tempPath = Paths.get(getTemp());
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(tempPath)) {
            for (Path path : directoryStream) {
                BasicFileAttributes basicAttr = Files.readAttributes(path, BasicFileAttributes.class);
                FileTime creationTime = basicAttr.creationTime();

                if (creationTime.toMillis() < someTimeAgo)
                    try {
                        Files.delete(path);
                    } catch (Throwable ignored) {
                    }
            }
        } catch (IOException ignored) {
        }
    }

    public static interface ICopyStream {
        void copy(InputStream input) throws PersistenceException;
    }
}
