package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Result {
    private String id;
    private String language;
    private List<LexicalEntry> lexicalEntries;
    private String type;
    private String word;

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

    public List<LexicalEntry> getLexicalEntries() {
        return lexicalEntries;
    }

    public void setLexicalEntries(final List<LexicalEntry> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Results{" + "id='" + id + '\'' + ", language='" + language + '\''
                + ", lexicalEntries=" + lexicalEntries + ", type='" + type + '\'' + ", word='" + word + '\'' + '}';
    }
}
