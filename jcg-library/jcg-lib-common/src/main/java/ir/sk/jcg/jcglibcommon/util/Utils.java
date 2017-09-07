package ir.sk.jcg.jcglibcommon.util;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class Utils {

    public static Path createRoot(String base, String... subdir) throws IOException {
        if (File.separator.equals("\\") && base.startsWith("/"))
            base = "C://" + base.substring(1);
        if (base.startsWith("~"))
            base = System.getProperty("user.home") + base.substring(1);
        if (File.separator.equals("\\"))
            base = base.replace('/', '\\');
        final Path rootDirectory = Paths.get(base, subdir);
        if (!Files.exists(rootDirectory))
            Files.createDirectories(rootDirectory);
        return rootDirectory;
    }

    /**
     * It hashes a string to another. Specially useful in password hashing
     *
     * @param str String to hash
     * @return Hashed string
     */
    public static String hashString(String str) {
        try {
            if (str == null)
                return null;
            str = "p" + str + "p";
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] utf8Bytes = str.getBytes("UTF8");
            for (int i = 0; i < utf8Bytes.length; i++) {
                utf8Bytes[i] += i * i / 321;
            }
            md.update(utf8Bytes, 0, utf8Bytes.length);
            byte[] result = md.digest();
            return new String(result, "ASCII");
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * validates a string.
     *
     * @param str       string to validate
     * @param canBeNull determines it can be null or not
     * @return false, when it contains %'\&#; or its is empty after trimming.
     */
    public static boolean isValid(String str, boolean canBeNull) {
        if (canBeNull && str == null) {
            return true;
        }
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        if (str.contains("%") || str.contains("'") || str.contains("\"")
                || str.contains("&") || str.contains(";")) {
            return false;
        }
        return true;
    }

    /**
     * converts a string in persian language to a standard form. it simple
     * removes arabic letters from it.
     *
     * @param source text to convert
     * @return converted text
     */
    public static String convert2StandardString(String source) {
        if (source != null) {
            source = source.replace('ي', 'ی').replace('ہ', 'ه').replace('ۂ',
                    'ه').replace('ۃ', 'ه').replace('ە', 'ه').replace('ة', 'ه')
                    .replace('ك', 'ک').replace('أ', 'ا')
                    .replace('ؤ', 'ئ').replace('إ', 'ا').replace('ۀ', 'ه')
                    .replace('،', ',').replace('؛', ';').trim();
            // return StringEscapeUtils.escapeHtml(source);
            source = source.replaceAll("\\s+", " ").trim();
            if (source.isEmpty())
                source = null;
        }
        return source;
    }

    /**
     * compresses a folder to a tar.gz file.
     *
     * @param dirPath   path to directory
     * @param tarGzPath path you want to save tar.gz file. it must includes full file
     *                  name. for example: /home/user/test.tar.gz
     * @throws IOException
     */
    public static void compressTarGZ(File dirPath, File tarGzPath)
            throws IOException {
        FileOutputStream fOut = null;
        BufferedOutputStream bOut = null;
        GzipCompressorOutputStream gzOut = null;
        TarArchiveOutputStream tOut = null;
        try {
            System.out.println(new File(".").getAbsolutePath());
            fOut = new FileOutputStream(tarGzPath);
            bOut = new BufferedOutputStream(fOut);
            gzOut = new GzipCompressorOutputStream(bOut);
            tOut = new TarArchiveOutputStream(gzOut);
            addFileToTarGz(tOut, dirPath, "");
        } finally {
            if (tOut != null)
                tOut.finish();
            if (tOut != null)
                tOut.close();
            if (gzOut != null)
                gzOut.close();
            if (bOut != null)
                bOut.close();
            if (fOut != null)
                fOut.close();
        }

    }

    private static void addFileToTarGz(TarArchiveOutputStream tOut, File path,
                                       String base) throws IOException {
        String entryName = base + path.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(path, entryName);
        tOut.putArchiveEntry(tarEntry);

        if (path.isFile()) {
            IOUtils.copy(new FileInputStream(path), tOut);
            tOut.closeArchiveEntry();
        } else {
            tOut.closeArchiveEntry();
            File[] children = path.listFiles();
            if (children != null) {
                for (File child : children) {
                    addFileToTarGz(tOut, child, entryName + "/");
                }
            }
        }
    }

    /**
     * extracts contents of a tar.gz file to a folder.
     *
     * @param tarGzFileStream stream of .tar.gz file.
     * @param outputDirPath   address of destination folder
     * @return The {@link List} of {@link File}s with the untared content.
     * @throws IOException
     * @throws ArchiveException
     */
    public static File extractTarGz(InputStream tarGzFileStream,
                                    File outputDirPath) throws IOException, ArchiveException {
        File file = new File("temp" + System.currentTimeMillis() + ".tar");
        extractGz(tarGzFileStream, file);
        List<File> list = extractTar(file.getAbsoluteFile(), outputDirPath);
        file.delete();
        return list.get(0);
    }

    /**
     * extracts contents of a tar.gz file to a folder.
     *
     * @param tarGzFileAddress address of .tar.gz file.
     * @param outputDirPath    address of destination folder
     * @return The {@link List} of {@link File}s with the untared content.
     * @throws IOException
     * @throws ArchiveException
     */
    public static File extractTarGz(File tarGzFileAddress, File outputDirPath)
            throws IOException, ArchiveException {
        File file = new File("temp" + System.currentTimeMillis() + ".tar");
        extractGz(tarGzFileAddress, file);
        List<File> list = extractTar(file.getAbsoluteFile(), outputDirPath);
        file.delete();
        return list.get(0);
    }

    /**
     * extracts a tar.gz file to a tar file. you can extract tar file later.
     *
     * @param tarGzFileAddress address of tar.gz file.
     * @param tarFileAddress   address of extracted .tar file. it must includes full file
     *                         name. for example: /home/user/test.tar
     */
    public static void extractGz(File tarGzFileAddress, File tarFileAddress) {
        FileOutputStream out = null;
        GzipCompressorInputStream gzIn = null;
        try {
            FileInputStream fin = new FileInputStream(tarGzFileAddress);
            BufferedInputStream in = new BufferedInputStream(fin);
            out = new FileOutputStream(tarFileAddress);
            gzIn = new GzipCompressorInputStream(in);
            final int bufferSize = 10240;
            final byte[] buffer = new byte[bufferSize];
            int n = 0;
            while (-1 != (n = gzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
        } catch (Exception exp) {
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                }
            if (gzIn != null)
                try {
                    gzIn.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * extracts a tar.gz file to a tar file. you can extract tar file later.
     *
     * @param tarGzFileStream stream of tar.gz file.
     * @param tarFileAddress  address of extracted .tar file. it must includes full file
     *                        name. for example: /home/user/test.tar
     */
    public static void extractGz(InputStream tarGzFileStream,
                                 File tarFileAddress) {
        FileOutputStream out = null;
        GzipCompressorInputStream gzIn = null;
        try {
            BufferedInputStream in = new BufferedInputStream(tarGzFileStream);
            out = new FileOutputStream(tarFileAddress);
            gzIn = new GzipCompressorInputStream(in);
            final int bufferSize = 10240;
            final byte[] buffer = new byte[bufferSize];
            int n = 0;
            while (-1 != (n = gzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
        } catch (Exception exp) {
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                }
            if (gzIn != null)
                try {
                    gzIn.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     * Untar an input file into an output file.
     * <p>
     * The output file is created in the output folder, having the same name as
     * the input file, minus the '.tar' extension.
     *
     * @param tarFileAddress the input .tar file
     * @param outputDirPath  the output directory file.
     * @return The {@link List} of {@link File}s with the untared content.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ArchiveException
     */
    public static List<File> extractTar(final File tarFileAddress,
                                        final File outputDirPath) throws FileNotFoundException,
            IOException, ArchiveException {
        final List<File> untaredFiles = new LinkedList<File>();

        if (!tarFileAddress.exists())
            return null;

        outputDirPath.mkdirs();

        final InputStream is = new FileInputStream(tarFileAddress);
        final TarArchiveInputStream debInputStream = (TarArchiveInputStream) new ArchiveStreamFactory()
                .createArchiveInputStream("tar", is);
        TarArchiveEntry entry = null;
        while ((entry = (TarArchiveEntry) debInputStream.getNextEntry()) != null) {
            final File outputFile = new File(outputDirPath, entry.getName());
            if (entry.isDirectory()) {
                if (!outputFile.exists()) {
                    if (!outputFile.mkdirs()) {
                        throw new IllegalStateException(String.format(
                                "Couldn't create directory %s.", outputFile
                                        .getAbsolutePath()));
                    }
                }
            } else {
                final OutputStream outputFileStream = new FileOutputStream(
                        outputFile);
                IOUtils.copy(debInputStream, outputFileStream);
                outputFileStream.close();
            }
            untaredFiles.add(outputFile);
        }
        debInputStream.close();

        return untaredFiles;
    }

    public static void removeDir(File f) throws FileNotFoundException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                removeDir(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
    }

    /**
     * removes folder and its sub folders
     *
     * @param path
     * @throws IOException
     */
    public static void removePath(Path path) throws IOException {
        if (!path.toFile().isDirectory())
            throw new IOException(path.toString() + " is not a directory");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file,
                                             BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                // try to delete the file anyway, even if its attributes
                // could not be read, since delete-only access is
                // theoretically possible
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                if (exc == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    // directory iteration failed; propagate exception
                    throw exc;
                }
            }
        });
    }
}
