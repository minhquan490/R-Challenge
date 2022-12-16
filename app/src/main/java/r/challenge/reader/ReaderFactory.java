package r.challenge.reader;

public final class ReaderFactory {
    public static Reader getReader(String path, String flieName) {
        return new DefaultReader(path + "/" + flieName);
    }
}
