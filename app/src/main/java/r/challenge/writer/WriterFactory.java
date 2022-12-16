package r.challenge.writer;

public class WriterFactory {
    public static Writer getWriter(String path, String fileName) {
        return new DeafaultWriter(path + "/" + fileName);
    }
}
