package tokyo.northside.cambridge.dtd;

public class Entry {

    private String dictionaryCode;
    private String entryContent;
    private String entryId;
    private String entryLabel;
    private String entryUrl;
    private String format;

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryLabel() {
        return entryLabel;
    }

    public void setEntryLabel(String entryLabel) {
        this.entryLabel = entryLabel;
    }

    public String getEntryUrl() {
        return entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "dictionaryCode='" + dictionaryCode + '\'' +
                ", entryContent='" + entryContent + '\'' +
                ", entryId='" + entryId + '\'' +
                ", entryLabel='" + entryLabel + '\'' +
                ", entryUrl='" + entryUrl + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
