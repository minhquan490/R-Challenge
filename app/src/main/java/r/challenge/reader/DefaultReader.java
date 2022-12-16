package r.challenge.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DefaultReader implements Reader {
    private BufferedReader bufferedReader;

    public DefaultReader(String path) {
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Can't find file with path " + path, e);
        }
    }

    @Override
    public String read() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
