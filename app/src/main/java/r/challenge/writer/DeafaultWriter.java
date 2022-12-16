package r.challenge.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DeafaultWriter implements Writer {
    private BufferedWriter bufferedWriter;
    private static final String OWNER_TEMPLATE = "Owner: ";
    private static final String AREA_TEMPLATE = "Area: ";

    public DeafaultWriter(String path) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path), 100);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't find output file with path " + path, e);
        }
    }

    public void write(String name, int value) throws IOException {
        bufferedWriter.write(OWNER_TEMPLATE + name + ", " + AREA_TEMPLATE + value);
    }

    public void close() throws IOException {
        bufferedWriter.close();
    }
}
