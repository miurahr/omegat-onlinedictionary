package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class VariantForm {
    private List<Pronunciation> pronunciations;
    private String text;

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "VariantForm{" + "pronunciations=" + pronunciations + ", text='" + text + '\'' + '}';
    }
}
