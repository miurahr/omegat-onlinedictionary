package tokyo.northside.oxfordapi;

import java.util.List;

public class Entry {
    private String id;
    private String language;
    private List<LexicalEntry> lexicalEntryList;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public List<LexicalEntry> getLexicalEntryList() {
        return lexicalEntryList;
    }

    public void setLexicalEntryList(final List<LexicalEntry> lexicalEntryList) {
        this.lexicalEntryList = lexicalEntryList;
    }
}
