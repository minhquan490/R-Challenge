package r.challenge.writer;

import java.io.IOException;

public interface Writer {

    public void write(String name, int value) throws IOException;

    public void close() throws IOException;
}
