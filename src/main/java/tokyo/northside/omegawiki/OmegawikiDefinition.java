package tokyo.northside.omegawiki;

public class OmegawikiDefinition {
    private String dmid;
    private String langid;
    private String lang;
    private Definition definition;

    public String getDmid() {
        return dmid;
    }

    public void setDmid(final String dmid) {
        this.dmid = dmid;
    }

    public String getLangid() {
        return langid;
    }

    public void setLangid(final String langid) {
        this.langid = langid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(final Definition definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Definition{" + "dmid=" + dmid + ", langid=" + langid + ","
               + " lang='" + lang + '\'' + ", definition=" + definition + '}';
    }
}
