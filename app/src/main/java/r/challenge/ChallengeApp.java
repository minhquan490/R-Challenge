package r.challenge;

import java.io.IOException;

import r.challenge.extractor.Extractor;
import r.challenge.extractor.ExtractorFactory;
import r.challenge.reader.Reader;
import r.challenge.reader.ReaderFactory;
import r.challenge.writer.Writer;
import r.challenge.writer.WriterFactory;

public class ChallengeApp {
    private static Reader reader;
    private static Writer writer;
    private static Extractor extractor;

    private ChallengeApp(String dataFilePath, String inputFileName, String outputFileName) {
        reader = ReaderFactory.getReader(dataFilePath, inputFileName);
        writer = WriterFactory.getWriter(dataFilePath, outputFileName);
        extractor = ExtractorFactory.getExtractor();
    }

    public void run() throws IOException {
        PreviousLand previousLand = new PreviousLand();
        String textLine;
        while ((textLine = reader.read()) != null) {
            String[] lineSplited = textLine.split(" ");
            String[] landInfo = subXCharacter(lineSplited[1]);
            Land nowLand = new Land(lineSplited[0], extractor.extractNumber(landInfo[0]),
                    extractor.extractNumber(landInfo[1]));
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

    private String[] subXCharacter(String target) {
        int xCharacterPosition = target.lastIndexOf("x");
        String[] c = { target.substring(0, xCharacterPosition), target.substring(xCharacterPosition + 1) };
        return c;
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
