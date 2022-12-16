package r.challenge.reader;

import java.io.IOException;

public interface Reader {

    public String read() throws IOException;

    public void close() throws IOException;
}
