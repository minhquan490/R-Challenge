package r.challenge.extractor;

public class ExtractorFactory {
    public static Extractor getExtractor() {
        return new DefaultExtractor();
    }
}