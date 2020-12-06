package tokyo.northside.omegawiki;

public class Definition {
    private String spelling;
    private Integer langid;
    private String lang;
    private String text;

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public Integer getLangid() {
        return langid;
    }

    public void setLangid(Integer langid) {
        this.langid = langid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
                "spelling='" + spelling + '\'' +
                ", langid=" + langid +
                ", lang='" + lang + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
