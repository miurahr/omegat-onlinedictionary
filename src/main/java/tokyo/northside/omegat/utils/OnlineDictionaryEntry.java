package tokyo.northside.omegat.utils;

import java.util.ArrayList;
import java.util.List;

public class OnlineDictionaryEntry {
    private String senseId;
    private List<String> definitions = new ArrayList<>();
    private List<String> translations = new ArrayList<>();

    public OnlineDictionaryEntry(String senseId) {
        this.senseId = senseId;
    }

    public OnlineDictionaryEntry(String senseId, String definition) {
        this.senseId = senseId;
        this.definitions.add(definition);
    }

    public String getSenseId() {
        return senseId;
    }

    public void setSenseId(String senseId) {
        this.senseId = senseId;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void addDefinition(final String definition) {
        this.definitions.add(definition);
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void addTranslation(final String translation) {
        this.translations.add(translation);
    }
}
