package r.challenge.extractor;

public class DefaultExtractor implements Extractor {

    @Override
    public String extractNumber(String target) {
        StringBuffer extracted = new StringBuffer();
        for (char a : target.toCharArray()) {
            if (Character.isDigit(a)) {
                extracted.append(a);
            }
        }
        return extracted.toString();
    }
}
