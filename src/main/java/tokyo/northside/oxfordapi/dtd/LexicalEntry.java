package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class LexicalEntry {
    private List<Compound> compounds;
    private List<Entry> entries;
    private String language;
    private String text;
    private LexicalCategory lexicalCategory;
    private List<Derivative> derivatives;
    private List<DerivativeOf> derivativeOf;
    private List<Phrase> phrases;
    private List<PhrasalVerb> phrasalVerbs;

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

    public List<PhrasalVerb> getPhrasalVerbs() {
        return phrasalVerbs;
    }

    public void setPhrasalVerbs(List<PhrasalVerb> phrasalVerbs) {
        this.phrasalVerbs = phrasalVerbs;
    }

    public List<DerivativeOf> getDerivativeOf() {
        return derivativeOf;
    }

    public void setDerivativeOf(List<DerivativeOf> derivativeOf) {
        this.derivativeOf = derivativeOf;
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
