package tokyo.northside.omegawiki.dtd;

public class Definition {
    private String spelling;
    private Integer langid;
    private String lang;
    private String text;

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(final String spelling) {
        this.spelling = spelling;
    }

    public Integer getLangid() {
        return langid;
    }

    public void setLangid(final Integer langid) {
        this.langid = langid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" + "spelling='" + spelling + '\'' + ", langid=" + langid
                + ", lang='" + lang + '\'' + ", text='" + text + '\'' + '}';
    }
}
