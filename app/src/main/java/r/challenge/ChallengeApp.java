package r.challenge;

import java.io.IOException;

import r.challenge.extractor.Extractor;
import r.challenge.extractor.ExtractorFactory;
import r.challenge.reader.Reader;
import r.challenge.reader.ReaderFactory;
import r.challenge.writer.Writer;
import r.challenge.writer.WriterFactory;

public class ChallengeApp {
    private final String dataFilePath;
    private final String inputFileName;
    private final String outputFileName;

    private ChallengeApp(String dataFilePath, String inputFileName, String outputFileName) {
        this.dataFilePath = dataFilePath;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void run() throws IOException {
        Reader reader = ReaderFactory.getReader(dataFilePath, inputFileName);
        Writer writer = WriterFactory.getWriter(dataFilePath, outputFileName);
        Extractor extractor = ExtractorFactory.getExtractor();
        PreviousLand previousLand = new PreviousLand();
        while (true) {
            if (reader.read() == null) {
                break;
            }
            StringBuilder line = new StringBuilder(reader.read());
            String[] lineSplited = line.toString().split(" ");
            line.setLength(0);
            line.append(lineSplited[1]);
            String[] landInfo = reverse(line.toString()).split("x", 2);
            String width = reverse(landInfo[1]);
            String height = reverse(landInfo[0]);
            Land nowLand = new Land(lineSplited[0], extractor.extractNumber(width), extractor.extractNumber(height));
            if (previousLand.getValue() == null) {
                previousLand.setValue(nowLand);
            }
            if (previousLand.getValue().getArea() < nowLand.getArea()) {
                previousLand.setValue(nowLand);
            }
        }
        Land land = previousLand.getValue();
        writer.write(land.getOwner(), land.getArea());
        reader.close();
        writer.close();
    }

    public static Builder builder() {
        return new Builder();
    }

    private String reverse(String target) {
        StringBuilder builder = new StringBuilder(target);
        return builder.reverse().toString();
    }

    static class Builder {
        private String dataFilePath;
        private String inputFileName;
        private String outputFileName;

        public Builder filePath(String dataFilePath) {
            this.dataFilePath = dataFilePath;
            return this;
        }

        public Builder inputFile(String inputFileName) {
            this.inputFileName = inputFileName;
            return this;
        }

        public Builder outputFile(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        public ChallengeApp build() {
            return new ChallengeApp(dataFilePath, inputFileName, outputFileName);
        }
    }
}
