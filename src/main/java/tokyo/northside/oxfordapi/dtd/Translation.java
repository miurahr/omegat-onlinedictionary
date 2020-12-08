package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Translation {
    private String language;
    private String text;
    private String type;
    private List<Collocation> collocations;
    private List<Domain> domains;
    private List<GrammaticalFeature> grammaticalFeatures;
    private List<Note> notes;
    private List <Region> regions;
    private List <Register> registers;
    private List<ToneGroup> toneGroups;

    public List<Collocation> getCollocations() {
        return collocations;
    }

    public void setCollocations(List<Collocation> collocations) {
        this.collocations = collocations;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
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

    public List<ToneGroup> getToneGroups() {
        return toneGroups;
    }

    public void setToneGroups(List<ToneGroup> toneGroups) {
        this.toneGroups = toneGroups;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "collocations=" + collocations +
                ", domains=" + domains +
                ", grammaticalFeatures=" + grammaticalFeatures +
                ", language='" + language + '\'' +
                ", notes=" + notes +
                ", regions=" + regions +
                ", registers=" + registers +
                ", text='" + text + '\'' +
                ", toneGroups=" + toneGroups +
                ", type='" + type + '\'' +
                '}';
    }
}
