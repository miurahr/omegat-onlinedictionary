package tokyo.northside.omegawiki.dtd;

public class OmegawikiMeaning {
    private String langid;
    private String lang;
    private String e;
    private String im;

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

    public String getE() {
        return e;
    }

    public void setE(final String e) {
        this.e = e;
    }

    public String getIm() {
        return im;
    }

    public void setIm(final String im) {
        this.im = im;
    }

    @Override
    public String toString() {
        return "{" + "langid='" + langid + '\'' + ", lang='" + lang + '\''
                + ", e='" + e + '\'' + ", im='" + im + '\'' + '}';
    }
}
