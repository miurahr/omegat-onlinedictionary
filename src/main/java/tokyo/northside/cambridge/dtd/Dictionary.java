package tokyo.northside.cambridge.dtd;

public class Dictionary {
    private String dictionaryCode;
    private String dictionaryName;
    private String dictionaryUrl;

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryUrl() {
        return dictionaryUrl;
    }

    public void setDictionaryUrl(String dictionaryUrl) {
        this.dictionaryUrl = dictionaryUrl;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryCode='" + dictionaryCode + '\'' +
                ", dictionaryName='" + dictionaryName + '\'' +
                ", dictionaryUrl='" + dictionaryUrl + '\'' +
                '}';
    }
}
