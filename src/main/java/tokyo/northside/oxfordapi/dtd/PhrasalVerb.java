package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class PhrasalVerb {
    private List<Domain> domains;
    private String id;
    private String language;
    private List<Region> regions;
    private List<Register> registers;
    private String text;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PhrasalVerb{" +
                "domains=" + domains +
                ", id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", regions=" + regions +
                ", registers=" + registers +
                ", text='" + text + '\'' +
                '}';
    }
}
