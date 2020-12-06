package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class LexicalEntry {
    @JsonIgnore
    private List<Compound> compounds;
    private List<Entry> entries;
    private String language;
    private String text;
    @JsonIgnore
    private LexicalCategory lexicalCategory;
    @JsonIgnore
    private List<Derivative> derivatives;
    private List<Phrase> phrases;

    public List<Compound> getCompounds() {
        return compounds;
    }

    public void setCompounds(final List<Compound> compounds) {
        this.compounds = compounds;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LexicalCategory getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(LexicalCategory lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public List<Derivative> getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(List<Derivative> derivatives) {
        this.derivatives = derivatives;
    }

    @Override
    public String toString() {
        return "LexicalEntry{" +
                "compounds=" + compounds +
                ", entries=" + entries +
                ", language='" + language + '\'' +
                ", text='" + text + '\'' +
                ", lexicalCategory=" + lexicalCategory +
                ", phrases=" + phrases +
                '}';
    }
}
